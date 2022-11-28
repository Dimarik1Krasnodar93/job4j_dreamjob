package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static AtomicInteger id = new AtomicInteger(0);
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "description 1", LocalDate.of(2022, 1, 1)));
        posts.put(2, new Post(2, "Middle Java Job", "description 1", LocalDate.of(2022, 10, 1)));
        posts.put(3, new Post(3, "Senior Java Job1", "description 1", LocalDate.of(2022, 9, 1)));
    }

    public static PostStore instOf() {
        return INST;
    }


    public void addPost(Post post) {
        posts.put(id.getAndAdd(1), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
