package ru.job4j.dreamjob.service;

import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;
import java.util.Collection;

public class PostService {

    private static final PostService INSTANCE = new PostService();
    private PostStore store = PostStore.instOf();

    public static PostService instanceOf() {
        return INSTANCE;
    }

    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
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
