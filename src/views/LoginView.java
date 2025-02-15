package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import java.sql.SQLException;

public class LoginView {
    private DBManager dbManager;
    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
        try {
            dbManager = new DBManager("jdbc:postgresql://localhost:5432/geometry", "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        Label title = new Label("Добро пожаловать!");
        Button registerButton = new Button("Регистрация");
        Button loginButton = new Button("Логин");

        registerButton.setOnAction(event -> showRegisterForm());
        loginButton.setOnAction(event -> showLoginForm());

        layout.getChildren().addAll(title, registerButton, loginButton);
        stage.setScene(new Scene(layout, 400, 200));
        stage.show();
    }

    private void showRegisterForm() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Регистрация");

        registerButton.setOnAction(event -> {
            try {
                dbManager.registerUser(usernameField.getText(), passwordField.getText());
                new Alert(Alert.AlertType.INFORMATION, "Регистрация успешна!").showAndWait();
                show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        layout.getChildren().addAll(new Label("Регистрация"), usernameField, passwordField, registerButton);
        stage.setScene(new Scene(layout, 400, 200));
    }

    private void showLoginForm() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Логин");

        loginButton.setOnAction(event -> {
            try {
                User currentUser = dbManager.loginUser(usernameField.getText(), passwordField.getText());
                if (currentUser != null) {
                    new MainMenuView(stage, currentUser, dbManager).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Неверные данные").showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        layout.getChildren().addAll(new Label("Логин"), usernameField, passwordField, loginButton);
        stage.setScene(new Scene(layout, 400, 200));
    }
}
