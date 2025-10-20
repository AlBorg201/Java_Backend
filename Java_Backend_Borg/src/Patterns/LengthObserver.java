package Patterns;

public class LengthObserver implements Observer {
    @Override
    public void update(String newValue) {
        System.out.println("Количество символов в строке: " + newValue.length());
    }
}
