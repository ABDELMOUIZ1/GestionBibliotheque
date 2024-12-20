package com.library.model;

public class Student {
    private int id;
    private String name;
    private String email;


    // Constructeur par défaut
    public Student() {
    }

    // Constructeur complet
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }
    public Student(String alice, String mail) {

        this.name=alice;
        this.email=mail;
    }

    public Student(int i, String alice, String mail) {
        this.id=i;
        this.name=alice;
        this.email=mail;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
