package com.example.microserviceone.Controllers;
import com.example.microserviceone.Models.Enrollment;
import com.example.microserviceone.Models.Notification;
import com.example.microserviceone.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/add")
    public Enrollment addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.addEnrollment(enrollment);
    }

    @GetMapping("/all")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }

    @PutMapping("/accept/{id}")
    public Enrollment acceptEnrollment(@PathVariable Long id) {
        return enrollmentService.acceptEnrollment(id);
    }

    @PutMapping("/reject/{id}")
    public Enrollment rejectEnrollment(@PathVariable Long id) {
        return enrollmentService.rejectEnrollment(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    @PutMapping("/cancel/{enrollmentId}")
    public Enrollment cancelEnrollment(@PathVariable Long enrollmentId) {
        return enrollmentService.cancelEnrollment(enrollmentId);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Enrollment> getEnrollmentsByInstructorId(@PathVariable Long instructorId) {
        return enrollmentService.getEnrollmentsByInstructorId(instructorId);
    }

    @PutMapping("/read/{id}")
    public Notification setNotificationAsRead(@PathVariable Long id) {
        return enrollmentService.setNotificationAsRead(id);
    }

    @GetMapping("/unread/{studentId}")
    public List<Notification> getUnreadNotificationsByStudentId(@PathVariable Long studentId) {
        return enrollmentService.getUnreadNotificationsByStudentId(studentId);
    }

}
