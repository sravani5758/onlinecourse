package com.example.demo.Dto;


import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollementResponse {
    private String courseTitle;
    private String courseDescription;
    private Double price;
    private LocalDateTime enrolledAt;
}
