package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDBStore;
import ru.job4j.dreamjob.store.PostStore;
import java.util.Collection;

@Service
public class PostService {

    private final PostDBStore store;

    public PostService(PostDBStore store) {
        this.store = store;
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void updatePost(Post post) {
        store.updatePost(post);
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void addPost(Post post) {
        store.addPost(post);
    }
}
