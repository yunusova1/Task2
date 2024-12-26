package com.example.builders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
public class Controller {
    @FXML
    private Pane indicatorPane; //отображение индикатора
    @FXML
    private TextField startField; //ввод начального значения
    @FXML
    private TextField stopField;
    @FXML
    private TextField measureField;
    private BuilderIndicatorMini builder;
    private Director director;
    @FXML
    public void initialize() {
        builder = new BuilderIndicatorMini();
        director = new Director();
        builder.addTitle("Индикатор значений"); // Установка заголовка
        indicatorPane.getChildren().add(builder.getMainPane());
    }
    @FXML
    private void updateIndicator() {
        try {
            float start = Float.parseFloat(startField.getText());
            float stop = Float.parseFloat(stopField.getText());
            float measure = Float.parseFloat(measureField.getText());
            // Используем директор для создания индикатора
            director.construct(builder, measure, start, stop);
            builder.show(measure);
        } catch (NumberFormatException e) {
            System.err.println("Пожалуйста, введите корректные числовые значения.");
        }
    }
}

