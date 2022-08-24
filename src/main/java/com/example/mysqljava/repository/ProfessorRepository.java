package com.example.mysqljava.repository;

import com.example.mysqljava.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String> {
    Iterable<Professor> findAllByDepartmentId(Long departmentId);
}
