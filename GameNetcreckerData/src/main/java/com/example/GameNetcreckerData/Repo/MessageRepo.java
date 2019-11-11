package com.example.GameNetcreckerData.Repo;


import com.example.GameNetcreckerData.Model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "Message")
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
