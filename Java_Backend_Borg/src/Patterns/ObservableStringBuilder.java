package Patterns;

import java.util.ArrayList;
import java.util.List;

public class ObservableStringBuilder implements Subject {

    private final StringBuilder delegate;
    private final List<Observer> observers;

    public ObservableStringBuilder(String str) {
        this.delegate = new StringBuilder(str);
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String currentValue = delegate.toString();
        for (Observer observer : observers) {
            observer.update(currentValue);
        }
    }

    public ObservableStringBuilder append(String str) {
        delegate.append(str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder insert(int offset, String str) {
        delegate.insert(offset, str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String str) {
        delegate.replace(start, end, str);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder reverse() {
        delegate.reverse();
        notifyObservers();
        return this;
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
