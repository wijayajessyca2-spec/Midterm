package com.example.midterm;

public class StudentModel {
    Integer id;
    String name, studentId;
    int age;

    public StudentModel(String name, String studentId, int age) {
        this.name = name;
        this.studentId = studentId;
        this.age = age;
    }

    public StudentModel(Integer id, String name, String studentId, int age) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.age = age;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}