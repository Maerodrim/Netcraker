package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.GraphGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;


@Repository
@Table(name = "GraphGame")
public interface GraphGameRepo extends CrudRepository<GraphGame, Long> {
    GraphGame findByDayAndEmail(Integer day,String email);
    List<GraphGame> findByEmail(String email);
}
