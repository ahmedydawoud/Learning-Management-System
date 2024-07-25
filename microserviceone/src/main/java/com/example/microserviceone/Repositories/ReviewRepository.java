package com.example.microserviceone.Repositories;

import com.example.microserviceone.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
