package com.example.microserviceone.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private String duration;
    @Column(name = "category")
    private String category;
    @Column(name = "rating")
    private float rating;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "numOfenrolledStudents")
    private int numOfenrolledStudents;
    @Column(name = "instructor_id")
    private Long instructorId;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Content> contents;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
