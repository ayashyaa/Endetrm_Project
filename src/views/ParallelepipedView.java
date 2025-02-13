package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Parallelepiped;
import models.User;

import java.sql.SQLException;

public class ParallelepipedView extends SquareView {
    public ParallelepipedView(Stage stage, DBManager dbManager, User currentUser) {
        super(stage, dbManager, currentUser);
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("layout");

        TextField lengthField = new TextField();
        lengthField.setPromptText("Введите длину");

        TextField widthField = new TextField();
        widthField.setPromptText("Введите ширину");

        TextField heightField = new TextField();
        heightField.setPromptText("Введите высоту");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                double height = Double.parseDouble(heightField.getText());

                Parallelepiped parallelepiped = new Parallelepiped(length, width, height);
                handleFigure(parallelepiped);
                new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show());

        layout.getChildren().addAll(
                new Label("Параллелепипед"), lengthField, widthField, heightField, calculateButton, backButton
        );
        super.getStage().setScene(new Scene(layout, 400, 300));
    }
}
