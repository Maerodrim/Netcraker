package com.example.DataRoadsAndWeather.Repo;

import com.example.DataRoadsAndWeather.Model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<Users, Long> {
    List<Users> findByEmail(String email);
}
