package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

@Table(name = "Student")
public class Student {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "university")
  private String university;

  @Column(name = "faculty")
  private String faculty;

  // Конструктор
  public Student(String name, int age, String university, String faculty) {
    this.name = name;
    this.age = age;
    this.university = university;
    this.faculty = faculty;
  }

  // Геттери та сеттери
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }
}
