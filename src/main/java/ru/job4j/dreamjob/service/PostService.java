package ru.job4j.dreamjob.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class PostService {

    private static PostService instance;
    private PostStore store = PostStore.instOf();

    public static PostService instanceOf() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public PostStore getStore() {
        return store;
    }

    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void addPost(Post post) {
        store.addPost(post);
    }
}
