package com.example.DataRoadsAndWeather.Repo;

import com.example.DataRoadsAndWeather.Model.Cube;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;


@Repository
@Table(name = "Cube")
public interface CubeRepo extends CrudRepository<Cube, Long> {
    Cube findByDay(Integer day);
}
