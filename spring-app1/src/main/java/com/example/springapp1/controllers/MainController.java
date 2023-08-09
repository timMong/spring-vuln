package com.example.springapp1.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.unbescape.html.HtmlEscape;

import javax.naming.Binding;
import java.io.IOException;

import static org.unbescape.html.HtmlEscape.escapeHtml4;


@Controller
public class MainController {
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("title", "Main page");
        return "login";
    }

    @GetMapping("/home")
    public String homeGet(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @PostMapping ("/home")
    public String homePost(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        if (login.equals("admin") && password.equals("admin"))
            return "redirect:/home";
        else return "redirect:/";
    }

    @PostMapping ("/search")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("output", search);
        return "search";
    }


    @GetMapping("/test")
    public void displayInput(@RequestParam String userInput, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        //  Если не использовать метод экранирования "escapeHtml4()" и оставить просто переменную, то страница будет уязвима для XSS
        String htmlContent = "<html><body><h2>User Input:</h2><p>" + escapeHtml4(userInput) + "</p></body></html>";
        response.getWriter().write(htmlContent);
    }
}
