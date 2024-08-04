package nk.projects.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
    
    @GetMapping
    public String sayWelcome(){
        return "homepage";
    }
}
