package views;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CalculationHistory;
import models.User;

import java.sql.SQLException;

public class ProfileView {
    private final Stage stage;
    private final DBManager dbManager;
    private final User currentUser;

    public ProfileView(Stage stage, DBManager dbManager, User currentUser) {
        this.stage = stage;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label userLabel = new Label("Профиль пользователя: " + currentUser.getUsername());

        TableView<CalculationHistory> table = new TableView<>();

        TableColumn<CalculationHistory, String> figureCol = new TableColumn<>("Фигура");
        figureCol.setCellValueFactory(new PropertyValueFactory<>("figure"));

        TableColumn<CalculationHistory, Double> areaCol = new TableColumn<>("Площадь");
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));

        TableColumn<CalculationHistory, Double> volumeCol = new TableColumn<>("Объем");
        volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));

        TableColumn<CalculationHistory, Double> perimeterCol = new TableColumn<>("Периметр");
        perimeterCol.setCellValueFactory(new PropertyValueFactory<>("perimeter"));

        table.getColumns().addAll(figureCol, areaCol, volumeCol, perimeterCol);

        try {
            ObservableList<CalculationHistory> historyList = FXCollections.observableArrayList(
                    dbManager.getHistory(currentUser.getId())
            );
            table.setItems(historyList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> new MainMenuView(stage, currentUser, dbManager).show());

        layout.getChildren().addAll(userLabel, table, backButton);
        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}