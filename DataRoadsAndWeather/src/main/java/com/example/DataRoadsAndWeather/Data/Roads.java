package com.example.DataRoadsAndWeather.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Roads")
public class Roads implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "locationRoad", unique = false, nullable = false)
    private String locationRoad;
    @Column(name = "dateRoad", unique = false, nullable = true)
    private String dateRoad;
    @Column(name = "valueRoad", unique = false, nullable = true)
    private Double valueRoad;
    public Roads(){}
    public Roads(String locationRoad, String dateRoad,Double valueRoad){
        this.locationRoad=locationRoad;
        this.dateRoad=dateRoad;
        this.valueRoad=valueRoad;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return dateRoad;
    }

    public void setDate(String date) {
        this.dateRoad = date;
    }

    public void setValue(Double value) {
        this.valueRoad = value;
    }

    public Double getValue() {
        return valueRoad;
    }
    public String getLocation() {
        return locationRoad;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setLocation(String location) {
        this.locationRoad = location;
    }


}
