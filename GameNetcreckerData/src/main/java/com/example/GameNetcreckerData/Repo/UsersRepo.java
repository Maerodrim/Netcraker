package com.example.GameNetcreckerData.Repo;

import com.example.GameNetcreckerData.Model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<Users, Long> {
    List<Users> findByEmail(String email);
}
