package com.example.microserviceone.Repositories;
import com.example.microserviceone.Models.Content;
import com.example.microserviceone.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Long> {
    List<Content> findByCourseAndStatus(Course course, int status);
}
