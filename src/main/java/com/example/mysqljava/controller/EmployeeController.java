package com.example.mysqljava.controller;

import com.example.mysqljava.entity.Employee;
import com.example.mysqljava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/create")
    public Employee addEmployee(@RequestParam String name, @RequestParam String dateOfBirth, @RequestParam Long departmentId, @RequestParam String city) throws Exception {
        Employee employee = new Employee();
        employee.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth));
        employee.setName(name);
        employee.setCity(city);
        employee.setDepartmentId(departmentId);
        employeeRepository.save(employee);
        return employee;
    }

    @GetMapping("/getById")
    public Employee getEmployee(@RequestParam String id) {
        Employee employee = employeeRepository.findById(id).orElse(new Employee());
        return employee;
    }

    @GetMapping("/all")
    public Iterable<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @PutMapping("/update")
    public String updateEmployee(@RequestParam String id, @RequestParam String name, @RequestParam String dateOfBirth, @RequestParam Long departmentId, @RequestParam String city) throws Exception {
        if (employeeRepository.existsById(id)) {
            Employee employee = employeeRepository.findById(id).get();
            if (!StringUtils.isEmpty(name)) {
                employee.setName(name);
            }
            if (!StringUtils.isEmpty(dateOfBirth)) {
                employee.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth));
            }
            if (departmentId != null) {
                employee.setDepartmentId(departmentId);
            }
            if (!StringUtils.isEmpty(city)) {
                employee.setCity(city);
            }
            employeeRepository.save(employee);
            return "Successfully updated the employee with id " + id + " and Value " + employee;
        }
        return "Employee doesnot exist with id " + id;
    }

    @DeleteMapping("/delete")
    public String deleteDocument(@RequestParam String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee with id : " + id + " deleted successfully";
        }
        return "Employee doesnot exist in database with id " + id;
    }

    @GetMapping("/getByDepartmentId")
    public Iterable<Employee> getEmployeesByDepartmentId(@RequestParam Long departmenId) {
        return employeeRepository.findAllByDepartmentId(departmenId);
    }

    @GetMapping("/sortByCity")
    public Iterable<Employee> getEmployeesSortByCity() {
        return employeeRepository.findAllOrderByCity();
    }

    @GetMapping("/sortByName")
    public Iterable<Employee> getEmployeesSortByName() {
        return employeeRepository.findAllOrderByName();
    }

    @GetMapping("/sortByDateOfBirth")
    public Iterable<Employee> getEmployeesSortByDateOfBirth() {
        return employeeRepository.findAllOrderByDateOfBirth();
    }
}
