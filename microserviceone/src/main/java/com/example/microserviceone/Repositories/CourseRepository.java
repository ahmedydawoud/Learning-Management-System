package com.example.microserviceone.Repositories;

import com.example.microserviceone.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByOrderByNumOfenrolledStudentsDesc();

    List<Course> findAllByOrderByRatingDesc();

    List<Course> findByNameContaining(String name);

    List<Course> findByCategoryContaining(String category);

    List<Course> findByInstructorId(Long instructorId);

    List<Course> findByInstructorIdOrderByRatingDesc(Long instructorId);
}
