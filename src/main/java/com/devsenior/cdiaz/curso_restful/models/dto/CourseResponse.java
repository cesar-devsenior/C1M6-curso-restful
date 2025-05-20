package com.devsenior.cdiaz.curso_restful.models.dto;

import lombok.Data;

@Data
public class CourseResponse {

    private Long courseId;

    private String name;

    private String code;

    private String description;

    private String initDate;

    private String endDate;

    private Integer creditsNum;
}
