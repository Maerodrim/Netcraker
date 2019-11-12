package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.GameTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "GameTable")
public interface GameTableRepo extends CrudRepository<GameTable, Long> {
    GameTable findByIdGameTable(Integer idGameTable);
}

