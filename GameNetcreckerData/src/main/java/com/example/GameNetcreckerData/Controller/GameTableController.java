package com.example.GameNetcreckerData.Controller;


import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.Card;
import com.example.GameNetcreckerData.Model.Cube;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.ColorCard;
import com.example.GameNetcreckerData.Model.Enum.TableStatus;
import com.example.GameNetcreckerData.Model.GameTable;
import com.example.GameNetcreckerData.Model.Users;
import com.example.GameNetcreckerData.Repo.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("GameTableController")
@Log4j2
public class GameTableController {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private NullPackCardRepo nullPackCardRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CubeRepo cubeRepo;
    @Autowired
    private GameTableRepo gameTableRepo;

    @JsonView(View.CUBA.class)
    @PostMapping("addGameTable")
    public String addGameTable(@RequestParam String nameGameTable) {
        GameTable gameTable = new GameTable(nameGameTable);
        newCube(gameTable.getIdGameTable());
        gameTable.setCube(cubeRepo.findByIdGameTable(gameTable.getIdGameTable()));
        gameTableRepo.save(gameTable);
        return "Ok";
    }

    @PostMapping("addUsers")
    public String addUsers(@RequestParam Integer IdGameTable, @RequestParam String email) {
        gameTableRepo.findByIdGameTable(IdGameTable).addUsers(usersRepo.findByEmail(email).get(0));
        usersRepo.findByEmail(email).get(0).setGameTable(gameTableRepo.findByIdGameTable(IdGameTable));
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        gameTableRepo.save(gameTableRepo.findByIdGameTable(IdGameTable));
        return "Ok";
    }

    @JsonView(View.CUBA.class)
    @PostMapping("newCube")
    public String newCube(@RequestParam Integer idGameTable) {
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
        return "Ok";
    }

    @JsonView(View.USERS.class)
    @PostMapping("/newDay")
    public Integer newDay(@RequestParam Integer idGameTable) {
        if(gameTableRepo.findByIdGameTable(idGameTable).getDay()!=21){
        gameTableRepo.findByIdGameTable(idGameTable).setStatus(TableStatus.Game);
        }else{
            gameTableRepo.findByIdGameTable(idGameTable).setStatus(TableStatus.End);
        }
        gameTableRepo.findByIdGameTable(idGameTable).setDay(gameTableRepo.findByIdGameTable(idGameTable).getDay() + 1);
        List<Users> users = List.copyOf(gameTableRepo.findByIdGameTable(idGameTable).getUser());
        for (int i = 0; i < gameTableRepo.findByIdGameTable(idGameTable).getUser().size(); i++) {
            usersRepo.findByEmail(users.get(i).getEmail()).get(0).newDay();
            usersRepo.save(usersRepo.findByEmail(users.get(i).getEmail()).get(0));
        }
        return gameTableRepo.findByIdGameTable(idGameTable).getDay();
    }
}
