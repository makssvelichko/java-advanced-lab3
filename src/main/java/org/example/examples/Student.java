/**
 * Represents a student entity with attributes such as name, age, university, and faculty.
 * Annotated to map to a database table named "Student" with corresponding columns.
 */
package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

/**
 * Maps the Student class to the "Student" table in the database.
 */
@Table(name = "Student")
public class Student {

  /**
   * Unique identifier for the student, mapped to the "id" column in the database.
   */
  @Id
  @Column(name = "id")
  private int id;

  /**
   * The name of the student, mapped to the "name" column in the database.
   */
  @Column(name = "name")
  private String name;

  /**
   * The age of the student, mapped to the "age" column in the database.
   */
  @Column(name = "age")
  private int age;

  /**
   * The university the student is enrolled in, mapped to the "university" column in the database.
   */
  @Column(name = "university")
  private String university;

  /**
   * The faculty of the university the student belongs to, mapped to the "faculty" column in the database.
   */
  @Column(name = "faculty")
  private String faculty;

  /**
   * Constructs a new Student instance.
   *
   * @param name       the name of the student
   * @param age        the age of the student
   * @param university the university of the student
   * @param faculty    the faculty of the student
   */
  public Student(String name, int age, String university, String faculty) {
    this.name = name;
    this.age = age;
    this.university = university;
    this.faculty = faculty;
  }

  /**
   * Gets the unique identifier of the student.
   *
   * @return the student's ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the student.
   *
   * @param id the student's ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the name of the student.
   *
   * @return the student's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the student.
   *
   * @param name the student's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the age of the student.
   *
   * @return the student's age
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the student.
   *
   * @param age the student's age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the university of the student.
   *
   * @return the student's university
   */
  public String getUniversity() {
    return university;
  }

  /**
   * Sets the university of the student.
   *
   * @param university the student's university
   */
  public void setUniversity(String university) {
    this.university = university;
  }

  /**
   * Gets the faculty of the student.
   *
   * @return the student's faculty
   */
  public String getFaculty() {
    return faculty;
  }

  /**
   * Sets the faculty of the student.
   *
   * @param faculty the student's faculty
   */
  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }
}
