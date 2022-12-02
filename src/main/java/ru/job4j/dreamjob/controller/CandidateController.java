package ru.job4j.dreamjob.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.service.CandidatesService;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.store.CandidateStore;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class CandidateController {

    private final CandidatesService candidatesService;
    private final CityService cityService;

    public CandidateController(CandidatesService candidatesService, CityService cityService) {
        this.candidatesService = candidatesService;
        this.cityService = cityService;
    }

    @GetMapping("/candidates")
    public String getCandidates(Model model) {
        model.addAttribute("candidates", candidatesService.getStore().findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate, @RequestParam("file") MultipartFile file) throws IOException {
        candidate.setPhoto(file.getBytes());
        candidatesService.getStore().addCandidate(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate) {
        candidatesService.getStore().updateCandidate(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/formUpdateCandidate/{candidateId}")
    public String formUpdateCandidate(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", candidatesService.getStore().findById(id));
        return "updateCandidate";
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("candidateId") Integer candidateId) {
        Candidate candidate = candidatesService.getById(candidateId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(candidate.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(candidate.getPhoto()));
    }


}
