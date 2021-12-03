package sample;
public class RegisterLocationCatalog {

    private LocationCatalog supplier;
    private TransportCatalog t_supplier;

    public RegisterLocationCatalog(LocationCatalog supplier, TransportCatalog t_supplier) {
        this.supplier = supplier;
        this.t_supplier = t_supplier;
    }

    public RegisterLocation get(String name) {
        RegisterLocation result;
        switch (name) {
            case ("German tour"):
                result = new TravelTour(name, supplier.get("Riu", 5));
                result.add(t_supplier.get("Boryspil-Brandenburg", 1));
                result.add(supplier.get("Hilton", 10));
                result.add(supplier.get("International", 15));
                result.add(t_supplier.get("Kyiv-Berlin", 2));
                return result;


            case ("Ireland tour"):
                result = new TravelTour(name, supplier.get("Riu", 9));
                result.add(t_supplier.get("Kyiv-Dublin", 1));
                result.add(supplier.get("International", 14));
                return result;
        }
        return null;
    }

}
