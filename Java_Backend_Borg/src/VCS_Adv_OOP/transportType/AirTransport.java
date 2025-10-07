package VCS_Adv_OOP.transportType;

public non-sealed abstract class AirTransport extends Transport {
    public AirTransport(String model, int maxSpeed) {
        super(model, maxSpeed);
    }

    @Override
    public void move() {
        System.out.println("Летит");
    }
}
