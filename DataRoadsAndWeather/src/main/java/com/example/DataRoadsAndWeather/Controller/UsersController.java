package com.example.DataRoadsAndWeather.Controller;


import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Users;
import com.example.DataRoadsAndWeather.Repo.UsersRepo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("UsersController")
@Log4j2
public class UsersController {
    @Autowired
    private UsersRepo usersRepo;

    @PostMapping("/addUsers")
    public String addUsers(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                           @RequestParam String role) {
        Users user = new Users(name, email, password, role);

        usersRepo.save(user);

        return "Ok";
    }

    @GetMapping("/getPassword")
    public Boolean getPassword(@RequestParam String email, @RequestParam String password) {

        return usersRepo.findByEmail(email).get(0).getPassword().equals(password);
    }

    @GetMapping("/getRole")
    public String getRole(@RequestParam String email) {

        return usersRepo.findByEmail(email).get(0).getRole();
    }

    @JsonView(View.SESSION.class)
    @GetMapping("/getSession")
    public Set getSession(@RequestParam String email) {

        return usersRepo.findByEmail(email).get(0).getSession();
    }

    @JsonView(View.USERS.class)
    @GetMapping("/getUsers")
    public Users getUsers(@RequestParam String email) {

        return usersRepo.findByEmail(email).get(0);
    }
}
