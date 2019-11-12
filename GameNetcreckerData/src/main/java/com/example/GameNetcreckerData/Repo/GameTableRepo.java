package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.Enum.TableStatus;
import com.example.GameNetcreckerData.Model.GameTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "GameTable")
public interface GameTableRepo extends CrudRepository<GameTable, Long> {
    GameTable findByIdGameTable(Integer idGameTable);
    List<GameTable> findByStatus(TableStatus status);
}

