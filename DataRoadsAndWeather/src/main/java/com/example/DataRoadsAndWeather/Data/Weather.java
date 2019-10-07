package com.example.DataRoadsAndWeather.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Weather")
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "locationWeather", unique = false, nullable = true)
    private String locationWeather;
    @Column(name = "dateWeather", unique = false, nullable = true)
    private String dateWeather;
    @Column(name = "valueWind", unique = false, nullable = true)
    private Double valueWind;
    @Column(name = "valueRain", unique = false, nullable = true)
    private Double valueRain;
    @Column(name = "valueOvercast", unique = false, nullable = true)
    private Double valueOvercast;
    @Column(name = "valueTemp", unique = false, nullable = true)
    private Double valueTemp;

    public Weather(){}

    public Weather(String locationWeather, String dateWeather, Double valueWind, Double valueRain, Double valueOvercast, Double valueTemp) {
        this.locationWeather = locationWeather;
        this.dateWeather = dateWeather;
        this.valueWind = valueWind;
        this.valueRain = valueRain;
        this.valueOvercast = valueOvercast;
        this.valueTemp = valueTemp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationWeather() {
        return locationWeather;
    }

    public void setLocationWeather(String locationWeather) {
        this.locationWeather = locationWeather;
    }

    public String getDateWeather() {
        return dateWeather;
    }

    public void setDateWeather(String dateWeather) {
        this.dateWeather = dateWeather;
    }

    public Double getValueWind() {
        return valueWind;
    }

    public void setValueWind(Double valueWind) {
        this.valueWind = valueWind;
    }

    public Double getValueRain() {
        return valueRain;
    }

    public void setValueRain(Double valueRain) {
        this.valueRain = valueRain;
    }

    public Double getValueOvercast() {
        return valueOvercast;
    }

    public void setValueOvercast(Double valueOvercast) {
        this.valueOvercast = valueOvercast;
    }

    public Double getValueTemp() {
        return valueTemp;
    }

    public void setValueTemp(Double valueTemp) {
        this.valueTemp = valueTemp;
    }
}
