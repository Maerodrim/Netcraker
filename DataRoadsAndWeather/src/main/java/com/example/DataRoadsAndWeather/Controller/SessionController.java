package com.example.DataRoadsAndWeather.Controller;

import com.example.DataRoadsAndWeather.Model.Session;
import com.example.DataRoadsAndWeather.Model.Users;
import com.example.DataRoadsAndWeather.Repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("SessionController")
public class SessionController {
    @Autowired
    private SessionRepo sessionRepo;

    @GetMapping
    public String main(Map<String, Object> model) {

        Iterable<Session> cards = sessionRepo.findAll();

        model.put("Session", cards);

        return "main";
    }

    @PostMapping("addSession")
    public String addSession(@RequestParam String nameSession, @RequestParam Set<Users> users,
                             Map<String, Object> model) {
        Session session = new Session(nameSession, users);

        sessionRepo.save(session);

        return "main";
    }
    @PostMapping("addNullPackCard")
    public String addSession(@RequestParam Integer idSession,
                             Map<String, Object> model) {
            sessionRepo.findByIdSession(idSession).get(0).setCard(sessionRepo.findByIdSession(0).get(0).getCard());
            return "main";
    }

    @PostMapping(path = "/allSession")
    public String delete() {
        sessionRepo.deleteAll();
        return "main";
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

        return "main";
    }

    @PostMapping("getCard")
    public String getCard(@RequestParam Integer idSession, Map<String, Object> model) {

        return sessionRepo.findByIdSession(idSession).get(0).getCard().toString();
    }

    @PostMapping("getDate")
    public Integer getDate(@RequestParam Integer idSession, Map<String, Object> model) {

        return sessionRepo.findByIdSession(idSession).get(0).getDataSession();
    }

    @PostMapping("getAnalysis")
    public Integer getAnalysis(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getAnalysis();
    }

    @PostMapping("getDevelopment")
    public Integer getDevelopment(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getDevelopment();
    }

    @PostMapping("getTesting")
    public Integer getTesting(@RequestParam Integer idSession, Map<String, Object> model) {
        return sessionRepo.findByIdSession(idSession).get(0).getTesting();
    }

    @PostMapping("getTesting")
    public void setTesting(@RequestParam Integer idSession,@RequestParam Integer value, Map<String, Object> model) {
         sessionRepo.findByIdSession(idSession).get(0).setTesting(value);
    }
    @PostMapping("getAnalysis")
    public  void  setAnalysis(@RequestParam Integer idSession,@RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setAnalysis(value);
    }

    @PostMapping("getDevelopment")
    public  void  setDevelopment(@RequestParam Integer idSession,@RequestParam Integer value, Map<String, Object> model) {
        sessionRepo.findByIdSession(idSession).get(0).setDevelopment(value);
    }


}
