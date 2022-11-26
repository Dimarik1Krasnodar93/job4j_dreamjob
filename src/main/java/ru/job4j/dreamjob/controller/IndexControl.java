package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.reader.StreamReader;


import java.io.*;
import java.util.Properties;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index() {
          return "index";
    }
}
