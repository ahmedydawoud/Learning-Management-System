package com.example.repos;

import com.example.models.AvailableDate;
import com.example.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    List<Exam> findByExamId(Long id);
}
