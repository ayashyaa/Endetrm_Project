package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Pyramid;
import models.User;

import java.sql.SQLException;

public class PyramidView extends SquareView {
    public PyramidView(Stage stage, DBManager dbManager, User currentUser) {
        super(stage, dbManager, currentUser);
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("layout");

        TextField baseLength = new TextField();
        baseLength.setPromptText("Введите длинну основания");

        TextField baseWidth = new TextField();
        baseWidth.setPromptText("Введите ширину основания");

        TextField heightField = new TextField();
        heightField.setPromptText("Введите высоту");



        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double baseW = Double.parseDouble(baseWidth.getText());
                double baseL = Double.parseDouble(baseLength.getText());
                double height = Double.parseDouble(heightField.getText());

                Pyramid pyramid = new Pyramid(baseL, baseW, height);
                handleFigure(pyramid);
                new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show());

        layout.getChildren().addAll(
                new Label("Пирамида"), baseWidth, baseLength, heightField, calculateButton, backButton
        );
        super.getStage().setScene(new Scene(layout, 400, 300));
    }
}
