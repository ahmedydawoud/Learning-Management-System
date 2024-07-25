package com.example.Service;

import com.example.models.AvailableDate;
import com.example.models.Center;
import com.example.models.Exam;
import com.example.models.ExamStudent;
import com.example.repos.CenterRepo;
import com.example.repos.ExamRepo;
import com.example.repos.AvailableDateRepo;
import com.example.repos.ExamStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private AvailableDateRepo availableDateRepo;

    @Autowired
    private ExamStudentRepo examStudentRepo;

    @Autowired
    private CenterRepo centerRepo;

    public Exam createExam(Exam exam) {
        Center center = centerRepo.findById(exam.getCenterId()).orElse(null);
        if (center == null) {
            return null;
        }
        exam.setCenter(center);
        return examRepo.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }

    public Exam addAvailableDatesToExam(Long examId, List<String> dates) {
        Exam exam = examRepo.findById(examId).orElse(null);
        if (exam == null) {
            return null;
        }
        List<AvailableDate> availableDates = new ArrayList<>();
        for (String date : dates) {
            AvailableDate availableDate = new AvailableDate();
            availableDate.setDate(date);
            availableDate.setExam(exam);
            availableDateRepo.save(availableDate);
            availableDates.add(availableDate);
        }
        exam.setAvailableDates(availableDates);
        return examRepo.save(exam);
    }

    public ExamStudent applyForExam(Long studentId, Long dateId, String centerName) {
        AvailableDate availableDate = availableDateRepo.findById(dateId).orElse(null);
        if (availableDate == null) {
            return null;
        }
        Center center = centerRepo.findByName(centerName);
        if (center == null) {
            return null;
        }

        ExamStudent examStudent = new ExamStudent();
        examStudent.setStudentId(studentId);
        examStudent.setDateId(dateId);
        examStudent.setCenter(center);
        examStudent.setGrade(0);
        return examStudentRepo.save(examStudent);
    }

    public ExamStudent setGradeForExamStudent(Long examStudentId, int grade) {
        Optional<ExamStudent> examStudentOptional = examStudentRepo.findById(examStudentId);
        if (!examStudentOptional.isPresent()) {
            return null;
        }
        ExamStudent examStudent = examStudentOptional.get();
        examStudent.setGrade(grade);
        return examStudentRepo.save(examStudent);
    }

    public List<Exam> getAllExamsForRepresentative(Long representativeId) {
        return examRepo.findByRepresentativeId(representativeId);
    }
    public List<ExamStudent> getGradesByStudentId(Long studentId) { // for student and representative
        return examStudentRepo.findByStudentId(studentId);
    }
    public List<ExamStudent> getExamStudentsByRepresentative(Long representativeId) {
        return examStudentRepo.findByRepresentativeIdWithDetails(representativeId);
    }
    public Optional<ExamStudent> getExamHistory(Long dateid) { // for representative
        return examStudentRepo.findById(dateid);
    }


}