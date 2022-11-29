package ru.job4j.dreamjob.model;

import java.time.LocalDate;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private LocalDate created;

    public Candidate(int id, String name, String description, LocalDate created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
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
}
