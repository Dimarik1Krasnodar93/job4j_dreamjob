package ru.job4j.dreamjob.queries;

public class UserQueries {
    public final static String FIND = "SELECT * FROM USERS WHERE email = ?";
    public final static String FIND_EMAIL_PASSWORD = "SELECT * FROM USERS WHERE email = ? and Password = ?";
    public final static String ADD = "INSERT INTO USERS (email, password) values (?, ?)";
}
