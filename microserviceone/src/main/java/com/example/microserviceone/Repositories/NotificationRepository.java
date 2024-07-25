package com.example.microserviceone.Repositories;

import com.example.microserviceone.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStudentIdAndIsReadFalse(Long studentId);
}
