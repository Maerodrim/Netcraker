package com.example.GameNetcreckerData.Repo;


import com.example.GameNetcreckerData.Model.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "Events")
public interface EventsRepo extends CrudRepository<Events, Long> {
    Events findByDay(Integer day);
}
