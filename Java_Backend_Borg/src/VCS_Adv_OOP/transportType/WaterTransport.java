package VCS_Adv_OOP.transportType;

public non-sealed abstract class WaterTransport extends Transport {
    public WaterTransport(String model, int maxSpeed) {
        super(model, maxSpeed);
    }

    @Override
    public void move() {
        System.out.println(model + " Идет по воде");
    }
}
