package VCS_Adv_OOP.properties;

public class Engine {
    private final double horsePower;
    private final Fuel fuel;

    public Engine(double horsePower, Fuel fuel) {
        this.horsePower = horsePower;
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Двигатель: " + horsePower + " л.с., топливо: " + fuel;
    }
}
