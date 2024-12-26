package com.example.iterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import java.io.File;

public class Controller {
    @FXML
    private ImageView imageView;
    @FXML
    private TextField delayField; // задержка между изображениями
    @FXML
    private TextField directoryField;
    @FXML
    private ComboBox<String> formatComboBox;
    private ConcreteAggregate conaggr; // Объект, хранящий изображения
    private Iterator iter; // Итератор для перебора изображений
    private Timeline time;
    @FXML
    public void initialize() {
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE); // Устанавливаем бесконечный цикл для таймлайна
        formatComboBox.getItems().addAll("PNG", "JPG");
    }
    @FXML
    public void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src/images"));
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            directoryField.setText(selectedDirectory.getAbsolutePath());
            // Обновляем путь к изображениям в ConcreteAggregate
            String path = selectedDirectory.getAbsolutePath() + File.separator; // Получаем полный путь к директории
            String format = formatComboBox.getValue().toLowerCase(); // Получаем выбранный формат изображения

            conaggr = new ConcreteAggregate(path, "." + format);
            iter = conaggr.getIterator();
        }
    }
    @FXML
    public void startSlideshow() {
        try {
            int delay = Integer.parseInt(delayField.getText());
            if (delay <= 0) { // Проверяем, что задержка больше нуля
                System.out.println("Задержка должна быть больше нуля.");
                return;
            }
            time.getKeyFrames().clear();
            time.getKeyFrames().add(new KeyFrame(Duration.seconds(delay), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (iter != null && iter.hasNext(1)) { // Проверяем, есть ли доступные изображения
                        imageView.setImage((Image) iter.next()); // Устанавливаем следующее изображение
                    } else {
                        System.out.println("Нет доступных изображений для показа.");
                    }
                }
            }));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play(); // Запуск временной шкалы
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, введите корректное число для задержки.");
        }
    }
    @FXML
    public void stopSlideshow() {
        time.stop(); // Остановка анимации при вызове этого метода
    }

    @FXML
    public void previousImage() {
        if (iter != null) {
            iter.previous(); // Возвращаемся к предыдущему изображению
            imageView.setImage((Image) iter.preview()); // Устанавливаем текущее изображение в ImageView
        }
    }
    @FXML
    public void nextImage() {
        if (iter != null && iter.hasNext(1)) {
            imageView.setImage((Image) iter.next()); // Устанавливаем следующее изображение в ImageView
        }
    }
}

