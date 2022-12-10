package ru.job4j.dreamjob.queries;

public class CandidateQueries {
    public static final String UPDATE = "UPDATE CANDIDATE set name = ?, description = ?, created = ?, "
            + "visible = ?, id_city = ?, photo = ? WHERE id = ?";
    public static final String ADD = "INSERT INTO CANDIDATE (name, description, created, visible, id_city, photo) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND = "SELECT * FROM CANDIDATE WHERE id = ?";
    public static final String FIND_ALL = "SELECT * FROM CANDIDATE";
}
