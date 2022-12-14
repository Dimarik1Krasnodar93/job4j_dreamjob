package ru.job4j.dreamjob.queries;

public class CandidateQueries {
    public static final String UPDATE = "UPDATE CANDIDATE set name = ?, description = ?, created = ?, "
            + "visible = ?, city_id = ?, photo = ? WHERE id = ?";
    public static final String ADD = "INSERT INTO CANDIDATE (name, description, created, visible, city_id, photo) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND = "SELECT * FROM CANDIDATE WHERE id = ?";
    public static final String FIND_ALL = "SELECT * FROM CANDIDATE";
}
