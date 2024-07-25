package com.example.microserviceone.Models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notification")
public class Notification {

    public Notification() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "message", length = 1024)
    private String message;

    @Column(name = "is_read")
    private boolean isRead;

}
