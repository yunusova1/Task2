package com.example.iterator;
import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class ConcreteAggregate implements Aggregate {
    private String directoryPath; // Путь к директории с изображениями
    private List<Image> images; // Список загруженных изображений
    // Конструктор
    public ConcreteAggregate(String directoryPath, String format) {
        this.directoryPath = directoryPath; // путь к директории
        loadImages(format);
    }
    private void loadImages(String format) {
        images = new ArrayList<>();
        // Создаем объект File для директории
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(format));
        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
            System.out.println("Загружено изображений: " + images.size());
        } else {
            System.out.println("Не удалось загрузить изображения.");
        }
    }
    // Метод для получения итератора изображений
    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }
    private class ImageIterator implements Iterator {
        private int current = 0;
        //проверка наличия следующего элемента
        @Override
        public boolean hasNext(int x) {
            return !images.isEmpty();
        }
        //получения следующего изображения
        @Override
        public Object next() {
            if (hasNext(1)) {
                Image image = images.get(current);
                current = (current + 1) % images.size();
                return image;
            }
            return null;
        }
        // Метод для получения текущего изображения без перемещения по списку
        @Override
        public Object preview() {
            if (!images.isEmpty()) {
                return images.get(current);
            }
            return null;
        }
        // Метод для получения предыдущего изображения
        @Override
        public Object previous() {
            if (!images.isEmpty()) {
                current = (current - 1 + images.size()) % images.size();
                return images.get(current);
            }
            return null;
        }
    }
}
