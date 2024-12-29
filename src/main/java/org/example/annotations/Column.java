package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The `Column` annotation is used to map a field in a Java class to a column in a database table.
 * It has a `name` parameter that specifies the name of the column.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
  /**
   * Specifies the name of the column in the database table.
   * @return the name of the column
   */
  String name();
}
