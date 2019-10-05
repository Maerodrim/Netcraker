package com.example.DataRoadsAndWeather.resources;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class Values {
    @Autowired
    private java.util.Date Date;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    public java.util.Date getDate() {

        return this.Date;
    }

    @Column(name = "value")
    private Double value;
}
