package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }
    public void addStudent(int id, String name, String email) {
        Student student = new Student(id, name, email);
        studentDAO.addStudent(student);
    }
    public void deleteAllStudents() {
        try {
            studentDAO.deleteAllStudents();
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de tous les étudiants : " + e.getMessage());
        }
    }
    public void displayStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant inscrit.");
        } else {
            for (Student student : students) {
                System.out.println(student.getName());
            }
        }
    }
    public void updateStudent(int id, String name, String email) {
        Student student = studentDAO.getStudentById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(name);
        student.setEmail(email);
        studentDAO.updateStudent(student);
    }
    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }

    public void getAllStudents() {
        studentDAO.getAllStudents();
    }

    public Student findStudentById(int id) {
        return studentDAO.findStudentById(id);
    }
}
