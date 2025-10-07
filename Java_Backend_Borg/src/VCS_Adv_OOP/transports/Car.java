package VCS_Adv_OOP.transports;

import VCS_Adv_OOP.properties.Engine;
import VCS_Adv_OOP.transportType.LandTransport;

public final class Car extends LandTransport {
    private final Engine engine;

    public Car(String model, int maxSpeed, Engine engine) {
        super(model, maxSpeed);
        this.engine = engine;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println(engine);
    }
}
