package ru.job4j.dreamjob.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created = LocalDateTime.now();
    private City city;
    private byte[] photo;
    private boolean visible;

    public Candidate(int id, String name, String description, LocalDateTime created, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.city = city;
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name, byte[] photo) {
        this.id = id;
        this.name = name;
        this.photo = photo.clone();
    }

    public Candidate(int id, String name, String description, LocalDateTime created, City city, byte[] photo, boolean visible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.city = city;
        this.photo = photo;
        this.visible = visible;
    }

    public Candidate() {

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

    public LocalDateTime getCreated() {
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

    public boolean getVisible() {
        return visible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
