package com.imc.repository;

import com.imc.model.BMIRecord;
import com.imc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BMIRecordRepository extends JpaRepository<BMIRecord, Long> {

    List<BMIRecord> findByUser(User user);

}