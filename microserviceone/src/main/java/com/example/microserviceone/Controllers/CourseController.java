package com.example.microserviceone.Controllers;
import com.example.microserviceone.Models.Course;
import com.example.microserviceone.Models.Content;
import com.example.microserviceone.Models.Review;
import com.example.microserviceone.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course savedCourse = courseService.saveCourse(course);
            return ResponseEntity.ok(savedCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> courseOptional = courseService.getCourseById(id);
        if (courseOptional.isPresent()) {
            return ResponseEntity.ok(courseOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID " + id + " not found");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            Course updatedCourse = courseService.updateCourse(id, courseDetails);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{courseId}/addContent")
    public ResponseEntity<Course> addContentToCourse(@PathVariable Long courseId, @RequestBody Content content) {
        Course updatedCourse = courseService.addContentToCourse(courseId, content);
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("acceptContent/{contentId}")
    public ResponseEntity<Void> acceptContent(@PathVariable Long contentId) {
        courseService.acceptContent(contentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("deleteContent/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long contentId) {
        courseService.deleteContent(contentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{courseId}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long courseId, @RequestBody Review review) {
        Review savedReview = courseService.addReviewToCourse(courseId, review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/sortedByEnrollment")
    public ResponseEntity<List<Course>> getCoursesSortedByEnrollment() {
        List<Course> courses = courseService.getCoursesSortedByEnrollment();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/sortedByRating")
    public ResponseEntity<List<Course>> getCoursesSortedByRating() {
        List<Course> courses = courseService.getCoursesSortedByRating();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/searchname")
    public ResponseEntity<List<Course>> searchCoursesByName(@RequestParam String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/searchcategory")
    public ResponseEntity<List<Course>> searchCoursesByCategory(@RequestParam String category) {
        List<Course> courses = courseService.searchCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}/pendingContents")
    public ResponseEntity<List<Content>> getPendingContentsOfCourse(@PathVariable Long courseId) {
        List<Content> pendingContents = courseService.getPendingContentsOfCourse(courseId);
        return ResponseEntity.ok(pendingContents);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructorId(@PathVariable Long instructorId) {
        List<Course> courses = courseService.getCoursesByInstructorId(instructorId);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/instructor/{instructorId}/sortedByRating")
    public ResponseEntity<List<Course>> getCoursesSortedByRatingForInstructor(@PathVariable Long instructorId) {
        List<Course> courses = courseService.getCoursesSortedByRatingForInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }
}
