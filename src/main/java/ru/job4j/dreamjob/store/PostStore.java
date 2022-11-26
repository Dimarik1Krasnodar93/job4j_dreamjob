package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "descriptopn 1", LocalDate.of(2022, 1, 1)));
        posts.put(2, new Post(2, "Middle Java Job", "descriptopn 1", LocalDate.of(2022, 10, 1)));
        posts.put(3, new Post(3, "Senior Java Job", "descriptopn 1", LocalDate.of(2022, 9, 1)));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}