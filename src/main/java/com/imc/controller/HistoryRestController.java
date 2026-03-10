package com.imc.controller;

import com.imc.model.BMIRecord;
import com.imc.model.User;
import com.imc.service.BMIService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HistoryRestController {

    private final BMIService bmiService;

    public HistoryRestController(BMIService bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping("/history")
    public List<BMIRecord> getHistory(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        return bmiService.getHistory(user);
    }
}