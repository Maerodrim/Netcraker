package com.example.GameNetcreckerData.Model;


import com.example.GameNetcreckerData.Dto.View.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "Cube")
public class Cube {
    public Cube() {
    }

    public Cube(Integer day, Integer cube1, Integer cube2,
                Integer cube3, Integer cube4, Integer cube5,
                Integer cube6, Integer cube7, Integer cube8,
                Integer idGameTable) {
        this.day = day;
        this.cube1 = cube1;
        this.cube2 = cube2;
        this.cube3 = cube3;
        this.cube4 = cube4;
        this.cube5 = cube5;
        this.cube6 = cube6;
        this.cube7 = cube7;
        this.cube8 = cube8;
        this.idGameTable = idGameTable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCube;

    @Column(name = "day", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer day;

    @Column(name = "cuba1", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube1;

    @Column(name = "cuba2", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube2;

    @Column(name = "cuba3", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube3;

    @Column(name = "cuba4", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube4;

    @Column(name = "cuba5", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube5;

    @Column(name = "cuba6", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube6;

    @Column(name = "cuba7", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube7;

    @Column(name = "cuba8", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer cube8;

    @Column(name = "idGameTable", unique = false, nullable = true)
    @JsonView(View.CUBA.class)
    private Integer idGameTable;

    public Integer getIdGameTable() {
        return idGameTable;
    }

    public void setIdGameTable(Integer idGameTable) {
        this.idGameTable = idGameTable;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getCube1() {
        return cube1;
    }

    public void setCube1(Integer cube1) {
        this.cube1 = cube1;
    }

    public Integer getCube2() {
        return cube2;
    }

    public void setCube2(Integer cube2) {
        this.cube2 = cube2;
    }

    public Integer getCube3() {
        return cube3;
    }

    public void setCube3(Integer cube3) {
        this.cube3 = cube3;
    }

    public Integer getCube4() {
        return cube4;
    }

    public void setCube4(Integer cube4) {
        this.cube4 = cube4;
    }

    public Integer getCube5() {
        return cube5;
    }

    public void setCube5(Integer cube5) {
        this.cube5 = cube5;
    }

    public Integer getCube6() {
        return cube6;
    }

    public void setCube6(Integer cube6) {
        this.cube6 = cube6;
    }

    public Integer getCube7() {
        return cube7;
    }

    public void setCube7(Integer cube7) {
        this.cube7 = cube7;
    }

    public Integer getCube8() {
        return cube8;
    }

    public void setCube8(Integer cube8) {
        this.cube8 = cube8;
    }
}
