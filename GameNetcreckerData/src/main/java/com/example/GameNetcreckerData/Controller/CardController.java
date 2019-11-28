package com.example.GameNetcreckerData.Controller;


import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.Card;
import com.example.GameNetcreckerData.Model.Cube;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.ColorCard;
import com.example.GameNetcreckerData.Repo.CardRepo;
import com.example.GameNetcreckerData.Repo.CubeRepo;
import com.example.GameNetcreckerData.Repo.NullPackCardRepo;
import com.example.GameNetcreckerData.Repo.UsersRepo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("CardController")
@Log4j2
public class CardController{
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private NullPackCardRepo nullPackCardRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CubeRepo cubeRepo;

    @PostMapping("addCard")
    public String addCard(@RequestParam String email, @RequestParam String nameCard,
                          @RequestParam Integer dataSession, @RequestParam Integer development,
                          @RequestParam Integer analysis, @RequestParam Integer testing,
                          @RequestParam Double Money, @RequestParam Integer color,
                          @RequestParam Integer priority, @RequestParam Integer subs) {
        Card card = new Card(
                nameCard,
                dataSession, 0,
                0, development, 0, analysis, 0, testing,
                Money, subs, ColorCard.values()[color],
                CardStatus.Selected, priority, email);
        usersRepo.findByEmail(email).get(0).addCard(card);
        cardRepo.save(card);
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        return "Ok";
    }

    @PostMapping("addNullPackCard")
    public String addNullPackCard(@RequestParam String email) {
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
        log.info("Добавлен нулевой набор");
        return "Ok";
    }

    @JsonView(View.CARD.class)
    @GetMapping("getCard")
    public Card getCard(@RequestParam Integer idCard) {
        return cardRepo.findByIdCard(idCard).get(0);
    }
    @JsonView(View.CARD.class)
    @GetMapping("getCardWhite")
    public List<Card> getCardWhite(@RequestParam String email) {
        return cardRepo.findByEmailAndColorCard(email,ColorCard.White);
    }

    @JsonView(View.CARD.class)
    @GetMapping("getCardByStatus")
    public List<Card> getCardByStatus(@RequestParam String email, @RequestParam Integer status) {
        return cardRepo.findByStatusAndEmail(CardStatus.values()[status], email);
    }

    @JsonView(View.CARD.class)
    @GetMapping("getAllCard")
    public Set<Card> getAllCard(@RequestParam String email) {
        return usersRepo.findByEmail(email).get(0).getCard();
    }

    @PostMapping("updateAnal")
    public String updateAnal(@RequestParam Integer idCard, @RequestParam Integer anal) {
        cardRepo.findByIdCard(idCard).get(0).addAnalysis(anal);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDev")
    public String updateDev(@RequestParam Integer idCard, @RequestParam Integer dev) {
        cardRepo.findByIdCard(idCard).get(0).addDevelopment(dev);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateTest")
    public String updateTest(@RequestParam Integer idCard, @RequestParam Integer test) {
        cardRepo.findByIdCard(idCard).get(0).addTesting(test);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        if(cardRepo.findByIdCard(idCard).get(0).getStatus()==CardStatus.ReadyDeploy){
            updateDateEnd(cardRepo.findByIdCard(idCard).get(0).getIdCard(),usersRepo.findByEmail(cardRepo.findByIdCard(idCard).get(0).getEmail()).get(0).getDay());
            cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        }
        return "Ok";
    }

    @PostMapping("updateStatus")
    public String updateStatus(@RequestParam Integer idCard) {
        cardRepo.findByIdCard(idCard).get(0).nextCardStatus();
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("backStatus")
    public String backStatus(@RequestParam Integer idCard) {
        cardRepo.findByIdCard(idCard).get(0).backCardStatus();
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDateBeg")
    public String updateDateBeg(@RequestParam Integer idCard, @RequestParam Integer date) {
        cardRepo.findByIdCard(idCard).get(0).setDataBegSession(date);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDateEnd")
    public String updateDateEnd(@RequestParam Integer idCard, @RequestParam Integer date) {
        cardRepo.findByIdCard(idCard).get(0).setDataEndSession(date);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @DeleteMapping(path = "DeleteCard")
    public String deleteCard(@RequestParam Integer idCard) {
        cardRepo.delete(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @DeleteMapping(path = "/allDeleteCardUsers")
    public String delete(@RequestParam String email) {
        cardRepo.deleteAll(usersRepo.findByEmail(email).get(0).getCard());
        return "Ok";
    }

    @JsonView(View.CARD.class)
    @PostMapping("updateCards")
    public String updateCards(@RequestParam ArrayList<Card> card) {

        for (int i = 0; i < card.size(); i++) {
            cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).updateCard(card.get(i));
            cardRepo.save(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0));
            if(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getStatus()==CardStatus.AnalProg){
                updateDateBeg(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getIdCard(),usersRepo.findByEmail(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getEmail()).get(0).getDay());
                cardRepo.save(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0));
            }
            if(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getStatus()==CardStatus.ReadyDeploy){
                updateDateEnd(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getIdCard(),usersRepo.findByEmail(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0).getEmail()).get(0).getDay());
                cardRepo.save(cardRepo.findByIdCard(card.get(i).getIdCard()).get(0));
            }
        }
        return "Ok";
    }

    @JsonView(View.CARD.class)
    @PostMapping("updateArrayAnal")
    public String updateArrayAnal(@RequestParam ArrayList<Integer> idCard, @RequestParam ArrayList<Integer> anal) {
        for (int i = 0; i < idCard.size(); i++) {
            cardRepo.findByIdCard(idCard.get(i)).get(0).addAnalysis(anal.get(i));
            cardRepo.save(cardRepo.findByIdCard(idCard.get(i)).get(0));
        }
        return "Ok";
    }

    @JsonView(View.CARD.class)
    @PostMapping("updateArrayDev")
    public String updateArrayDev(@RequestParam ArrayList<Integer> idCard, @RequestParam ArrayList<Integer> dev) {
        for (int i = 0; i < idCard.size(); i++) {
            cardRepo.findByIdCard(idCard.get(i)).get(0).addDevelopment(dev.get(i));
            cardRepo.save(cardRepo.findByIdCard(idCard.get(i)).get(0));
        }
        return "Ok";
    }

    @JsonView(View.CARD.class)
    @PostMapping("updateArrayTest")
    public String updateArrayTest(@RequestParam ArrayList<Integer> idCard, @RequestParam ArrayList<Integer> test) {
        for (int i = 0; i < idCard.size(); i++) {
            cardRepo.findByIdCard(idCard.get(i)).get(0).addTesting(test.get(i));
            cardRepo.save(cardRepo.findByIdCard(idCard.get(i)).get(0));
            if(cardRepo.findByIdCard(idCard.get(i)).get(0).getStatus()==CardStatus.ReadyDeploy){
                updateDateEnd(cardRepo.findByIdCard(idCard.get(i)).get(0).getIdCard(),usersRepo.findByEmail(cardRepo.findByIdCard(idCard.get(i)).get(0).getEmail()).get(0).getDay());
                cardRepo.save(cardRepo.findByIdCard(idCard.get(i)).get(0));
            }

        }
        return "Ok";
    }

    @JsonView(View.CUBA.class)
    @GetMapping("getCube")
    public Cube getCube(@RequestParam Integer day,@RequestParam Integer idGameTable) {
        return cubeRepo.findByDayAndIdGameTable(day,idGameTable);
    }
}
