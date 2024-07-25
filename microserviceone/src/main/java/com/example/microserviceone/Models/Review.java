package com.example.microserviceone.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "text", length = 1024)
    private String text;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
