package com.example.lab08;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class CarViewController {
    @FXML
    private TableView<Car> carTable;
    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private Label totalCarsLabel;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private BarChart<String, Integer> manufacturerChart;

    private ObservableList<Car> carData = FXCollections.observableArrayList();
    private ObservableList<Car> filteredCarData = FXCollections.observableArrayList();
    private ObservableList<Integer> yearData = FXCollections.observableArrayList();
    private CarModel carModel;

    // Initialization
    void initialize() {
        try {
            carModel = new CarModel();

            carData = FXCollections.observableArrayList(carModel.getCarsFromDatabase());
            filteredCarData.addAll(carData);

            yearData = FXCollections.observableArrayList(carModel.getYearsFromDatabase());
            yearComboBox.setItems(yearData);

            carTable.setItems(filteredCarData);

            carTable.getColumns().clear();

            TableColumn<Car, Integer> carIDColumn = new TableColumn<>("Car ID");
            carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

            TableColumn<Car, Integer> modelYearColumn = new TableColumn<>("Model Year");
            modelYearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));

            TableColumn<Car, String> makeColumn = new TableColumn<>("Make");
            makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

            TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

            TableColumn<Car, Integer> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn<Car, LocalDate> dateSoldColumn = new TableColumn<>("Date Sold");
            dateSoldColumn.setCellValueFactory(new PropertyValueFactory<>("dateSold"));

            carTable.getColumns().addAll(carIDColumn, modelYearColumn, makeColumn, modelColumn, priceColumn, dateSoldColumn);

            updateLabelsAndChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom method for update the Bar Chart and table after selecting the combobox
    private void updateLabelsAndChart() {
        int totalCars = filteredCarData.size();
        totalCarsLabel.setText("Total Cars Sold : " + totalCars);

        int totalSales = filteredCarData.stream().mapToInt(Car::getPrice).sum();
        totalSalesLabel.setText("Total Sales : $" + totalSales);

        manufacturerChart.getData().clear();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Number of Cars Sold");

        int acuraCount = (int) filteredCarData.stream().filter(car -> car.getMake().equals("Acura")).count();
        series.getData().add(new XYChart.Data<>("Acura", acuraCount));

        int fordCount = (int) filteredCarData.stream().filter(car -> car.getMake().equals("Ford")).count();
        series.getData().add(new XYChart.Data<>("Ford", fordCount));

        int hondaCount = (int) filteredCarData.stream().filter(car -> car.getMake().equals("Honda")).count();
        series.getData().add(new XYChart.Data<>("Honda", hondaCount));

        int nissanCount = (int) filteredCarData.stream().filter(car -> car.getMake().equals("Nissan")).count();
        series.getData().add(new XYChart.Data<>("Nissan", nissanCount));

        int teslaCount = (int) filteredCarData.stream().filter(car -> car.getMake().equals("Tesla")).count();
        series.getData().add(new XYChart.Data<>("Tesla", teslaCount));

        manufacturerChart.getData().add(series);
    }

    // Custom method for the year selection combobox
    @FXML
    private void handleYearSelection() {
        int selectedYear = yearComboBox.getSelectionModel().getSelectedItem();
        filteredCarData.clear();

        if (selectedYear == 0) {
            filteredCarData.addAll(carData);
        } else {
            filteredCarData.addAll(carData.stream().filter(car -> car.getModelYear() == selectedYear).collect(Collectors.toList()));
        }

        carTable.setItems(filteredCarData);
        updateLabelsAndChart();
    }
}