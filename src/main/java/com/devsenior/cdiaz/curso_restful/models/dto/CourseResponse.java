package com.devsenior.cdiaz.curso_restful.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Respuesta de la informacion completa de los cursos")
@Data
public class CourseResponse {

    @Schema(description = "Identificador unico del curso", example = "1")
    private Long courseId;

    @Schema(description = "Nombre del curso", example = "Programacion Basica")
    private String name;

    @Schema(description = "Código único del curso", example = "PROG101")
    private String code;

    @Schema(description = "Descripción detallada del curso", example = "Curso introductorio a la programación que cubre conceptos básicos y fundamentales")
    private String description;

    @Schema(description = "Fecha de inicio del curso", example = "2025-01-31")
    private String initDate;

    @Schema(description = "Fecha de finalización del curso", example = "2025-06-30")
    private String endDate;

    @Schema(description = "Número de créditos académicos del curso", example = "4")
    private Integer creditsNum;
}
