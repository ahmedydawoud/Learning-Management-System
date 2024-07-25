package com.example.microserviceone.Repositories;
import com.example.microserviceone.Models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByStudentId(Long studentId);

    @Query(value = "SELECT e.* FROM enrollment e JOIN course c ON e.course_id = c.id WHERE c.instructor_id = :instructorId", nativeQuery = true)
    List<Enrollment> findByInstructorId(@Param("instructorId") Long instructorId);
}
