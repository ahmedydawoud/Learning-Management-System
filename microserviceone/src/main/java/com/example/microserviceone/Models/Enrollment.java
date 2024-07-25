package com.example.microserviceone.Models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "status")
    private int status;

}
