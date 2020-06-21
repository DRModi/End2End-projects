package com.drmodi.studentdal;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drmodi.studentdal.model.Student;
import com.drmodi.studentdal.repository.StudentRepository;

@SpringBootTest
class StudentdalApplicationTests {

	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void testCreateStudent() {
		
		Student std = new Student();
		std.setName("TestUser");
		std.setCourse("Computer Science");
		std.setFee(10000d);
		
		studentRepository.save(std);
	}
	
	
	@Test
	public void findStudentBasedOnName() {
		Optional<Student> findById = studentRepository.findById(2l);
		
		if(findById.isPresent()) {
			Student student = findById.get();
			System.out.println("********  Student : "+student);
		}
		
		
	}
	
	
	
	@Test
	public void testUpdateStudent() {
		
				Optional<Student> findById = studentRepository.findById(2l);
				Student student;
				if(findById.isPresent()) {
					student = findById.get();
					student.setFee(11000d);
					studentRepository.save(student);
				}				
				
	}
	
	@Test
	public void testDeleteStudent() {
		
				Optional<Student> findById = studentRepository.findById(2l);
				Student student;
				if(findById.isPresent()) {
					student = findById.get();
					studentRepository.deleteById(student.getId());
				}				
				
	}
	
	
}
