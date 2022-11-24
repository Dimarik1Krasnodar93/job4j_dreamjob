package ru.job4j.dreamjob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.reader.StreamReader;


import java.io.*;
import java.util.Properties;

@RestController
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        StringBuilder indexBuilder = new StringBuilder();
        final ClassLoader loader = IndexControl.class.getClassLoader();
        int read;
        try (InputStream io = loader.getResourceAsStream("templates/index.html")) {
            while ((read = io.read()) != -1) {
                indexBuilder.append((char) read);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return indexBuilder.toString();
    }
}
