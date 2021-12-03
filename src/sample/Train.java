package sample;
public class Train extends Transport {
    public Train(String name, double dayPrice) {

        super(name, dayPrice);
    }
    public Train(String name, double dayPrice, double days) {
        super(name, dayPrice, days);
    }

    @Override
    public String getName() {
        return "Train "  + super.getName();
    }

}
