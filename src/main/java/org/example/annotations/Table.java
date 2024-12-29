package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The `Table` annotation is used to map a Java class to a database table.
 * It has a `name` parameter that specifies the name of the table.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
  /**
   * Specifies the name of the table in the database.
   * @return the name of the table
   */
  String name();
}
