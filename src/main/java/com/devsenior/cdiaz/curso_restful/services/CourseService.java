package com.devsenior.cdiaz.curso_restful.services;

import java.util.List;

import com.devsenior.cdiaz.curso_restful.models.dto.CourseRequest;
import com.devsenior.cdiaz.curso_restful.models.dto.CourseResponse;

public interface CourseService {
    List<CourseResponse> getAll();

    CourseResponse getOne(Long id);

    List<CourseResponse> getByName(String name);

    CourseResponse create(CourseRequest course);

    CourseResponse update(Long id, CourseRequest course);

    void delete(Long id);
}
