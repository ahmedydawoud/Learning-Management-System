package com.example.microserviceone.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "material")
    String material;

    @Column(name = "status")
    int status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

}
