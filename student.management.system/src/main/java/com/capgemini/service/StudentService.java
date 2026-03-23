package com.capgemini.service;

import java.util.List;
import java.util.Optional;
import com.capgemini.entity.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Student;
import com.capgemini.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	public void saveStudent(Student student) {
		studentRepository.save(student);
		System.out.println("Student Saved Successfully!");
	}

	public void saveStudents(List<Student> students) {
		studentRepository.saveAll(students);
		System.out.println("Students Saved Successfully!");
	}

	public void getStudent(Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = optional.get();
		System.out.println(student);
		System.out.println("Student Read Successfully!");
	}

	public void getStudents() {
		List<Student> students = studentRepository.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
		System.out.println("Students Read Successfully!");
	}

	public void updateStudent(Integer id, String mailId, Long contactNumber) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = optional.get();
		student.setMailId(mailId);
		student.setContactNumber(contactNumber);
		studentRepository.save(student);
		System.out.println("Student Updated Successfully!");
	}

	public void deleteStudent(Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = optional.get();
		studentRepository.delete(student);
		System.out.println("Student Deleted Successfully!");
	}

	public void deleteStudents() {
		studentRepository.deleteAll();
		System.out.println("Students Deleted Successfully!");
	}
}