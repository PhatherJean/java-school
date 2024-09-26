package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/instructors")
public class InstructorController
{
    @Autowired
    private InstructorService instructorService;

    @GetMapping(value = "/instructor/{instructorId}/advice", produces = "application/json")
            public ResponseEntity<?> getInstructorAdviceById(@PathVariable long instructorId)
    {
        Instructor i = instructorService.addAdvice(instructorId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping(value = "/instructor/{instructorId}/advice/{searchTerm}", produces = "application/json")
    public ResponseEntity<?> getInstructorAdviceByIdAndSearch(@PathVariable long instructorId, @PathVariable String searchTerm)
    {
        Instructor i = instructorService.addAdvice(instructorId, searchTerm);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
