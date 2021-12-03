package sample;
public class LocationCatalog {
    public Location get(String name) {
        switch(name) {
            case ("Hyatt"):
                return new BusinessClass(name, 15);
            case ("Hilton"):
                return new EconomClass(name, 10);
            case ("International"):
                return new BusinessClass(name, 12);
            case ("Riu"):
                return new EconomClass(name, 8);
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
