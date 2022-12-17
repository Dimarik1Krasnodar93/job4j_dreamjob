package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.util.UserAdditional;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class PostController {

    private final PostService postService;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("post", new Post(0, "Заполните название",
                "Заполните описание",
                LocalDateTime.now()));
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/gotoView")
    public String gotoView(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        return "redirect:/index";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.updatePost(post);
        return "redirect:/posts";
    }
}

