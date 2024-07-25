package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "center")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "location")
    private String location;
    @Column(name = "bio")
    private String bio;
    @Column(name = "representative_id")
    private Long representativeId;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    private List<Branches> Branches;

    @JsonIgnore
    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    private List<Exam> exams;

}
