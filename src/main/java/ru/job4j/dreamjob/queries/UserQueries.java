package ru.job4j.dreamjob.queries;

public class UserQueries {
    public final static String FIND = "SELECT * FROM USERS WHERE email = ?";
    public final static String ADD = "INSERT INTO USERS (email, password) values (?, ?)";
}
