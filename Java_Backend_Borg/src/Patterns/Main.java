package Patterns;

public class Main {
    public static void main(String[] args) {
        ObservableStringBuilder builder = new ObservableStringBuilder("найден");

        Observer logger1 = new LoggerObserver("Logger1");
        Observer logger2 = new LoggerObserver("Logger2");
        Observer lengthObserver = new LengthObserver();

        builder.registerObserver(logger1);
        builder.registerObserver(logger2);
        builder.registerObserver(lengthObserver);

        builder.append(" баг");
        builder.insert(0, "опять ");
        builder.replace(0, 5, "снова").reverse();

        builder.removeObserver(logger1);

        builder.append(" а ,ьтяпо ен").reverse();

        builder.replace(0, 25, "");
        builder.append(" пропал");
    }
}
