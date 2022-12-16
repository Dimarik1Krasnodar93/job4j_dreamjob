package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.optional.UserAdditional;
import ru.job4j.dreamjob.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formRegistration")
    public String formRegistration(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("user",  new User(0, "Enter email with @", "enter password"));
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "/fail";
        }
        return "/success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail,
                            HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        model.addAttribute("user", user);
        model.addAttribute("fail", fail != null);
        model.addAttribute("user",  new User(0, "Enter email with @", "enter password"));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        User user = UserAdditional.getFromHtthSession(httpSession);
        user.setName("Гость");
        model.addAttribute("user", user);
        return "redirect:/index";
    }
}
