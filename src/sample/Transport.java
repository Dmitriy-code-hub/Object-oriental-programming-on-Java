package sample;
public class Transport  implements Location {

    private String name;
    private double dayPrice;
    private double days;

    public Transport(String name, double dayPrice) {
        this.name = name;
        this.dayPrice = dayPrice;
    }

    public Transport(String name, double dayPrice, double days) {
        this(name, dayPrice);
        this.days = days;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return dayPrice * days ;
    }

    @Override
    public void setDays(double days) {
        this.days = days;
    }

    @Override
    public double getDays() {
        return days;
    }


}
