package com.example.DataRoadsAndWeather.Service.Impl;

import com.example.DataRoadsAndWeather.Model.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CardService {

    Optional<Card> save(Card car);

    List<Card> saveAll(List<Card> cars);

    Optional<Card> update(Card car);

    Optional<Card> get(Long id);

    List<Card> getAll();

    Boolean deleteById(Long id);

    Boolean deleteAll();
}
