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
    private PostService postService = PostService.instanceOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        return postService.posts(model, postStore);
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        return postService.addPost(model);
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        return postService.createPost(post, postStore);
    }

    @GetMapping("/gotoView")
    public String gotoView(Model model) {
        return postService.gotoView(model);
    }
}
