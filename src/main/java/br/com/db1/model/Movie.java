package br.com.db1.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private Integer id;
    private String name;
    private String dateHour;

    protected Movie() {
    }

    public Movie(String name, String dateHour) {
        this.name = name;
        this.dateHour = dateHour;
    }

    public Movie(Integer id, String name, String dateHour) {
        this.id = id;
        this.name = name;
        this.dateHour = dateHour;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateHour() {
        return dateHour;
    }
}
