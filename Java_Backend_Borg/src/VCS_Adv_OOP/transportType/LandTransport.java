package VCS_Adv_OOP.transportType;

public non-sealed abstract class LandTransport extends Transport {
    public LandTransport(String model, int maxSpeed) {
        super(model, maxSpeed);
    }

    @Override
    public void move() {
        System.out.println(model + " Движется по земле");
    }
}
