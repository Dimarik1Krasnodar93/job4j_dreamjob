package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dreamjob.store.PostStore;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class PostController {
    private final PostStore postStore = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        StringBuilder stringBuilder = new StringBuilder();
        final ClassLoader loader = IndexControl.class.getClassLoader();
        int read;
        try (InputStream io = loader.getResourceAsStream("templates/posts.html")) {
            while ((read = io.read()) != -1) {
                stringBuilder.append((char) read);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        var result = model.addAttribute("posts", postStore.findAll());
        stringBuilder.append(result);
        return stringBuilder.toString();
    }
}
