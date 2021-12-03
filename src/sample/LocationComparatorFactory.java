package sample;
import java.util.Comparator;

public class LocationComparatorFactory {
    public static Comparator<Location> get(String id) {
        switch (id) {
            case "price": /* separate class example */
                return new PriceComparator();

            case "days": /* anonymous class example */
                return new Comparator<Location>() {
                    @Override
                    public int compare(Location f1, Location f2) {
                        return Double.compare(f1.getDays(), f2.getDays());
                    }
                };

            case "name": /* lambda expression example */
                return (Location f1, Location f2) ->
                        f1.getName().compareTo(f2.getName());
        }
        return null;
    }
}

class PriceComparator implements Comparator<Location> {
    @Override
    public int compare(Location f1, Location f2) {
        return Double.compare(f1.getPrice(), f2.getPrice());
    }

}
