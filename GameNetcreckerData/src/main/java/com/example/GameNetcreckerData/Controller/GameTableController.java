package com.example.GameNetcreckerData.Controller;


import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.*;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.TableStatus;
import com.example.GameNetcreckerData.Repo.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("GameTableController")
@Log4j2
public class GameTableController {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CubeRepo cubeRepo;
    @Autowired
    private GameTableRepo gameTableRepo;
    @Autowired
    private EventsRepo eventsRepo;
    @Autowired
    private NullPackCardRepo nullPackCardRepo;
    @Autowired
    private GraphGameRepo graphGameRepo;

    @JsonView(View.CUBA.class)
    @PostMapping("addGameTable")
    public Integer addGameTable(@RequestParam String nameGameTable, @RequestParam Integer numberOfPlayers) {
        GameTable gameTable = new GameTable(nameGameTable, numberOfPlayers);
        gameTableRepo.save(gameTable);
        newCube(gameTable.getIdGameTable());
        gameTable.setCube(cubeRepo.findByIdGameTable(gameTable.getIdGameTable()));
        gameTableRepo.save(gameTable);
        return gameTable.getIdGameTable();
    }

    @PostMapping("addUsers")
    public Integer addUsers(@RequestParam Integer IdGameTable, @RequestParam String email) {
        gameTableRepo.findByIdGameTable(IdGameTable).addUsers(usersRepo.findByEmail(email).get(0));
        usersRepo.findByEmail(email).get(0).setGameTable(gameTableRepo.findByIdGameTable(IdGameTable).getIdGameTable());
        usersRepo.findByEmail(email).get(0).setDay(7);
        cardRepo.deleteAll(usersRepo.findByEmail(email).get(0).getCard());
        for (int i = 0; i < 36; i++) {
            Card card = new Card(
                    nullPackCardRepo.findByIdCard(i).get(0).getNameCard(),
                    nullPackCardRepo.findByIdCard(i).get(0).getDataBegSession(),
                    nullPackCardRepo.findByIdCard(i).get(0).getDataEndSession(),
                    nullPackCardRepo.findByIdCard(i).get(0).getDevelopment(),
                    nullPackCardRepo.findByIdCard(i).get(0).getAllDevelopment(),
                    nullPackCardRepo.findByIdCard(i).get(0).getAnalysis(),
                    nullPackCardRepo.findByIdCard(i).get(0).getAllAnalysis(),
                    nullPackCardRepo.findByIdCard(i).get(0).getTesting(),
                    nullPackCardRepo.findByIdCard(i).get(0).getAllTesting(),
                    nullPackCardRepo.findByIdCard(i).get(0).getMoney(),
                    nullPackCardRepo.findByIdCard(i).get(0).getSubs(),
                    nullPackCardRepo.findByIdCard(i).get(0).getColorCard(),
                    nullPackCardRepo.findByIdCard(i).get(0).getStatus(),
                    nullPackCardRepo.findByIdCard(i).get(0).getPriority(),
                    email);
            usersRepo.findByEmail(email).get(0).addCard(card);
            cardRepo.save(card);
            usersRepo.save(usersRepo.findByEmail(email).get(0));
        }
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        gameTableRepo.save(gameTableRepo.findByIdGameTable(IdGameTable));
        return gameTableRepo.findByIdGameTable(IdGameTable).getNumberOfPlayers()-gameTableRepo.findByIdGameTable(IdGameTable).getUser().size() ;
    }

    @JsonView(View.CUBA.class)
    @PostMapping("newCube")
    public void newCube(@RequestParam Integer idGameTable) {
        Random rand = new java.util.Random();
        for (int i = 8; i < 22; i++) {
            Cube cude = new Cube(i,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    Math.abs(rand.nextInt()) % 6 + 1,
                    idGameTable);
            cubeRepo.save(cude);
        }
    }

