package com.example.mysqljava.repository;

import com.example.mysqljava.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
}
