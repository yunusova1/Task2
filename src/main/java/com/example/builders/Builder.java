package com.example.builders;
import javafx.scene.layout.Pane;

public interface Builder {
    //Устанавливает границы линии.
    void lineBounds(float start, float stop);
    //Устанавливает параметры рисования линии.
    void linePaint(float measure);
    void lineMark(String measure);
    //Добавляет заголовок к графическому элементу.
    void addTitle(String name);
    Indicator build();
    Pane getMainPane();
}
