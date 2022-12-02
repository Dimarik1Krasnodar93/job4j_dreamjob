package ru.job4j.dreamjob.model;

import java.time.LocalDate;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private City city;
    private byte[] photo;
    private boolean visible;

    public Candidate(int id, String name, String description, LocalDate created, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
