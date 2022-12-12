package ru.job4j.dreamjob.queries;

public class PostQueries {
    public static final String UPDATE = "UPDATE POST set name = ?, description = ?, created = ?, "
            + "visible = ?, id_city = ? WHERE id = ?";
    public static final String ADD = "INSERT INTO POST (name, description, created, visible, id_city) VALUES (?, ?, ?, ?, ?)";
    public static final String FIND = "SELECT * FROM POST WHERE id = ?";
    public static final String FIND_ALL = "SELECT * FROM POST";
}
