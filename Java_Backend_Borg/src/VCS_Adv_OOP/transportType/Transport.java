package VCS_Adv_OOP.transportType;

public sealed abstract class Transport permits LandTransport, AirTransport, WaterTransport {
    protected String model;
    protected int maxSpeed;
    protected boolean isRunning = false;

    public Transport(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public void showInfo() {
        System.out.println("Модель: " + model + ", Максимальная скорость: " + maxSpeed + " км/ч");
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(model + " запустился");
        } else {
            System.out.println(model + " уже запущен");
        }
    }

    public abstract void move();

    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println(model + " остановился");
        } else {
            System.out.println(model + " уже остановлен");
        }
    }
}
