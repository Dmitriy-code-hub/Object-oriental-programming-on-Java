package sample;
public class BusinessClass extends Accommodation {
        public BusinessClass(String name, double dayPrice) {
            super(name, dayPrice);
        }
        public BusinessClass(String name, double dayPrice, double days) {
            super(name, dayPrice, days);
        }

        @Override
        public String getName() {
            return "Business class hotel "  + super.getName();
        }

    }


