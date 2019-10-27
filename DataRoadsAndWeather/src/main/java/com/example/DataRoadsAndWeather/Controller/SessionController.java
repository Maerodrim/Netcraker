package com.example.DataRoadsAndWeather.Controller;

import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Session;
import com.example.DataRoadsAndWeather.Repo.CardRepo;
import com.example.DataRoadsAndWeather.Repo.SessionRepo;
import com.example.DataRoadsAndWeather.Repo.UsersRepo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("SessionController")
@Log4j2
public class SessionController {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private UsersRepo usersRepo;

    public Integer addSession(@RequestParam String nameSession) {
        Session session = new Session(nameSession, 8, 3, 2, 2);

        sessionRepo.save(session);

        log.info("Сессия создана");
        return sessionRepo.findByNameSession(nameSession).get(0).getIdSession();
    }

    @PostMapping("addUsers")
    public String addUsers(@RequestParam Integer idSession, @RequestParam String email) {

        sessionRepo.findByIdSession(idSession).get(0).addUsers(usersRepo.findByEmail(email).get(0));
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        usersRepo.findByEmail(email).get(0).addSession(sessionRepo.findByIdSession(idSession).get(0));
        usersRepo.save(usersRepo.findByEmail(email).get(0));
        log.info("Юзер создан");
        return "Ok";
    }

    @PostMapping("addNullPackCard")
    public String addNullPackCard(@RequestParam Integer idSession) {
        for (int i = 0; i < 36; i++) {
            Card card = new Card(
                    cardRepo.findByIdCard(i).get(0).getNameCard(),
                    cardRepo.findByIdCard(i).get(0).getDataBegSession(),
                    cardRepo.findByIdCard(i).get(0).getDataEndSession(),
                    cardRepo.findByIdCard(i).get(0).getDevelopment(),
                    cardRepo.findByIdCard(i).get(0).getAllDevelopment(),
                    cardRepo.findByIdCard(i).get(0).getAnalysis(),
                    cardRepo.findByIdCard(i).get(0).getAllAnalysis(),
                    cardRepo.findByIdCard(i).get(0).getTesting(),
                    cardRepo.findByIdCard(i).get(0).getAllTesting(),
                    cardRepo.findByIdCard(i).get(0).getMoney(),
                    cardRepo.findByIdCard(i).get(0).getSubs(),
                    cardRepo.findByIdCard(i).get(0).getColorCard(),
                    cardRepo.findByIdCard(i).get(0).getStatus(),
                    cardRepo.findByIdCard(i).get(0).getPriority(),
                    idSession);
            sessionRepo.findByIdSession(idSession).get(0).addCard(card);
            cardRepo.save(card);
            sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        }
        log.info("Добавлен нулевой набор");
        return "Ok";
    }

    @PostMapping(path = "/allSession")
    public String delete() {
        sessionRepo.deleteAll();
        return "Ok";
    }

    @DeleteMapping(path = "DeleteSession")
    public String deleteSession(@RequestParam Integer idSession) {
        sessionRepo.delete(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @JsonView(View.SESSION.class)
    @GetMapping("getSession")
    public Session getSession(@RequestParam Integer idSession) {
        return sessionRepo.findByIdSession(idSession).get(0);
    }

    @JsonView(View.CARD.class)
    @GetMapping("getCard")
    public Set<Card> getCard(@RequestParam Integer idSession) {
        return sessionRepo.findByIdSession(idSession).get(0).getCard();
    }

    @GetMapping("getDate")
    public Integer getDate(@RequestParam Integer idSession) {

        return sessionRepo.findByIdSession(idSession).get(0).getDataSession();
    }

    @GetMapping("getAnalysis")
    public Integer getAnalysis(@RequestParam Integer idSession) {
        return sessionRepo.findByIdSession(idSession).get(0).getAnal();
    }

    @GetMapping("getDevelopment")
    public Integer getDevelopment(@RequestParam Integer idSession) {
        return sessionRepo.findByIdSession(idSession).get(0).getDev();
    }

    @GetMapping("getTesting")
    public Integer getTesting(@RequestParam Integer idSession) {
        return sessionRepo.findByIdSession(idSession).get(0).getTest();
    }

    @PostMapping("setTesting")
    public String setTesting(@RequestParam Integer idSession, @RequestParam Integer value) {
        sessionRepo.findByIdSession(idSession).get(0).setTest(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("setAnalysis")
    public String setAnalysis(@RequestParam Integer idSession, @RequestParam Integer value) {
        sessionRepo.findByIdSession(idSession).get(0).setAnal(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("setDevelopment")
    public String setDevelopment(@RequestParam Integer idSession, @RequestParam Integer value) {
        sessionRepo.findByIdSession(idSession).get(0).setDev(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }

    @PostMapping("setData")
    public String setData(@RequestParam Integer idSession, @RequestParam Integer value) {
        sessionRepo.findByIdSession(idSession).get(0).setDataSession(value);
        sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return "Ok";
    }
}
