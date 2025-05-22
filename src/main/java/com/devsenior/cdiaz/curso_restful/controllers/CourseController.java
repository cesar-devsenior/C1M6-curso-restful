package com.devsenior.cdiaz.curso_restful.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.curso_restful.models.dto.CourseRequest;
import com.devsenior.cdiaz.curso_restful.models.dto.CourseResponse;
import com.devsenior.cdiaz.curso_restful.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Tag(name = "Cursos", description = "Endpoint para gestionar los cursos de la academia")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Listar todos los cursos", description = "Lista todos los cursos que existen en el sistema. Incluye los cursos activos e inactivos.")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = CourseResponse.class)))
    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAll();
    }

    @Operation(summary = "Obtener curso por ID", description = "Obtiene un curso específico basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado exitosamente",
                    content = @Content(mediaType = "application/json", 
                            schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "El body enviado no es válido", 
                    content = @Content(mediaType = "text/plain", 
                            schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado", 
                    content = @Content(mediaType = "text/plain", 
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping("{id}")
    public CourseResponse getCourseById(
            @Parameter(description = "ID del curso a buscar", required = true) 
            @PathVariable("id") Long id) {
        return courseService.getOne(id);
    }

    @Operation(summary = "Buscar cursos por nombre", description = "Busca cursos que coincidan con el nombre proporcionado")
    @ApiResponse(responseCode = "200", description = "Lista de cursos encontrados", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = CourseResponse.class)))
    @GetMapping("buscar")
    public List<CourseResponse> getCoursesByName(
            @Parameter(description = "Nombre del curso a buscar", required = true) 
            @RequestParam("nombre") String name) {
        return courseService.getByName(name);
    }

    @Operation(summary = "Crear nuevo curso", description = "Crea un nuevo curso en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Curso creado exitosamente",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos del curso inválidos")
    })
    @PostMapping
    public CourseResponse createCourse(
            @Parameter(description = "Datos del curso a crear", required = true) 
            @Valid @RequestBody CourseRequest course) {
        return courseService.create(course);
    }

    @Operation(summary = "Actualizar curso existente", description = "Actualiza los datos de un curso existente basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos del curso inválidos"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @PutMapping("{id}")
    public CourseResponse updateCourse(
            @Parameter(description = "ID del curso a actualizar", required = true) 
            @Valid @Min(1) @PathVariable("id") Long id,
            @Parameter(description = "Nuevos datos del curso", required = true) 
            @Valid @RequestBody CourseRequest course) {
        return courseService.update(id, course);
    }

    @Operation(summary = "Eliminar curso", description = "Elimina un curso existente basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("{id}")
    public void deleteCourse(
            @Parameter(description = "ID del curso a eliminar", required = true) 
            @PathVariable("id") Long id) {
        courseService.delete(id);
    }
}
