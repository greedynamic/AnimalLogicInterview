package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {

    private static final String testData = "The theremin is there, right? Yes, it is.";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/converter")
    public Converter converter(@RequestParam(value = "testStr", defaultValue = "the") String testStr) {
        return new Converter(counter.incrementAndGet(), "Markov", testData, testStr);
    }
}