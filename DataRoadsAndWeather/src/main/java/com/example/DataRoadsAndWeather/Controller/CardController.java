package com.example.DataRoadsAndWeather.Controller;


import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;
import com.example.DataRoadsAndWeather.Repo.CardRepo;
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

    @PostMapping("/addCard")
    public String add(@RequestParam String nameCard, @RequestParam Integer idSession,
                      @RequestParam Integer dataSession, @RequestParam Integer development,
                      @RequestParam Integer analysis, @RequestParam Integer testing
                      ,@RequestParam Double Money, @RequestParam ColorCard color,
                      @RequestParam Integer priority,@RequestParam Integer subs, Map<String, Object> model) {
       Card card = new Card(nameCard, idSession,dataSession,development,
               analysis,testing,Money,color,priority,subs);

        cardRepo.save(card);

        Iterable<Card> cards = cardRepo.findAll();

        model.put("card", card);

        return "main";
    }

    @PostMapping("/updateAnal")
    public String updateAnal(@RequestParam Integer idCard, @RequestParam Integer anal, Map<String, Object> model) {
        /*card.addAnalysis(anal);*/
        cardRepo.findByIdCard(idCard).get(0).addAnalysis(anal);
        return "main";
    }
    @PostMapping("/updateDev")
    public String updateDev(@RequestParam Integer idCard,@RequestParam Integer dev, Map<String, Object> model) {
        /*card.addDevelopment(dev);
        model.put("card", card);*/
        cardRepo.findByIdCard(idCard).get(0).addDevelopment(dev);
        return "main";
    }
    @PostMapping("/updateTest")
    public String updateTest(@RequestParam Integer idCard,@RequestParam Integer test, Map<String, Object> model) {

        cardRepo.findByIdCard(idCard).get(0).addTesting(test);
        return "main";
    }
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer idCard, Map<String, Object> model) {

        cardRepo.findByIdCard(idCard).get(0).nextCardStatus();
        return "main";
    }
    @PostMapping("/updateDateBeg")
    public String updateDateBeg(@RequestParam Integer idCard,@RequestParam Integer date, Map<String, Object> model) {

        cardRepo.findByIdCard(idCard).get(0).setDataBegSession(date);
        return "main";
    }
    @PostMapping("/updateDateEnd")
    public String updateDateEnd(@RequestParam Integer idCard,@RequestParam Integer date, Map<String, Object> model) {

        cardRepo.findByIdCard(idCard).get(0).setDataEndSession(date);
        return "main";
    }

    @PostMapping(path = "/allCard")
    public String delete() {
        cardRepo.deleteAll();
        return "main";
    }

    @PostMapping("filterCard")
    public String filter(@RequestParam Integer  filter, Map<String, Object> model) {
        Iterable<Card> card;

        if (filter != null && filter!=0) {
            card = cardRepo.findByIdCard(filter);
        } else {
            card = cardRepo.findAll();
        }

        model.put("card", card);

        return "main";
    }
}
