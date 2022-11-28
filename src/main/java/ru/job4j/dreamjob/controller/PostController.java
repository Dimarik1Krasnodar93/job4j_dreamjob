package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.store.PostStore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Controller
public class PostController {
    private final PostStore postStore = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postStore.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "Заполните название",
                "Заполните описание",
                LocalDateTime.now().toLocalDate()));
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        postStore.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/gotoView")
    public String gotoView(Model model) {
        return "redirect:/index";
    }
}

