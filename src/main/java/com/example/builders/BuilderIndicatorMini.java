package com.example.builders;
import javafx.scene.layout.Pane;
//реализует интерфейс Builder
public class BuilderIndicatorMini implements Builder {
    private Indicator indicator; // Индикатор, который будет построен
    private Pane mainPane; // Основная панель для отображения индикатора
    private float start;
    private float stop;
    // Конструктор
    public BuilderIndicatorMini() {
        indicator = new Indicator();
        mainPane = new Pane();
    }
    @Override
    public void addTitle(String title) {
    }
    // установка границ линии индикатора
    @Override
    public void lineBounds(float start, float stop) {
        this.start = start; // Сохраняем начальную границу
        this.stop = stop;
        indicator.setPaint(0, start, stop);
    }
    // Метод для обновления цвета линии индикатора в зависимости от измерения
    @Override
    public void linePaint(float measure) {
        indicator.setPaint(measure, start, stop);
        indicator.setMetka(measure, start, stop);
    }
    // Метод для добавления метки
    @Override
    public void lineMark(String measure) {
    }
    // Метод для получения основной панели индикатора
    @Override
    public Pane getMainPane() {
        return mainPane;
    }
    // Метод для завершения сборки и возврата созданного индикатора
    @Override
    public Indicator build() {
        return indicator;
    }
    // Метод для отображения индикатора с заданным измерением
    public void show(float measure) {
        indicator.show(mainPane, start, stop, measure);
    }
}
