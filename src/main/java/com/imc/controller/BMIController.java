package com.imc.controller;

import com.imc.model.BMIRecord;
import com.imc.model.User;
import com.imc.service.BMIService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BMIController {

    private final BMIService bmiService;

    public BMIController(BMIService bmiService) {
        this.bmiService = bmiService;
    }

    @PostMapping("/calculate")
    public String calculateBMI(
            @RequestParam("weight") double weight,
            HttpSession session,
            org.springframework.ui.Model model) {

        User user = (User) session.getAttribute("user");

        BMIRecord record = bmiService.calculateBMI(user, weight);

        model.addAttribute("bmi", record.getBmi());

        return "result";
    }
}