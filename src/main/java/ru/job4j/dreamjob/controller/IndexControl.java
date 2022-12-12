package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.reader.StreamReader;
import ru.job4j.dreamjob.model.User;


import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User(0, "", "");
            user.setName("Гость");
        }
        model.addAttribute("user", user);
          return "index";
    }
}
