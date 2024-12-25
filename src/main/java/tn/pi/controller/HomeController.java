package tn.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // Define the base URL mapping
public class HomeController {

    @GetMapping("") // Handle GET requests to the root URL
    public String home() {
        return "index"; // This should map to index.html in src/main/resources/templates
    }
}
