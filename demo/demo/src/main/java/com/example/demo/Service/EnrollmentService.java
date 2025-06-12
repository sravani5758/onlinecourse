package com.example.demo.Service;

import com.example.demo.Dto.EnrollementRequest;
import com.example.demo.Dto.EnrollementResponse;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.EnrollmentRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class EnrollmentService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    EnrollmentRepo enrollmentRepo;

    @Autowired
    CourseRepo courseRepo;


    public String enrollInCourse(EnrollementRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Id not found"));
        if (enrollmentRepo.findByStudentIdAndCourseId(student.getId(), course.getId()).isPresent()) {
            throw new RuntimeException("Already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .build();
        enrollmentRepo.save(enrollment);
        return "Enrollment Successful";



    }

    public List<EnrollementResponse> getMyEnrollments() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepo.findByEmail(email)
                .orElseThrow(() ->new RuntimeException("User not found"));


        List<Enrollment> enrollments = enrollmentRepo.findAllByStudent(student);

        return enrollmentRepo.findAllByStudent(student).stream()
                .map(this::convertToResponse)
                .toList();
    }

    private EnrollementResponse convertToResponse(Enrollment enrollment) {
        Course course = enrollment.getCourse();
        return new EnrollementResponse(
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                enrollment.getEnrolledAt()
        );
    }


    }

