package com.example.mysqljava.repository;

import com.example.mysqljava.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Iterable<Employee> findAllByDepartmentId(Long departmentId);

    @Query(value = "SELECT * FROM employee ORDER BY city ASC", nativeQuery = true)
    Iterable<Employee> findAllOrderByCity();

    @Query(value = "SELECT * FROM employee ORDER BY name ASC", nativeQuery = true)
    Iterable<Employee> findAllOrderByName();

    @Query(value = "SELECT * FROM employee ORDER BY date_of_birth ASC", nativeQuery = true)
    Iterable<Employee> findAllOrderByDateOfBirth();

}
