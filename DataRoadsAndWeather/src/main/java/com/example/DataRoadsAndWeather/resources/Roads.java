package com.example.DataRoadsAndWeather.resources;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Embeddable
@Entity
@Table(name = "Roads")
public class Roads implements Serializable {

    public Roads(){;}
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "location" )
    private String location;
    @Autowired
    private java.util.Date Date;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    public java.util.Date getDate() {

        return this.Date;
    }

    @Column(name = "value")
    private Double value;

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
