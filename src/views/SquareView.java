package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Figure;
import models.Square;
import models.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SquareView {
    private Stage stage;
    private DBManager dbManager;
    private User currentUser;

    public SquareView(Stage stage, DBManager dbManager, User currentUser) {
        this.stage = stage;
        this.dbManager = dbManager;
        this.currentUser = currentUser;

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 800, 600);

        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField sideField = new TextField();
        sideField.setPromptText("Введите сторону квадрата");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double side = Double.parseDouble(sideField.getText());
                Square square = new Square(side);
                handleFigure(square);
                new CalculatorView(stage, dbManager, currentUser).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(stage, dbManager, currentUser).show());

        layout.getChildren().addAll(new Label("Квадрат"), sideField, calculateButton, backButton);
        stage.setScene(new Scene(layout, 400, 200));
    }

    void handleFigure(Figure figure) throws SQLException {
        double area = figure.calculateArea();
        double volume = figure.calculateVolume();
        double perimeter = figure.calculatePerimeter();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format(
                "Результаты расчетов:\nПлощадь: %.2f\nОбъем: %.2f\nПериметр: %.2f",
                area, volume, perimeter));
        alert.showAndWait();

        dbManager.saveCalculation(currentUser.getId(), figure.getName(), area, volume, perimeter);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
