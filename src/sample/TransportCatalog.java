package sample;
public class TransportCatalog {
    public Location get(String name) {
        switch(name) {
            case ("Kyiv-Berlin"):
                return new Train(name, 100);
            case ("Dnipro-Dresden"):
                return new Train(name, 120);
            case ("Boryspil-Brandenburg"):
                return new Plane(name, 250);
            case ("Kyiv-Dublin"):
                return new Plane(name, 480);
        }
        return null;
    }
    public Location get(String name, double days) {
        Location result = get(name);
        if (result != null) {
            result.setDays(days);
        }
        return result;
    }

}
