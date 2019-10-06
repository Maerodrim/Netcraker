package com.example.DataRoadsAndWeather.Interface;

import org.springframework.data.repository.CrudRepository;

import com.example.DataRoadsAndWeather.Data.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}
