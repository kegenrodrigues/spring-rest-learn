package com.coders.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coders.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> studentList;
	
	@PostConstruct
	public void loadData() {	
		studentList = new ArrayList<>();
		Student student1 = new Student("Albert","Einstein");
		Student student2 = new Student("Saygin","Yalcin");
		Student student3 = new Student("Thomas","Edison");
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
	}
	
	//define the endpoint for "/students" and return the list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return studentList;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId ) {
		
		if (studentId<0 || studentId>=studentList.size()) {
			throw new StudentNotFoundException("Student ID not found: "+studentId);
		}
		return studentList.get(studentId);
	}

	
}
