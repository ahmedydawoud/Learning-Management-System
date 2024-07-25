package com.example.repos;

import com.example.models.ExamStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamStudentRepo extends JpaRepository<ExamStudent, Long> {
    List<ExamStudent> findByStudentId(Long studentId);

    @Query("SELECT es FROM ExamStudent es " +
            "JOIN AvailableDate ad ON es.dateId = ad.id " +
            "JOIN Exam e ON ad.exam.id = e.id " +
            "JOIN Center c ON e.center.id = c.id " +
            "WHERE c.representativeId = ?1")
    List<ExamStudent> findAllByRepresentativeId(Long representativeId);


    @Query("SELECT es FROM ExamStudent es " +
            "JOIN AvailableDate ad ON es.dateId = ad.id " +
            "JOIN Exam e ON ad.exam.id = e.id " +
            "JOIN Center c ON e.center.id = c.id " +
            "WHERE c.representativeId = ?1")
    List<ExamStudent> findByRepresentativeIdWithDetails(Long representativeId);

}

