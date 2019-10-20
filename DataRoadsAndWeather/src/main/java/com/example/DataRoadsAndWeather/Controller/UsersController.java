package com.example.DataRoadsAndWeather.Controller;


import com.example.DataRoadsAndWeather.Model.Users;
import com.example.DataRoadsAndWeather.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("UsersController")
public class UsersController {
    @Autowired
    private UsersRepo usersRepo;

    @PostMapping("/add")
    public String add(@RequestParam String name,@RequestParam String email,@RequestParam String password,
                      @RequestParam String role, Map<String, Object> model) {
        Users user = new Users(name,email,password,role);

        usersRepo.save(user);

        model.put("users", user);

        return "url:/MessageController/main";
    }
    @PostMapping("/getPassword")
    public Boolean getPassword(@RequestParam String email,@RequestParam String password, Map<String, Object> model) {

        return usersRepo.findByEmail(email).get(0).getPassword().toString().equals(password);
    }
    @PostMapping("/getRole")
    public String getRole(@RequestParam String email, Map<String, Object> model) {

        return usersRepo.findByEmail(email).get(0).getRole();
    }
}
