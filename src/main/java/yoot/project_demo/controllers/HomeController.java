package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.domain.entity.Student;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<String> Home() {
        return ResponseEntity.ok("\"data\": \"This is my content\"");
    }
    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> findAll(){
        return ResponseEntity.ok(studentService.findByAll());
    }
}
