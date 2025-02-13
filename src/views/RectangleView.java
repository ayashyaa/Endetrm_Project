package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import models.User;
import views.SquareView;
import models.Rectangle;
import java.sql.SQLException;

public class RectangleView extends SquareView {
    public RectangleView(Stage stage, DBManager dbManager, User currentUser) {
        super(stage, dbManager, currentUser);
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField lengthField = new TextField();
        lengthField.setPromptText("Введите длину");
        TextField widthField = new TextField();
        widthField.setPromptText("Введите ширину");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(event -> {
            try {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                Rectangle rectangle = new Rectangle(length, width);
                handleFigure(rectangle);
                new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show();
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> new CalculatorView(super.getStage(), super.getDbManager(), super.getCurrentUser()).show());

        layout.getChildren().addAll(new Label("Прямоугольник"), lengthField, widthField, calculateButton, backButton);
        super.getStage().setScene(new Scene(layout, 400, 200));
    }
}
