package com.example.DataRoadsAndWeather.Repo;

import com.example.DataRoadsAndWeather.Model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
@Repository
@Table(name = "Card")
public interface CardRepo extends CrudRepository<Card, Long> {
    List<Card> findByIdCard(Integer IdCard);
    List<Card> findAllByIdCardNotNull();
}
