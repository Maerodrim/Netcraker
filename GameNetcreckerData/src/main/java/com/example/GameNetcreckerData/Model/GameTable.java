package com.example.GameNetcreckerData.Model;


import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.ColorCard;
import com.example.GameNetcreckerData.Model.Enum.TableStatus;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GameTable")
public class GameTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.GAMETABLE.class)
    private Integer idGameTable;
    @Column(name = "Day", unique = false, nullable = true)
    @JsonView(View.GAMETABLE.class)
    private Integer day;
    @Column(name = "nameGameTable", unique = false, nullable = true)
    @JsonView(View.GAMETABLE.class)
    private String nameGameTable;
    @OneToMany
    @Column(name = "Users", unique = false, nullable = true)
    @JsonView(View.USERS.class)
    private Set<Users> user;
    @OneToMany
    @Column(name = "Cube", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Set<Cube> cube;
    @Column(name = "TableStatus", unique = false, nullable = true)
    @JsonView(View.GAMETABLE.class)
    private TableStatus status;
    @Column(name = "NumberOfPlayers", unique = false, nullable = true)
    @JsonView(View.GAMETABLE.class)
    private Integer NumberOfPlayers;

    public GameTable() {
    }

    public GameTable(String nameGameTable, Integer numberOfPlayers) {
        this.nameGameTable = nameGameTable;
        this.NumberOfPlayers = numberOfPlayers;
        this.status = TableStatus.Start;
        this.day = 8;
    }

    public Integer getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        NumberOfPlayers = numberOfPlayers;
    }

    public Set<Cube> getCube() {
        return cube;
    }

    public void setCube(Set<Cube> cube) {
        this.cube = cube;
    }

    public void addCube(Cube cube) {
        this.cube.add(cube);
    }

    public void removeCube(Cube cube) {
        this.cube.remove(cube);
    }

    public void addUsers(Users user) {
        this.user.add(user);
    }

    public void removeUsers(Users user) {
        this.user.remove(user);
    }

    public Integer getIdGameTable() {
        return idGameTable;
    }

    public String getNameGameTable() {
        return nameGameTable;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setIdGameTable(Integer idGameTable) {
        this.idGameTable = idGameTable;
    }

    public void setNameGameTable(String nameGameTable) {
        this.nameGameTable = nameGameTable;
    }

    public void setUser(Set<Users> user) {
        this.user = user;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Set<Users> getUser() {
        return user;
    }
}
