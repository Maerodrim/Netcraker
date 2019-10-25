package com.example.DataRoadsAndWeather.Controller;


import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Enum.CardStatus;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;
import com.example.DataRoadsAndWeather.Repo.CardRepo;
import com.example.DataRoadsAndWeather.Repo.SessionRepo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("CardController")
@Log4j2
public class CardController {
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private SessionRepo sessionRepo;

    @GetMapping
    public String main(Map<String, Object> model) {

        Iterable<Card> cards = cardRepo.findAll();

        model.put("card", cards);

        return "main";
    }

    @PostMapping("addCard")
    public String addCard(@RequestParam Integer idSession, @RequestParam String nameCard, @RequestParam Integer dataSession, @RequestParam Integer development,
                          @RequestParam Integer analysis, @RequestParam Integer testing
            , @RequestParam Double Money, @RequestParam Integer color,
                          @RequestParam Integer priority, @RequestParam Integer subs) {
        Card card = new Card(
                nameCard,
                dataSession, 0,
                0, development, 0, analysis, 0, testing,
                Money, subs, ColorCard.values()[color],
                CardStatus.Selected, priority, idSession);
        sessionRepo.findByIdSession(idSession).get(0).addCard(card);
        cardRepo.save(card);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @JsonView(View.SESSION.class)
    @GetMapping("getCard")
    public Card getCard(@RequestParam Integer idCard) {
        return cardRepo.findByIdCard(idCard).get(0);
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
}
