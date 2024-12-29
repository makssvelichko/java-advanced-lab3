/**
 * Represents a car entity with attributes such as brand, model, year, and price.
 * Annotated to map to a database table named "Car" with corresponding columns.
 */
package org.example.examples;

import org.example.annotations.Column;
import org.example.annotations.Id;
import org.example.annotations.Table;

/**
 * Maps the Car class to the "Car" table in the database.
 */
@Table(name = "Car")
public class Car {

  /**
   * Unique identifier for the car, mapped to the "id" column in the database.
   */
  @Id
  @Column(name = "id")
  private int id;

  /**
   * The brand of the car, mapped to the "brand" column in the database.
   */
  @Column(name = "brand")
  private String brand;

  /**
   * The model of the car, mapped to the "model" column in the database.
   */
  @Column(name = "model")
  private String model;

  /**
   * The manufacturing year of the car, mapped to the "year" column in the database.
   */
  @Column(name = "year")
  private int year;

  /**
   * The price of the car, mapped to the "price" column in the database.
   */
  @Column(name = "price")
  private double price;

  /**
   * Constructs a new Car instance with all attributes.
   *
   * @param id    the unique identifier of the car
   * @param brand the brand of the car
   * @param model the model of the car
   * @param year  the manufacturing year of the car
   * @param price the price of the car
   */
  public Car(int id, String brand, String model, int year, double price) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
  }

  /**
   * Constructs a new Car instance without an ID.
   * Useful for cases where the ID is generated automatically by the database.
   *
   * @param brand the brand of the car
   * @param model the model of the car
   * @param year  the manufacturing year of the car
   * @param price the price of the car
   */
  public Car(String brand, String model, int year, double price) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.price = price;
  }

  /**
   * Gets the unique identifier of the car.
   *
   * @return the car's ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the car.
   *
   * @param id the car's ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the brand of the car.
   *
   * @return the car's brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the car.
   *
   * @param brand the car's brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the model of the car.
   *
   * @return the car's model
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the car.
   *
   * @param model the car's model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets the manufacturing year of the car.
   *
   * @return the car's year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets the manufacturing year of the car.
   *
   * @param year the car's year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Gets the price of the car.
   *
   * @return the car's price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the car.
   *
   * @param price the car's price
   */
  public void setPrice(double price) {
    this.price = price;
  }
}
