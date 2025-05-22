package com.devsenior.cdiaz.curso_restful.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {

    // Un identificador único (ID).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    // Nombre del curso.
    @Column(name = "name", nullable = false)
    private String name;

    // Código del curso (que idealmente debería ser único).
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    // Una descripción.
    @Column(name = "description")
    private String description;

    // Fecha de inicio.
    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    // Fecha de fin.
    @Column(name = "final_date")
    private LocalDate finalDate;

    // Número de créditos.
    @Column(name = "credit_number", nullable = false)
    private Integer credits;

}
