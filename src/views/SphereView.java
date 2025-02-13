package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Sphere;
import models.User;

import java.net.URL;
import java.sql.SQLException;

public class SphereView extends SquareView {
    public SphereView(Stage stage, DBManager dbManager, User currentUser) {
        super(stage, dbManager, currentUser);
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("layout");

        TextField radiusField = new TextField();
        radiusField.setPromptText("Введите радиус");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double radius = Double.parseDouble(radiusField.getText());
                Sphere sphere = new Sphere(radius);
                handleFigure(sphere);
                new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show());

        layout.getChildren().addAll(
                new Label("Сфера"), radiusField, calculateButton, backButton
        );
        super.getStage().setScene(new Scene(layout, 400, 300));
    }
}
