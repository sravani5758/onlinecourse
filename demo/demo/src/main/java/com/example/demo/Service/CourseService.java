package com.example.demo.Service;


import com.example.demo.Dto.CourseRequest;
import com.example.demo.Dto.CourseResponse;
import com.example.demo.entity.Course;
import com.example.demo.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;

    public CourseResponse createCourse(CourseRequest request) {

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        courseRepo.save(course);
        return mapToResponse(course);
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(course.getId(),course.getTitle(),course.getDescription(),course.getPrice());
    }


    public CourseResponse updateCourse(Integer id, CourseRequest request) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());

        courseRepo.save(course);
        return mapToResponse(course);
    }

    public void deleteCourse(Integer id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        courseRepo.delete(course);
    }

    public List<Course> getAllCourses() {
        List<Course> courses = courseRepo.findAll();
        return courses;

    }
}
