package com.example.DataRoadsAndWeather.Controller;

import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Card;
import com.example.DataRoadsAndWeather.Model.Session;
import com.example.DataRoadsAndWeather.Model.Users;
import com.example.DataRoadsAndWeather.Repo.SessionRepo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("SessionController")
public class SessionController {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    ObjectMapper mapper = new ObjectMapper();
    @PostMapping("addSession")
    public Integer addSession(@RequestParam String nameSession, @RequestParam Set<Users> users,
                              Map<String, Object> model) {
        Session session = new Session(nameSession, users);

        sessionRepo.save(session);

        return sessionRepo.findByNameSession(nameSession).get(0).getIdSession();
    }

    @PostMapping("addNullPackCard")
    public String addNullPackSession(@RequestParam Integer idSession,
                                     Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setCard(sessionRepo.findByIdSession(0).get(0).getCard());
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

    @PostMapping("filterCard")
    public String filter(@RequestParam Integer filter, Map<String, Object> model) {
        Iterable<Session> card;

        if (filter != null && filter != 0) {
            card = sessionRepo.findByIdSession(filter);
        } else {
            card = sessionRepo.findAll();
        }

        model.put("card", card);

        return "Ok";
    }
    @JsonView(View.UI.class)
    @GetMapping("getCard")
    public Set<Card> getCard(@RequestParam Integer idSession, Map<String, Object> model) {

        return sessionRepo.findByIdSession(idSession).get(0).getCard();
    }
    @GetMapping("getDate")
    public Integer getDate(@RequestParam Integer idSession, Map<String, Object> model) {

        return sessionRepo.findByIdSession(idSession).get(0).getDataSession();
    }

    @GetMapping("getAnalysis")
    public Integer getAnalysis(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getAnalysis();
    }

    @GetMapping("getDevelopment")
    public Integer getDevelopment(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getDevelopment();
    }

    @GetMapping("getTesting")
    public Integer getTesting(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getTesting();
    }

    @PostMapping("setTesting")
    public void setTesting(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setTesting(value);
    }

    @PostMapping("setAnalysis")
    public Integer setAnalysis(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
       sessionRepo.findByIdSession(idSession).get(0).setAnalysis(value);
       sessionRepo.save(sessionRepo.findByIdSession(idSession).get(0));
        return sessionRepo.findByIdSession(idSession).get(0).getAnalysis();
    }

    @PostMapping("setDevelopment")
    public void setDevelopment(@RequestParam Integer idSession, @RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setDevelopment(value);
    }
}
