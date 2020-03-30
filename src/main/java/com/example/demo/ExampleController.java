package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getSiteContent")
public class ExampleController {

    @RequestMapping
    public String getSiteContent() {
        return "Типа сайт контент";
    }
}
