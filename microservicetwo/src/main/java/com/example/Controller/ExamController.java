package com.example.Controller;

import com.example.Service.ExamService;
import com.example.models.Exam;
import com.example.models.ExamStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.OK);
    }


    @PostMapping("/{examId}/available-dates")
    public ResponseEntity<Exam> addAvailableDatesToExam(@PathVariable Long examId, @RequestBody List<String> dates) {
        Exam updatedExam = examService.addAvailableDatesToExam(examId, dates);
        if (updatedExam == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(updatedExam, HttpStatus.OK);
    }

    @PostMapping("/apply-exam")
    public ResponseEntity<ExamStudent> applyExam(@RequestBody Map<String, Object> requestBody) {
        Long studentId = Long.parseLong(requestBody.get("studentId").toString());
        Long dateId = Long.parseLong(requestBody.get("dateId").toString());
        String centerName = (String) requestBody.get("centerName");

        ExamStudent examStudent = examService.applyForExam(studentId, dateId, centerName);
        if (examStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(examStudent, HttpStatus.OK);
    }

    @PutMapping("/grade")
    public ResponseEntity<ExamStudent> setGradeForExamStudent(@RequestBody Map<String, Object> requestBody) {
        String examStudentIdStr = (String) requestBody.get("examStudentId");
        Long examStudentId = Long.parseLong(examStudentIdStr);
        int grade = Integer.parseInt((String) requestBody.get("grade"));

        ExamStudent updatedExamStudent = examService.setGradeForExamStudent(examStudentId, grade);
        if (updatedExamStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedExamStudent, HttpStatus.OK);
    }

    @GetMapping("/getallexams/{representativeId}")
    public List<Exam> getAllExams(@PathVariable Long representativeId) {
        return examService.getAllExamsForRepresentative(representativeId);
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }


    @GetMapping("/{studentId}")
    public List<ExamStudent> getGradesByStudentId(@PathVariable Long studentId) {
        return examService.getGradesByStudentId(studentId);
    }

    @GetMapping("/getexamhistory/{dateid}")
    public Optional<ExamStudent> getExamHistory(@PathVariable Long dateid) {
        return examService.getExamHistory(dateid);
    }

    @GetMapping("/examstudents/representative/{representativeId}")
    public List<ExamStudent> getExamStudentsByRepresentative(@PathVariable Long representativeId) {
        return examService.getExamStudentsByRepresentative(representativeId);
    }

}