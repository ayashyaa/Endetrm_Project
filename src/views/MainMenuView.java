package views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import db.DBManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuView {
    private final Stage stage;
    private final User currentUser;
    private final DBManager dbManager;
    @FXML
    private Pane rootPane;
    public MainMenuView(Stage stage, User currentUser, DBManager dbManager) {
        this.stage = stage;
        this.currentUser = currentUser;
        this.dbManager = dbManager;

    }
    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Button calculatorButton = new Button("Калькулятор");
        Button educationButton = new Button("Educational Process");
        Button profileButton = new Button("Profile");
        Button exitButton = new Button("Выход");

        calculatorButton.setOnAction(event -> new CalculatorView(stage, dbManager, currentUser).show());
        profileButton.setOnAction(event -> new ProfileView(stage, dbManager, currentUser).show());
        educationButton.setOnAction(event -> new EducationalProcessView(stage,  dbManager,currentUser).show());
        exitButton.setOnAction(event -> stage.close());

        layout.getChildren().addAll(calculatorButton, educationButton, profileButton, exitButton);
        stage.setScene(new Scene(layout, 400, 200));
    }
}
