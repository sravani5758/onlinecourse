package com.example.demo.Controller;


import com.example.demo.Dto.EnrollementRequest;
import com.example.demo.Dto.EnrollementResponse;
import com.example.demo.Service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollementController {
    private final EnrollmentService enrollmentService;
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public ResponseEntity<String> enroll(@RequestBody EnrollementRequest request) {
        return ResponseEntity.ok(enrollmentService.enrollInCourse(request));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/my-courses")
    public ResponseEntity<List<EnrollementResponse>> getMyEnrollments() {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments());
    }


}
