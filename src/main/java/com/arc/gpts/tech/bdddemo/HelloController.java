package com.arc.gpts.tech.bdddemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    public Greeting hello(@RequestParam(defaultValue = "World") String name) {
        return new Greeting("Bonjour " + name);
    }

    public record Greeting(String message) {
    }

}
