package com.example.microserviceone.Services;
import com.example.microserviceone.Models.Content;
import com.example.microserviceone.Models.Course;
import com.example.microserviceone.Models.Review;
import com.example.microserviceone.Repositories.ContentRepository;
import com.example.microserviceone.Repositories.CourseRepository;
import com.example.microserviceone.Repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final ContentRepository contentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public CourseService(CourseRepository courseRepository, ContentRepository contentRepository) {
        this.courseRepository = courseRepository;
        this.contentRepository = contentRepository;
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
            course.setRating(0.0f);
            return courseRepository.save(course);
        }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
        course.setName(courseDetails.getName());
        return courseRepository.save(course);
    }

    @Transactional
    public Course addContentToCourse(Long courseId, Content content) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id " + courseId));
        content.setStatus(0);
        content.setCourse(course);
        Content savedContent = contentRepository.save(content);
        course.getContents().add(savedContent);
        return courseRepository.save(course);
    }
    @Transactional
    public void acceptContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found with id " + contentId));
        content.setStatus(1); // Setting status to 1 for accepted content
        contentRepository.save(content);
    }

    @Transactional
    public void deleteContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found with id " + contentId));
        content.setStatus(2); // Setting status to 2 for deleted content
        contentRepository.save(content);
    }

    public Review addReviewToCourse(Long courseId,Review review) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + courseId));
        review.setCourse(course);
        course.getReviews().add(review);
        updateCourseRating(course);

        Review rev = reviewRepository.save(review);
        courseRepository.save(course);

        return rev;
    }
    private void updateCourseRating(Course course) {
        List<Review> reviews = course.getReviews();
        if (reviews.isEmpty()) {
            course.setRating(0);
        } else {
            double average = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            course.setRating((float) average);
        }
    }
    public List<Course> getCoursesSortedByEnrollment() {
        return courseRepository.findAllByOrderByNumOfenrolledStudentsDesc();
    }

    public List<Course> getCoursesSortedByRating() {
        return courseRepository.findAllByOrderByRatingDesc();
    }

    public List<Course> searchCoursesByCategory(String category) {
        return courseRepository.findByCategoryContaining(category);
    }

    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContaining(name);
    }

    public List<Content> getPendingContentsOfCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + courseId));

        List<Content> pendingContents = contentRepository.findByCourseAndStatus(course, 0);
        return pendingContents;
    }
    public List<Course> getCoursesByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }
    public List<Course> getCoursesSortedByRatingForInstructor(Long instructorId) {
        return courseRepository.findByInstructorIdOrderByRatingDesc(instructorId);
    }
}

