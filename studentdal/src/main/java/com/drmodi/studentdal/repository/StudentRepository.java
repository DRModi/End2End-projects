package com.drmodi.studentdal.repository;

import org.springframework.data.repository.CrudRepository;

import com.drmodi.studentdal.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
