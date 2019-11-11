package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.Card;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
@Repository
@Table(name = "Card")
public interface CardRepo extends CrudRepository<Card, Long> {
    List<Card> findByIdCard(Integer IdCard);
    List<Card> findAllByIdCardNotNull();
    List<Card> findByStatusAndEmail(CardStatus cardStatus, String email);
}
