package VCS_Adv_OOP.transports;

import VCS_Adv_OOP.transportType.AirTransport;
import VCS_Adv_OOP.properties.Engine;

public final class Airplane extends AirTransport {
    private final Engine engine;

    public Airplane(String model, int maxSpeed, Engine engine) {
        super(model, maxSpeed);
        this.engine = engine;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println(engine);
    }
}
