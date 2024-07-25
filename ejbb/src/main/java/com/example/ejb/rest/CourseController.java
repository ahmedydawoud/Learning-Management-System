package com.example.ejb.rest;

import com.example.ejb.ejb.CourseService;
import com.example.ejb.entity.Course;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseController {

    @Inject
    private CourseService courseService;

    @GET
    public Response getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return Response.ok(courses).build();
    }

    @GET
    @Path("/{id}")
    public Response getCourseById(@PathParam("id") Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return Response.ok(course).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Course with ID " + id + " not found").build();
        }
    }
    @POST
    public Response addCourse(Course course) {
        Course newCourse = courseService.saveCourse(course);
        return Response.status(Response.Status.CREATED).entity(newCourse).build();
    }

}
