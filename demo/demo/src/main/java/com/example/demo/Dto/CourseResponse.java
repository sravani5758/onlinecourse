package com.example.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Integer id;
    private String title;
    private String description;
    private Double price;

}
