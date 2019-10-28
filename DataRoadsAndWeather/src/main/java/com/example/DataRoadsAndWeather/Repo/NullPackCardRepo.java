package com.example.DataRoadsAndWeather.Repo;

import com.example.DataRoadsAndWeather.Model.NullPackCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NullPackCardRepo extends CrudRepository<NullPackCard, Long> {
        List<NullPackCard> findByIdCard(Integer IdCard);
        List<NullPackCard> findAllByIdCardNotNull();
        }
