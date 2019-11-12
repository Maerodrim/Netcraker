package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.Cube;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Set;


@Repository
@Table(name = "Cube")
public interface CubeRepo extends CrudRepository<Cube, Long> {
    Cube findByDayAndIdGameTable(Integer day,Integer idGameSession);
   Set<Cube> findByIdGameTable(Integer idGameSession);
}
