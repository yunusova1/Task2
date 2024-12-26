package com.example.builders;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Indicator {
    private Color color;
    private String mark;

    public void setPaint(float measure, float start, float stop) {
        if (measure < start || measure > stop) {
            color = Color.RED; // Если значение ниже начальной границы или выше конечной границы, цвет красный
        } else if (measure == start || measure == stop) {
            color = Color.YELLOW; // Если значение на границе, цвет желтый
        } else {
            color = Color.GREEN; // Если значение в пределах, цвет зеленый
        }
    }

    public void setMetka(float measure, float start, float stop) {
        if (measure < start) {
            mark = "Значение ниже нормы: " + measure;
        } else if (measure > stop) {
            mark = "Значение выше нормы: " + measure;
        } else if (measure == start || measure == stop) {
            mark = "Значение на границе: " + measure;
        } else {
            mark = "Значение в норме: " + measure;
        }
    }

    public void show(Pane mainPane, float start, float stop, float measure) {
        // Очистка предыдущих элементов
        mainPane.getChildren().clear();

        // Отрезок от start до stop
        Line line = new Line(5, 50, 600, 50);
        line.setStroke(Color.BLACK);
        mainPane.getChildren().add(line);

        // Дуга на выбранном значении
        double x = (measure - start) / (stop - start) * 495 + 5; // Пропорциональное значение для дуги
        Arc arc = new Arc(x, 50, 10, 10, 0, 180); // Дуга радиусом 10
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        mainPane.getChildren().add(arc);

        // Отображение метки для начального значения
        Text startText = new Text(String.valueOf(start));
        startText.setX(5); // Позиция по оси X
        startText.setY(40); // Позиция по оси Y
        startText.setFont(new Font(16)); // Увеличение размера текста
        mainPane.getChildren().add(startText);

        // Отображение метки для конечного значения
        Text stopText = new Text(String.valueOf(stop));
        stopText.setX(300); // Позиция по оси X
        stopText.setY(40); // Позиция по оси Y
        stopText.setFont(new Font(16)); // Увеличение размера текста
        mainPane.getChildren().add(stopText);

        // Отображение метки
        Text markText = new Text(mark);
        markText.setY(70); // Смещение метки вниз
        markText.setFont(new Font(16)); // Увеличение размера текста
        markText.setX((300 - 5) / 2 - markText.getLayoutBounds().getWidth() / 2); // Центрирование метки по оси X
        mainPane.getChildren().add(markText);
    }
}
