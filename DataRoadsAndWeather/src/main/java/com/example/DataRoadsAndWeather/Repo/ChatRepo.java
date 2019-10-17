package com.example.DataRoadsAndWeather.Repo;



import com.example.DataRoadsAndWeather.Model.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepo extends CrudRepository<Chat, Long> {
}
