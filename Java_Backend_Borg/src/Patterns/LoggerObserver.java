package Patterns;

public class LoggerObserver implements Observer {
    private final String name;

    public LoggerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String newValue) {
        System.out.println("[" + name + "] Обновление: " + newValue);
    }
}
