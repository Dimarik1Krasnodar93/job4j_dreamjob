package ru.job4j.dreamjob.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.time.LocalDateTime;

public class PostService {

    private static PostService instance;

    public static PostService instanceOf() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }


    public String posts(Model model, PostStore postStore) {
        model.addAttribute("posts", postStore.findAll());
        return "posts";
    }

    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "Заполните название",
                "Заполните описание",
                LocalDateTime.now().toLocalDate()));
        return "addPost";
    }

    public String createPost(Post post, PostStore postStore) {
        postStore.addPost(post);
        return "redirect:/posts";
    }

    public String gotoView(Model model) {
        return "redirect:/index";
    }
}
