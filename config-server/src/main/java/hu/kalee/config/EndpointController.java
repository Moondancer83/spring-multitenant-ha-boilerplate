package hu.kalee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {
    @Value("${spring.application.name}")
    String name;

    @RequestMapping("/")
    public String home() {
        return "Application name: " + name;
    }
}

