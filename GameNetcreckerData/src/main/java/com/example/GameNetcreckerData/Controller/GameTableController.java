package com.example.GameNetcreckerData.Controller;


import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.*;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.TableStatus;
import com.example.GameNetcreckerData.Repo.*;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
@RequestMapping("GameTableController")
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
        usersRepo.findByEmail(email).get(0).setGameTable(IdGameTable);
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        gameTableRepo.findByIdGameTable(IdGameTable).addUsers(usersRepo.findByEmail(email).get(0));
        usersRepo.findByEmail(email).get(0).setDay(8);
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        List<NullPackCard> nullCard = nullPackCardRepo.findAll();
        Set<Card> usersCard = usersRepo.findByEmail(email).get(0).getCard();
        Iterator<Card> iterator = usersCard.iterator();
        while (iterator.hasNext()) {
            usersRepo.findByEmail(email).get(0).removeCard(iterator.next());
        }
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        cardRepo.deleteAll(cardRepo.findByEmail(email));
        for (int i = 0; i < nullCard.size(); i++) {
            Card card = new Card(
                    nullCard.get(i).getNameCard(),
                    nullCard.get(i).getDataBegSession(),
                    nullCard.get(i).getDataEndSession(),
                    nullCard.get(i).getDevelopment(),
                    nullCard.get(i).getAllDevelopment(),
                    nullCard.get(i).getAnalysis(),
                    nullCard.get(i).getAllAnalysis(),
                    nullCard.get(i).getTesting(),
                    nullCard.get(i).getAllTesting(),
                    nullCard.get(i).getMoney(),
                    nullCard.get(i).getSubs(),
                    nullCard.get(i).getColorCard(),
                    nullCard.get(i).getStatus(),
                    nullCard.get(i).getPriority(),
                    email);
            cardRepo.save(card);
            usersRepo.findByEmail(email).get(0).addCard(card);
            usersRepo.save(usersRepo.findByEmail(email).get(0));
        }
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        gameTableRepo.save(gameTableRepo.findByIdGameTable(IdGameTable));
        return gameTableRepo.findByIdGameTable(IdGameTable).getNumberOfPlayers() - gameTableRepo.findByIdGameTable(IdGameTable).getUser().size();
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
        gameTableRepo.save(gameTableRepo.findByIdGameTable(idGameTable));
        List<Users> users = List.copyOf(gameTableRepo.findByIdGameTable(idGameTable).getUser());
        if (gameTableRepo.findByIdGameTable(idGameTable).getDay() < 22) {
            graphMade(idGameTable, users);
            return eventsRepo.findByDay(gameTableRepo.findByIdGameTable(idGameTable).getDay());
        } else {
            gameTableRepo.findByIdGameTable(idGameTable).setStatus(TableStatus.End);
            gameTableRepo.save(gameTableRepo.findByIdGameTable(idGameTable));
            return eventsRepo.findByDay(gameTableRepo.findByIdGameTable(idGameTable).getDay());
        }
    }

    @DeleteMapping("/endGame")
    public void endGame(@RequestParam Integer idGameTable){
        List<Users> users = List.copyOf(gameTableRepo.findByIdGameTable(idGameTable).getUser());
            for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
                graphGameRepo.deleteAll(graphGameRepo.findByEmail(users.get(i).getEmail()));
            }
            gameTableRepo.delete(gameTableRepo.findByIdGameTable(idGameTable));
            cubeRepo.deleteAll(cubeRepo.findByIdGameTable(idGameTable));
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
    public Integer getNumberOfPlayers(@RequestParam Integer idGameTable) {
        return gameTableRepo.findByIdGameTable(idGameTable).getNumberOfPlayers();
    }

    @DeleteMapping(path = "deleteUsersFromTable")
    public String deleteUsersFromTable(@RequestParam String email) {
        gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()).removeUsers(usersRepo.findByEmail(email).get(0));
        gameTableRepo.save(gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()));
        if (0 == gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()).getUser().size()) {
            gameTableRepo.delete(gameTableRepo.findByIdGameTable(usersRepo.findByEmail(email).get(0).getGameTable()));
        }
        graphGameRepo.deleteAll(graphGameRepo.findByEmail(email));
        return "Ok";
    }

    private void graphMade(Integer idGameTable, List<Users> users) {
        for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
            usersRepo.findByEmail(users.get(i).getEmail()).get(0).newDay();
            usersRepo.save(usersRepo.findByEmail(users.get(i).getEmail()).get(0));
            Integer anal = cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalProg).size();
            Integer dev = cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevProg).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.AnalDone).size();
            Integer test = cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Test).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.DevDone).size();
            Integer deploy = cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).size() + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).size();
            GraphGame graphGame = new GraphGame(
                    gameTableRepo.findByIdGameTable(idGameTable).getDay(),
                    anal + dev + test + deploy,
                    dev + test + deploy,
                    test + deploy,
                    deploy,
                    users.get(i).getEmail()
            );
            Integer cost=0;
            for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).size(); j++) {
                cost+=(int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getSubs() *
                                (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataEndSession()
                                        - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getDataBegSession())
                                + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.Deploed).get(j).getMoney());
            }
            for (int j = 0; j < cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).size(); j++){
                cost+= (int) (10 * cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getSubs() *
                                (cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataEndSession()
                                        - cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getDataBegSession())
                                + cardRepo.findByEmailAndStatus(users.get(i).getEmail(), CardStatus.ReadyDeploy).get(j).getMoney());}
            graphGame.setCost(cost);
            graphGameRepo.save(graphGame);
            gameTableRepo.save(gameTableRepo.findByIdGameTable(idGameTable));
        }
    }

    @PostMapping("addEvents")
    public String addEvents(@RequestParam String text,@RequestParam Integer date){
        Events card = new Events(date,text);
        eventsRepo.save(card);
        return "Ok";
    }

    @DeleteMapping(path = "deleteEvents")
    public String deleteEvents(@RequestParam Integer day) {
        eventsRepo.delete(eventsRepo.findByDay(day));
        return "Ok";
    }
}
