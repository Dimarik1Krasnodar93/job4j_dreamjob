package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dreamjob.store.CandidateStore;

@Controller
public class CandidateController {

    private final CandidateStore candidateStore = CandidateStore.instanceOf();

    @GetMapping("/candidates")
    public String getCandidates(Model model) {
        model.addAttribute("candidates", candidateStore.findAll());
        return "candidates";
    }

}
