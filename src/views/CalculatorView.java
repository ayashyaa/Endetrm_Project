package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import db.DBManager;
import models.Cone;
import models.User;

public class CalculatorView {
    private final Stage stage;
    private final DBManager dbManager;
    private final User currentUser;

    public CalculatorView(Stage stage, DBManager dbManager, User currentUser) {
        this.stage = stage;
        this.dbManager = dbManager;
        this.currentUser = currentUser;

    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        layout.getStyleClass().add("layout");

        Button squareButton = new Button("Квадрат");
        Button rectangleButton = new Button("Прямоугольник");
        Button backButton = new Button("Назад");
        Button parallelepipedButton = new Button("Параллелепипед");
        Button pyramidButton = new Button("Пирамида");
        Button sphereButton = new Button("Сфера");
        Button coneButton = new Button("Конус");


        parallelepipedButton.setOnAction(event -> new ParallelepipedView(stage, dbManager, currentUser).show());
        coneButton.setOnAction(event -> new ConeView(stage, dbManager, currentUser).show());
        pyramidButton.setOnAction(event -> new PyramidView(stage, dbManager, currentUser).show());
        sphereButton.setOnAction(event -> new SphereView(stage, dbManager, currentUser).show());
        squareButton.setOnAction(event -> new SquareView(stage, dbManager, currentUser).show());
        rectangleButton.setOnAction(event -> new RectangleView(stage, dbManager, currentUser).show());
        backButton.setOnAction(event -> new MainMenuView(stage, currentUser, dbManager).show());

        layout.getChildren().addAll(squareButton, rectangleButton, parallelepipedButton, pyramidButton, sphereButton, backButton);

        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.show();
    }

}
