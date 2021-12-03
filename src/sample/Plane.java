package sample;
public class Plane extends Transport {
    public Plane(String name, double dayPrice) {
        super(name, dayPrice);
    }
    public Plane(String name, double dayPrice, double days) {
        super(name, dayPrice, days);
    }

    @Override
    public String getName() {
        return "Plane "  + super.getName();
    }

}
