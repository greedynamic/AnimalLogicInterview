package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {

    private static final String template = "Markov chain, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/converter")
    public Converter converter(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Converter(counter.incrementAndGet(), String.format("markov"), String.format(template, name));
    }
}