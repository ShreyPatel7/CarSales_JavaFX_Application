package com.example.lab08;

import java.time.LocalDate;

public class Car {
    private int carID;
    private int modelYear;
    private String make;
    private String model;
    private int price;
    private LocalDate dateSold;

    // Constructor having the 5 parameter that validates the rules
    public Car(int carID, int modelYear, String make, String model, int price, LocalDate dateSold) {
        validateCarID(carID);
        validateMake(make);
        validateModel(model);
        validatePrice(price);
        validateDateSold(dateSold);

        this.carID = carID;
        this.modelYear = modelYear;
        this.make = make;
        this.model = model;
        this.price = price;
        this.dateSold = dateSold;
    }

    // Validation rule for the carID
    private void validateCarID(int carID) {
        if (carID <= 0) {
            throw new IllegalArgumentException("Car ID should be greater than 0.");
        }
    }

    // Validation rule for the maker of the cars
    private void validateMake(String make) {
        if (!make.equals("Acura") && !make.equals("Ford") && !make.equals("Honda") && !make.equals("Nissan") && !make.equals("Tesla")) {
            throw new IllegalArgumentException("Invalid make. Make should be 'Acura', 'Ford', 'Honda', 'Nissan', or 'Tesla'.");
        }
    }

    // validation rule for the model should be least 2 characters long
    private void validateModel(String model) {
        if (model.length() < 2) {
            throw new IllegalArgumentException("Model should be at least 2 characters long.");
        }
    }

    // Validation rule for price it should be greater than 0
    private void validatePrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price should be greater than 0.");
        }
    }

    // Validation rule for the date sold should be no later than today
    private void validateDateSold(LocalDate dateSold) {
        LocalDate currentDate = LocalDate.now();
        if (dateSold.isAfter(currentDate)) {
            throw new IllegalArgumentException("Invalid date sold. Date cannot be in the future.");
        }
    }

    // Setters And Getters
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        validateCarID(carID);
        this.carID = carID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        validateMake(make);
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        validateModel(model);
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        validatePrice(price);
        this.price = price;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        validateDateSold(dateSold);
        this.dateSold = dateSold;
    }
}