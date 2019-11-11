package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.NullPackCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NullPackCardRepo extends CrudRepository<NullPackCard, Long> {
        List<NullPackCard> findByIdCard(Integer IdCard);
        List<NullPackCard> findAllByIdCardNotNull();
        }
