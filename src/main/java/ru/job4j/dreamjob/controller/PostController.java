package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.store.PostStore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Controller
public class PostController {

    private PostService postService = PostService.instanceOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.getStore().findAll());
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
        postService.getStore().addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/gotoView")
    public String gotoView(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", postService.getStore().findById(id));
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        postService.getStore().updatePost(post);
        return "redirect:/posts";
    }
}

