package org.example;

import org.example.examples.Car;
import org.example.examples.Message;
import org.example.examples.Student;
import org.example.examples.CarSQLGenerator;
import org.example.examples.MessageSQLGenerator;
import org.example.examples.StudentSQLGenerator;

public class Main {
    public static void main(String[] args) {

        // Створення об'єкта Car
        Car car = new Car(1, "Toyota", "Camry", 2021, 30000.0);

        // Генерація SQL-запитів для Car
        String carCreateQuery = CarSQLGenerator.CREATE_QUERY;
        String carInsertQuery = CarSQLGenerator.generateInsertQuery(car);
        String carUpdateQuery = CarSQLGenerator.generateUpdateQuery(car);
        String carDeleteQuery = CarSQLGenerator.generateDeleteQuery(car.getId());
        String carSelectQuery = CarSQLGenerator.SELECT_QUERY;

        System.out.println("CREATE Query (Car): " + carCreateQuery);
        System.out.println("INSERT Query (Car): " + carInsertQuery);
        System.out.println("UPDATE Query (Car): " + carUpdateQuery);
        System.out.println("DELETE Query (Car): " + carDeleteQuery);
        System.out.println("SELECT Query (Car): " + carSelectQuery);

        // Створення об'єкта Message
        Message message = new Message("Hello, how are you?", "Alice", "Bob");
        message.setId(1);

        // Генерація SQL-запитів для Message
        String messageCreateQuery = MessageSQLGenerator.CREATE_QUERY;
        String messageInsertQuery = MessageSQLGenerator.generateInsertQuery(message);
        String messageUpdateQuery = MessageSQLGenerator.generateUpdateQuery(message);
        String messageDeleteQuery = MessageSQLGenerator.generateDeleteQuery(message.getId());
        String messageSelectQuery = MessageSQLGenerator.SELECT_QUERY;

        System.out.println("CREATE Query (Message): " + messageCreateQuery);
        System.out.println("INSERT Query (Message): " + messageInsertQuery);
        System.out.println("UPDATE Query (Message): " + messageUpdateQuery);
        System.out.println("DELETE Query (Message): " + messageDeleteQuery);
        System.out.println("SELECT Query (Message): " + messageSelectQuery);

        // Створення об'єкта Student
        Student student = new Student("John Doe", 21, "Harvard", "Computer Science");
        student.setId(101);

        // Генерація SQL-запитів для Student
        String studentCreateQuery = StudentSQLGenerator.CREATE_QUERY;
        String studentInsertQuery = StudentSQLGenerator.generateInsertQuery(student);
        String studentUpdateQuery = StudentSQLGenerator.generateUpdateQuery(student);
        String studentDeleteQuery = StudentSQLGenerator.generateDeleteQuery(student.getId());
        String studentSelectQuery = StudentSQLGenerator.SELECT_QUERY;

        System.out.println("CREATE Query (Student): " + studentCreateQuery);
        System.out.println("INSERT Query (Student): " + studentInsertQuery);
        System.out.println("UPDATE Query (Student): " + studentUpdateQuery);
        System.out.println("DELETE Query (Student): " + studentDeleteQuery);
        System.out.println("SELECT Query (Student): " + studentSelectQuery);
    }
}
