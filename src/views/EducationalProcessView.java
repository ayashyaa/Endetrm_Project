package views;

import db.DBManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EducationalProcessView {
    private final Stage stage;
    private final DBManager dbManager;
    private final User currentUser;
    private Map<String, String[]> quizQuestions;
    private final String[] figures = {"Квадрат", "Прямоугольник", "Параллелепипед", "Пирамида", "Сфера", "Конус"};

    public EducationalProcessView(Stage stage, DBManager dbManager, User currentUser) {
        this.stage = stage;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
        initializeQuizQuestions();
    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Button squareButton = new Button("Формулы квадрата");
        Button rectangleButton = new Button("Формулы прямоугольника");
        Button parallelepipedButton = new Button("Формулы параллелепипеда");
        Button pyramidButton = new Button("Формулы пирамиды");
        Button sphereButton = new Button("Формулы сферы");
        Button coneButton = new Button("Формулы конуса");
        Button quizButton = new Button("Тест по фигурам");
        Button backButton = new Button("Назад");

        squareButton.setOnAction(event -> showFormulas("Квадрат", "S = a²\nP = 4a"));
        rectangleButton.setOnAction(event -> showFormulas("Прямоугольник", "S = ab\nP = 2(a+b)"));
        parallelepipedButton.setOnAction(event -> showFormulas("Параллелепипед", "V = a × b × c\nS = 2(ab + bc + ac)"));
        pyramidButton.setOnAction(event -> showFormulas("Пирамида", "V = (1/3) × Sосн × h\nS = Sосн + Sбок"));
        sphereButton.setOnAction(event -> showFormulas("Сфера", "V = (4/3)πr³\nS = 4πr²"));
        coneButton.setOnAction(event -> showFormulas("Конус", "V = (1/3)πr²h\nS = πr(l + r)"));
        quizButton.setOnAction(event -> startQuiz());
        backButton.setOnAction(event -> new MainMenuView(stage, currentUser, dbManager).show());

        layout.getChildren().addAll(squareButton, rectangleButton, parallelepipedButton, pyramidButton, sphereButton, coneButton, quizButton, backButton);
        stage.setScene(new Scene(layout, 400, 450));
    }

    private void showFormulas(String shape, String formulas) {
        new Alert(Alert.AlertType.INFORMATION, shape + "\n" + formulas).showAndWait();
    }

    private void startQuiz() {
        Random random = new Random();
        String figure = figures[random.nextInt(figures.length)];
        String[] questions = quizQuestions.get(figure);
        boolean isVolumeQuestion = random.nextBoolean();

        String questionText = isVolumeQuestion ? "Выберите формулу объема для " + figure : "Выберите формулу площади для " + figure;
        String correctAnswer = isVolumeQuestion ? questions[1] : questions[0];
        String incorrectAnswer = isVolumeQuestion ? questions[0] : questions[1];

        VBox quizLayout = new VBox(10);
        quizLayout.setPadding(new Insets(20));
        Label questionLabel = new Label(questionText);
        ToggleGroup optionsGroup = new ToggleGroup();
        RadioButton option1 = new RadioButton(correctAnswer);
        RadioButton option2 = new RadioButton(incorrectAnswer);
        option1.setToggleGroup(optionsGroup);
        option2.setToggleGroup(optionsGroup);
        Button submitButton = new Button("Проверить");

        submitButton.setOnAction(event -> {
            if (option1.isSelected()) {
                new Alert(Alert.AlertType.INFORMATION, "Правильно!").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Неправильно. Правильный ответ: " + correctAnswer).showAndWait();
            }
            show();
        });

        quizLayout.getChildren().addAll(questionLabel, option1, option2, submitButton);
        stage.setScene(new Scene(quizLayout, 400, 300));
    }

    private void initializeQuizQuestions() {
        quizQuestions = new HashMap<>();
        quizQuestions.put("Квадрат", new String[]{"S = a²", "V = -"});
        quizQuestions.put("Прямоугольник", new String[]{"S = ab", "V = -"});
        quizQuestions.put("Параллелепипед", new String[]{"S = 2(ab + bc + ac)", "V = a × b × c"});
        quizQuestions.put("Пирамида", new String[]{"S = Sосн + Sбок", "V = (1/3) × Sосн × h"});
        quizQuestions.put("Сфера", new String[]{"S = 4πr²", "V = (4/3)πr³"});
        quizQuestions.put("Конус", new String[]{"S = πr(l + r)", "V = (1/3)πr²h"});
    }
}
