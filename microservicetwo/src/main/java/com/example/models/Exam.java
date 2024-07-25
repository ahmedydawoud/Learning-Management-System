package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "grade")
    private int grade;

    @Column(name = "representative_id")
    private Long representativeId;

    @Column(name = "center_id")
    private Long centerId;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<AvailableDate> availableDates;

    @ManyToOne
    @JoinColumn(name = "center_id", insertable = false, updatable = false)
    private Center center;
}



