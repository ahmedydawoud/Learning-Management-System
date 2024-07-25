package com.example.ejb.ejb;

import com.example.ejb.entity.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless
public class CourseService {

    EntityManager em;

    public CourseService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<Course> getAllCourses() {
        return em.createQuery("SELECT c FROM course c", Course.class).getResultList();
    }

    public Course getCourseById(Long id) {
        return em.find(Course.class, id);
    }

    public Course saveCourse(Course course) {
        em.persist(course);
        return course;
    }
}
