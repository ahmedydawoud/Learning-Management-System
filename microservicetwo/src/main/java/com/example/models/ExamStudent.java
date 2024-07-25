package com.example.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "examstudent")
public class ExamStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "date_id")
    private Long dateId;

    @ManyToOne
    @JoinColumn(name = "date_id", insertable = false, updatable = false)
    private AvailableDate availableDate;

    @Column(name = "grade")
    private int grade;

    public void setCenter(Center center) {
    }



}
