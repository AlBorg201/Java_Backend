package Multithreaded;

/**
 * Интерфейс наблюдателя (Observer) для реализации паттерна "Наблюдатель".
 * Используется для логгирования и мониторинга событий в банке.
 */
public interface Observer {
    /**
     * Метод вызывается при получении уведомления.
     *
     * @param message сообщение о событии
     */
    void update(String message);
}
