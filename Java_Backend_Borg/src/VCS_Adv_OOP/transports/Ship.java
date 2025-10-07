package VCS_Adv_OOP.transports;

import VCS_Adv_OOP.properties.Engine;
import VCS_Adv_OOP.transportType.WaterTransport;

public final class Ship extends WaterTransport {
    private final Engine engine;

    public Ship(String model, int maxSpeed, Engine engine) {
        super(model, maxSpeed);
        this.engine = engine;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println(engine);
    }
}
