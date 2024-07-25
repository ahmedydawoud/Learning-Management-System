package com.example.microserviceone.Services;

import com.example.microserviceone.Models.Course;
import com.example.microserviceone.Models.Enrollment;
import com.example.microserviceone.Models.Notification;
import com.example.microserviceone.Repositories.EnrollmentRepository;
import com.example.microserviceone.Repositories.CourseRepository;
import com.example.microserviceone.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment addEnrollment(Enrollment enrollment) {
        enrollment.setStatus(0);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);
        return enrollmentOptional.orElse(null);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public Enrollment acceptEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus(1);
        enrollmentRepository.save(enrollment);

        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        incrementNumOfEnrolledStudents(course.getId());

        createNotification(enrollment, "Your enrollment in " + course.getName() + " course has been accepted");

        return enrollment;
    }


    private void createNotification(Enrollment enrollment, String message) {
        Notification notification = new Notification();
        notification.setStudentId(enrollment.getStudentId());
        notification.setMessage(message);
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    private void incrementNumOfEnrolledStudents(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            int currentNumOfEnrolledStudents = course.getNumOfenrolledStudents();
            course.setNumOfenrolledStudents(currentNumOfEnrolledStudents + 1);
            courseRepository.save(course);
        }
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment rejectEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus(2);
        enrollmentRepository.save(enrollment);

        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        createNotification(enrollment, "Your enrollment in " + course.getName() + " course has been rejected");

        return enrollment;
    }

    public Enrollment cancelEnrollment(Long enrollmentId) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            enrollment.setStatus(3); // 3 for cancel
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }

    public List<Enrollment> getEnrollmentsByInstructorId(Long instructorId) {
        return enrollmentRepository.findByInstructorId(instructorId);
    }

    public Notification setNotificationAsRead(Long notificationId) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notification not found");
    }
    public List<Notification> getUnreadNotificationsByStudentId(Long studentId) {
        return notificationRepository.findByStudentIdAndIsReadFalse(studentId);
    }
}
