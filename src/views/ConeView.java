package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Cone;
import models.User;

import java.sql.SQLException;

public class ConeView extends SquareView {
    public ConeView(Stage stage, DBManager dbManager, User currentUser) {
        super(stage, dbManager, currentUser);
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField radiusField = new TextField();
        radiusField.setPromptText("Введите радиус");
        TextField heightField = new TextField();
        heightField.setPromptText("Введите высоту");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double radius = Double.parseDouble(radiusField.getText());
                double height = Double.parseDouble(heightField.getText());
                Cone cone = new Cone(radius, height);
                handleFigure(cone);
                new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show());

        layout.getChildren().addAll(new Label("Конус"), radiusField, heightField, calculateButton, backButton);
        super.getStage().setScene(new Scene(layout, 400, 200));
    }
}
