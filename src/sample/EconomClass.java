package sample;
public class EconomClass extends Accommodation
{
        public EconomClass(String name, double dayPrice) {
            super(name, dayPrice);
        }
        public EconomClass(String name, double dayPrice, double days) {
            super(name, dayPrice, days);
        }
        @Override
        public String getName() {
            return "Econom class hotel "  + super.getName();
        }


    }
