package com.example.mysqljava.controller;

import com.example.mysqljava.entity.Professor;
import com.example.mysqljava.repository.ProfessorRepository;
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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @PostMapping("/create")
    public Professor addProfessor(@RequestParam String name, @RequestParam String dateOfBirth, @RequestParam Long departmentId) throws Exception {
        Professor professor = new Professor();
        professor.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth));
        professor.setName(name);
        professor.setDepartmentId(departmentId);
        professorRepository.save(professor);
        return professor;
    }

    @GetMapping("/getById")
    public Professor getProfessor(@RequestParam String id) {
        Professor professor = professorRepository.findById(id).orElse(new Professor());
        return professor;
    }

    @GetMapping("/all")
    public Iterable<Professor> getAllProfessor() {
        return professorRepository.findAll();
    }

    @PutMapping("/update")
    public String updateProfessor(@RequestParam String id, @RequestParam String name, @RequestParam String dateOfBirth, @RequestParam Long departmentId) throws Exception {
        if (professorRepository.existsById(id)) {
            Professor professor = professorRepository.findById(id).get();
            if (!StringUtils.isEmpty(name)) {
                professor.setName(name);
            }
            if (!StringUtils.isEmpty(dateOfBirth)) {
                professor.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth));
            }
            if (!StringUtils.isEmpty(departmentId)) {
                professor.setDepartmentId(departmentId);
            }
            professorRepository.save(professor);
            return "Successfully updated the professor with id " + id + " and Value " + professor;
        }
        return "Professor doesnot exist with id " + id;
    }

    @DeleteMapping("/delete")
    public String deleteDocument(@RequestParam String id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return "Professor with id : " + id + " deleted successfully";
        }
        return "Professor doesnot exist in database with id " + id;
    }

    @GetMapping("/getByDepartmentId")
    public Iterable<Professor> getProfessorsByDepartmentId(Long departmenId) {
        return professorRepository.findAllByDepartmentId(departmenId);
    }
}
