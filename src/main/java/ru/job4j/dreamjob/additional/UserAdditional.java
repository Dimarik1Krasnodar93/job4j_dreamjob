package ru.job4j.dreamjob.additional;

import ru.job4j.dreamjob.model.User;

import javax.servlet.http.HttpSession;

public class UserAdditional {
    public static User getFromHtthSession(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User(0, "", "");
            user.setName("Гость");
        }
        return user;
    }
}
