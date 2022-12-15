package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private static AtomicInteger id = new AtomicInteger(3);
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "description 1",
                LocalDateTime.of(2022, 1, 1, 0, 0, 0), new City(1, "Moscow")));
        posts.put(2, new Post(2, "Middle Java Job", "description 2",
                LocalDateTime.of(2022, 10, 1, 0, 0, 0), new City(1, "Moscow")));
        posts.put(3, new Post(3, "Senior Java Job1", "description 3",
                LocalDateTime.of(2022, 9, 1, 0, 0, 0), new City(1, "Moscow")));
    }

    public void addPost(Post post) {
        post.setId(id.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void updatePost(Post post) {
        posts.put(post.getId(), post);
    }

}
