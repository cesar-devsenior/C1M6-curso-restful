package com.devsenior.cdiaz.curso_restful.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.curso_restful.models.dto.CourseRequest;
import com.devsenior.cdiaz.curso_restful.models.dto.CourseResponse;
import com.devsenior.cdiaz.curso_restful.models.entities.Course;
import com.devsenior.cdiaz.curso_restful.repositories.CourseRepository;
import com.devsenior.cdiaz.curso_restful.services.CourseService;
import com.devsenior.cdiaz.exceptions.CourseNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public List<CourseResponse> getAll() {
        // List<CourseResponse> responses = new ArrayList<>();
        // var courses = courseRepository.findAll();

        // for (var course : courses) {
        // var response = toResponse(course);
        // responses.add(response);
        // }

        // return responses;

        return courseRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CourseResponse getOne(Long id) {
        return courseRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new CourseNotFoundException("No se ha encontrado un curso"));
    }

    @Override
    public List<CourseResponse> getByName(String name) {
        return courseRepository.getAllByName('%'+name+'%').stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CourseResponse create(CourseRequest course) {
        

        var entity = toEntity(course);

        var newCourse = courseRepository.save(entity);

        return toResponse(newCourse);
    }

    @Override
    public CourseResponse update(Long id, CourseRequest course) {
        var entityOptional = courseRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new CourseNotFoundException("No se encontró el curso");
        }

        var entity = toEntity(course);
        entity.setId(entityOptional.get().getId());

        var updatedEntity = courseRepository.save(entity);

        return toResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponse toResponse(Course course) {
        var response = new CourseResponse();
        response.setCourseId(course.getId());
        response.setName(course.getName());
        response.setCode(course.getCode());
        response.setDescription(course.getDescription());
        response.setInitDate(course.getInitialDate().format(formatter));
        response.setEndDate(course.getFinalDate().format(formatter));
        response.setCreditsNum(course.getCredits());
        return response;
    }

    private Course toEntity(CourseRequest course) {
        var entity = new Course();
        entity.setName(course.getName());
        entity.setCode(course.getCode());
        entity.setDescription(course.getDescription());
        entity.setInitialDate(LocalDate.parse(course.getInitDate(), formatter));
        entity.setFinalDate(LocalDate.parse(course.getEndDate(), formatter));
        entity.setCredits(course.getCreditsNum());

        return entity;
    }
}
