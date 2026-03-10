package com.imc.service;

import com.imc.model.BMIRecord;
import com.imc.model.User;
import com.imc.repository.BMIRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BMIService {

    private final BMIRecordRepository bmiRecordRepository;

    public BMIService(BMIRecordRepository bmiRecordRepository) {
        this.bmiRecordRepository = bmiRecordRepository;
    }

    public BMIRecord calculateBMI(User user, double weight) {

        if (weight <= 0) {
            throw new RuntimeException("Weight must be greater than 0");
        }

        double bmi = weight / (user.getHeight() * user.getHeight());

        BMIRecord record = new BMIRecord();

        record.setUser(user);
        record.setWeight(weight);
        record.setBmi(bmi);
        record.setDateRecorded(LocalDateTime.now());

        return bmiRecordRepository.save(record);
    }
    public java.util.List<BMIRecord> getHistory(User user) {
    return bmiRecordRepository.findByUser(user);
}
}