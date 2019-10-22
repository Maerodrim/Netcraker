package com.example.DataRoadsAndWeather.Repo;


import com.example.DataRoadsAndWeather.Model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "Session")
public interface SessionRepo extends CrudRepository<Session, Long> {
    List<Session> findByIdSession(Integer IdSession);
    List<Session> findByNameSession(String nameSession);

}
