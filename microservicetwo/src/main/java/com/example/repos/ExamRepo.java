package com.example.repos;


import com.example.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam,Long> {
    Exam findByName(String name);
    List<Exam> findByRepresentativeId(Long representativeId);
}
