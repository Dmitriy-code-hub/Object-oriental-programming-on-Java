package sample;
import java.util.*;
public class RegisterLocation extends Accommodation {

    private List<Location> components;

    public RegisterLocation(String name) {
        super(name, 0);
        components = new ArrayList<Location>();
    }

    public void add(Location component) {
        components.add(component);
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (Location component: components) {
            price += component.getPrice();
        }
        return price;
    }

    @Override
    public void setDays(double days) {
        // OK, let it be 'scaling', not exception
        double dayKoef;
        if (getDays() > 0) {
            dayKoef = days / getDays();
            for (Location component: components) {
                component.setDays(component.getDays() * dayKoef);
            }
        }
        else if (!components.isEmpty()) {
            dayKoef = days / components.size();
            for (Location component: components) {
                component.setDays(dayKoef);
            }
        }
    }

    @Override
    public double getDays() {
        double w = 0;
        for (Location component: components) {
            w += component.getDays();
        }
        return w;
    }

    public List<Location> getComponents() {
        return new ArrayList<>(components);
    }

}
