package com.devsenior.cdiaz.curso_restful.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la creación y actualización de cursos")
public class CourseRequest {
    
    @Schema(
        description = "Nombre del curso",
        example = "Programación Java Avanzada",
        required = true
    )
    @NotBlank(message = "El campo 'name' es obligatorio")
    @Size(min = 3, message = "El campo name debe tener minimo 3 caracteres")
    private String name;

    @Schema(
        description = "Código único del curso",
        example = "JAVA101",
        required = true,
        minLength = 5,
        maxLength = 7
    )
    @NotBlank(message = "El campo 'code' es obligatorio")
    @Size(min = 5, max = 7)
    private String code;

    @Schema(
        description = "Descripción detallada del curso",
        example = "Curso avanzado de programación en Java con Spring Boot",
        required = false
    )
    private String description;

    @Schema(
        description = "Fecha de inicio del curso en formato yyyy-mm-dd",
        example = "2024-03-01",
        required = true,
        pattern = "^\\d{4}-\\d{2}-\\d{2}$"
    )
    @NotNull(message = "El campo 'initDate' es obligatorio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de fecha debe ser yyyy-mm-dd")
    private String initDate;

    @Schema(
        description = "Fecha de finalización del curso en formato yyyy-mm-dd",
        example = "2024-06-30",
        required = true,
        pattern = "^\\d{4}-\\d{2}-\\d{2}$"
    )
    @NotNull(message = "El campo 'endDate' es obligatorio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de fecha debe ser yyyy-mm-dd")
    private String endDate;

    @Schema(
        description = "Número de créditos del curso",
        example = "3",
        required = true,
        minimum = "1",
        maximum = "5"
    )
    @NotNull(message = "El campo 'creditsNum' es obligatorio")
    @Min(1)
    @Max(5)
    private Integer creditsNum;

    @Schema(
        description = "Indica si el curso está activo",
        example = "true",
        required = false,
        defaultValue = "true"
    )
    private Boolean active;
}