    @JsonView(View.Events.class)
    @PostMapping("/newDay")
    public Events newDay(@RequestParam Integer idGameTable) {
        gameTableRepo.findByIdGameTable(idGameTable).setStatus(TableStatus.Game);
        gameTableRepo.findByIdGameTable(idGameTable).setDay(gameTableRepo.findByIdGameTable(idGameTable).getDay() + 1);
        List<Users> users = List.copyOf(gameTableRepo.findByIdGameTable(idGameTable).getUser());
        if (gameTableRepo.findByIdGameTable(idGameTable).getDay() < 21) {
            for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
                usersRepo.findByEmail(users.get(i).getEmail()).get(0).newDay();
                usersRepo.save(usersRepo.findByEmail(users.get(i).getEmail()).get(0));
                GraphGame graphGame = new GraphGame(
                        gameTableRepo.findByIdGameTable(idGameTable).getDay(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalProg).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalDone).size(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevProg).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevDone).size(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Test).size(),
                        users.get(i).getEmail()
                );
                if (gameTableRepo.findByIdGameTable(idGameTable).getDay() % 3 == 0) {
                    for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).size(); j++)
                        graphGame.setCost(
                                (int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getSubs() *
                                        (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataEndSession()
                                                - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataBegSession())
                                        + (Double) cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getMoney()));
                    for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).size(); j++)
                        graphGame.setCost(
                                (int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getSubs() *
                                        (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataEndSession()
                                                - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataBegSession())
                                        + (Double) cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getMoney()));
                }
                graphGameRepo.save(graphGame);
            }
            gameTableRepo.save(gameTableRepo.findByIdGameTable(idGameTable));
            return eventsRepo.findByDay(gameTableRepo.findByIdGameTable(idGameTable).getDay() + 1);
        } else {
            gameTableRepo.findByIdGameTable(idGameTable).setStatus(TableStatus.End);
            if (gameTableRepo.findByIdGameTable(idGameTable).getDay() > 21) {
                for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
                   graphGameRepo.deleteAll(graphGameRepo.findByEmail(users.get(i).getEmail()));
                }
                gameTableRepo.delete(gameTableRepo.findByIdGameTable(idGameTable));
                cubeRepo.deleteAll(cubeRepo.findByIdGameTable(idGameTable));

                return eventsRepo.findByDay(21);
            }
            for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
                usersRepo.findByEmail(users.get(i).getEmail()).get(0).newDay();
                usersRepo.save(usersRepo.findByEmail(users.get(i).getEmail()).get(0));
                GraphGame graphGame = new GraphGame(
                        gameTableRepo.findByIdGameTable(idGameTable).getDay(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalProg).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalDone).size(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevProg).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevDone).size(),
                        cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Test).size(),
                        users.get(i).getEmail()
                );
                if (gameTableRepo.findByIdGameTable(idGameTable).getDay() % 3 == 0) {
                    for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).size(); j++) {
                        graphGame.setCost(
                                (int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getSubs() *
                                        (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataEndSession()
                                                - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataBegSession())
                                        + (Double) cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getMoney()));
                    }
                        for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).size(); j++)
                            graphGame.setCost(
                                    (int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getSubs() *
                                            (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataEndSession()
                                                    - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataBegSession())
                                            + (Double) cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getMoney()));

                        graphGameRepo.save(graphGame);
                    }
                }
                gameTableRepo.save(gameTableRepo.findByIdGameTable(idGameTable));
                return eventsRepo.findByDay(gameTableRepo.findByIdGameTable(idGameTable).getDay() + 1);
            }
        }

    @JsonView(View.Events.class)
    @GetMapping("/getEvents")
    public Events getEvents(@RequestParam Integer day) {
        return eventsRepo.findByDay(day);
    }

    @JsonView(View.GAMETABLE.class)
    @GetMapping("/getData")
    public Integer getData(@RequestParam Integer idGameTable) {
        return gameTableRepo.findByIdGameTable(idGameTable).getDay();
    }

    @JsonView(View.GAMETABLE.class)
    @GetMapping("/getNumberOfPlayers")
    public Integer  getNumberOfPlayers(@RequestParam Integer idGameTable) {
        return gameTableRepo.findByIdGameTable(idGameTable).getNumberOfPlayers();
    }

    @DeleteMapping(path = "deleteUsersFromTable")
    public String deleteUsersFromTable(@RequestParam String email) {
        gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()).removeUsers(usersRepo.findByEmail(email).get(0));
        gameTableRepo.save(gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()));
        if(0==gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()).getUser().size()){
            gameTableRepo.delete(gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()));
        }
        graphGameRepo.deleteAll(graphGameRepo.findByEmail(email));
        return "Ok";
    }
}
