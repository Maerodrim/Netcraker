package com.example.DataRoadsAndWeather.Controller;


import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Enum.CardStatus;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;
import com.example.DataRoadsAndWeather.Repo.CardRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("CardController")
public class CardController {
    @Autowired
    private CardRepo cardRepo;

    @GetMapping
    public String main(Map<String, Object> model) {

        Iterable<Card> cards = cardRepo.findAll();

        model.put("card", cards);

        return "main";
    }

    @PostMapping("addCard")
    public String addCard(@RequestParam String nameCard, @RequestParam Integer dataSession, @RequestParam Integer development,
                          @RequestParam Integer analysis, @RequestParam Integer testing
            , @RequestParam Double Money, @RequestParam ColorCard color,
                          @RequestParam Integer priority, @RequestParam Integer subs, Map<String, Object> model) {
        Card card = new Card(
                nameCard,
                dataSession, 0,
                0, development, 0, analysis, 0, testing,
                Money, subs, color,
                CardStatus.Selected, priority);

        cardRepo.save(card);

        Iterable<Card> cards = cardRepo.findAll();

        model.put("card", card);

        return "Ok";
    }

    @JsonView(View.UI.class)
    @GetMapping("getCard")
    public Card getCard(@RequestParam Integer idCard, Map<String, Object> model) {
        return cardRepo.findByIdCard(idCard).get(0);
    }

    @PostMapping("updateAnal")
    public String updateAnal(@RequestParam Integer idCard, @RequestParam Integer anal, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).addAnalysis(anal);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDev")
    public String updateDev(@RequestParam Integer idCard, @RequestParam Integer dev, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).addDevelopment(dev);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateTest")
    public String updateTest(@RequestParam Integer idCard, @RequestParam Integer test, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).addTesting(test);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateStatus")
    public String updateStatus(@RequestParam Integer idCard, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).nextCardStatus();
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("backStatus")
    public String backStatus(@RequestParam Integer idCard, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).backCardStatus();
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDateBeg")
    public String updateDateBeg(@RequestParam Integer idCard, @RequestParam Integer date, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).setDataBegSession(date);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping("updateDateEnd")
    public String updateDateEnd(@RequestParam Integer idCard, @RequestParam Integer date, Map<String, Object> model) {
        cardRepo.findByIdCard(idCard).get(0).setDataEndSession(date);
        cardRepo.save(cardRepo.findByIdCard(idCard).get(0));
        return "Ok";
    }

    @PostMapping(path = "/allCard")
    public String delete() {
        cardRepo.deleteAll();
        return "OK";
    }

    @PostMapping("filterCard")
    public String filter(@RequestParam Integer filter, Map<String, Object> model) {
        Iterable<Card> card;

        if (filter != null && filter != 0) {
            card = cardRepo.findByIdCard(filter);
        } else {
            card = cardRepo.findAll();
        }

        model.put("card", card);

        return "main";
    }
}
