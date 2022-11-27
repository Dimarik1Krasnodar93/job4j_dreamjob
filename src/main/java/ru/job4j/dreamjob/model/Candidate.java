package ru.job4j.dreamjob.model;

import java.time.LocalDate;

public class Candidate {
    private int id;
    private String name;
    private String desc;
    private LocalDate created;

    public Candidate(int id, String name, String desc, LocalDate created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDate getCreated() {
        return created;
    }
}
