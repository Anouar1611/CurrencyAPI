package de.check24.challenge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 */
@Controller
public class IndexController {

    /**
     * Index endpoint to show the index page
     *
     * @param model Spring's view model
     * @return view name
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "CHECK24 coding challenge");
        model.addAttribute("welcome", "Welcome to CHECK24");
        model.addAttribute("applicationTitle", "Currency Converter");
        return "index";
    }
}
