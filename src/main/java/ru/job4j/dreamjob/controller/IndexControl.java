package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.optional.UserAdditional;
import ru.job4j.dreamjob.model.User;


import javax.servlet.http.HttpSession;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
          return "index";
    }
}
