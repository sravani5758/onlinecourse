package com.example.demo.repo;

import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findAllByStudent(User student);

    Optional<Object> findByStudentIdAndCourseId(Integer id, Integer id1);
}
