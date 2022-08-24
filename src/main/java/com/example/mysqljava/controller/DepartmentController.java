package com.example.mysqljava.controller;


import com.example.mysqljava.entity.Department;
import com.example.mysqljava.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping("/create")
    public Department addDepartment(@RequestParam String name, @RequestParam String description) {
        Department department = new Department();
        department.setDescription(description);
        department.setName(name);
        departmentRepository.save(department);
        return department;
    }

    @GetMapping("/getById")
    public Department getDepartment(@RequestParam String id) {
        Department department = departmentRepository.findById(id).orElse(new Department());
        return department;
    }

    @GetMapping("/all")
    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PutMapping("/update")
    public String updateDepartment(@RequestParam String id, @RequestParam String name, @RequestParam String description) {
        if (departmentRepository.existsById(id)) {
            Department department = departmentRepository.findById(id).get();
            if (!StringUtils.isEmpty(name)) {
                department.setName(name);
            }
            if (!StringUtils.isEmpty(description)) {
                department.setDescription(description);
            }
            departmentRepository.save(department);
            return "Successfully updated the department with id " + id + " and Value " + department;
        }
        return "Deparment doesnot exist with id " + id;
    }

    @DeleteMapping("/delete")
    public String deleteDocument(@RequestParam String id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return "Department with id " + id + " deleted successfully";
        }
        return "Department doesnot exist in database with id " + id;
    }
}
