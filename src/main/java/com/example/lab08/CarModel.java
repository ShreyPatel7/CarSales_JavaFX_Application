package com.example.lab08;

// CarModel.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CarModel {
    // Making the database connection
// Has the username of "student" and the password of "student"
    private Connection getConnection() throws Exception {
        String username = "student";
        String password = "student";
        String url = "jdbc:mysql://localhost/F22Midterm";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to get cars from the database
    public ArrayList<Car> getCarsFromDatabase() throws Exception {
        ArrayList<Car> cars = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM carSales");

            while (resultSet.next()) {
                int carID = resultSet.getInt("carID");
                int modelYear = resultSet.getInt("modelYear");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                LocalDate dateSold = resultSet.getDate("dateSold").toLocalDate();

                Car car = new Car(carID, modelYear, make, model, price, dateSold);
                cars.add(car);
            }
        }

        return cars;
    }

    // Method to get years from the database
    public ArrayList<Integer> getYearsFromDatabase() throws Exception {
        ArrayList<Integer> years = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT modelYear FROM carSales");

            while (resultSet.next()) {
                int year = resultSet.getInt("modelYear");
                years.add(year);
            }
        }

        return years;
    }
}