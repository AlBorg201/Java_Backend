package Multithreaded;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс логгера, реализующий интерфейс Observer.
 * Используется для вывода сообщений о событиях в системе.
 */
public class Logger implements Observer {
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Выводит сообщение с временной меткой.
     *
     * @param message текст события
     */
    @Override
    public synchronized void update(String message) {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("[LOG " + time + "] " + message);
    }
}
