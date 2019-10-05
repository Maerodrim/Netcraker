package com.example.DataRoadsAndWeather.resources;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Embeddable
@Entity
@Table(name = "Weather")
public class Weather implements Serializable {
    @Id
    @Column(name = "location")
    private String location;
    @Embedded
    private List<Values> valuesAndDate;

    public List<Values> getValuesAndDate() {
        return valuesAndDate;
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

    public void setValuesAndDate(List<Values> valuesAndDate) {
        this.valuesAndDate = valuesAndDate;
    }
}
