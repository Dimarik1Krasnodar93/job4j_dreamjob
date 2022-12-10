package ru.job4j.dreamjob.queries;

public class UserQueries {
    public final static String FIND = "SELECT * FROM USER WHERE email = ?";
    public final static String ADD = "INSERT INFO USER (email, password) values (?, ?)";
}
