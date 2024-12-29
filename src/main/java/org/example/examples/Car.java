package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

@Table(name = "Car")
public class Car {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "brand")
  private String brand;

  @Column(name = "model")
  private String model;

  @Column(name = "year")
  private int year;

  @Column(name = "price")
  private double price;

  // Конструктор для створення об'єкта з параметрами
  public Car(int id, String brand, String model, int year, double price) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
  }
  public Car(String brand, String model, int year, double price) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
  }

  // Геттери та сеттери
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
