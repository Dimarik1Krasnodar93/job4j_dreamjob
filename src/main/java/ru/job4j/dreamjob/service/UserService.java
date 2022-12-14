package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserDBStore userDBStore;

    public UserService(UserDBStore userDBStore) {
        this.userDBStore = userDBStore;
    }

    public User findUserByEmail(String email) {
        return userDBStore.findUserByEmail(email).orElseThrow(() -> new NoSuchElementException());
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return userDBStore.findUserByEmailAndPassword(email, password);
    }

    public Optional<User> add(User user) {
        return userDBStore.addUser(user);
    }
}
