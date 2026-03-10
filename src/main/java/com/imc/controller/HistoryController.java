package com.imc.controller;

import com.imc.model.BMIRecord;
import com.imc.model.User;
import com.imc.service.BMIService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {

    private final BMIService bmiService;

    public HistoryController(BMIService bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping("/history")
    public String viewHistory(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        List<BMIRecord> history = bmiService.getHistory(user);

        model.addAttribute("history", history);

        return "history";
    }
}