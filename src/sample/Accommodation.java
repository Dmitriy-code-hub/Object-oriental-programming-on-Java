package sample;
public class Accommodation implements Location {

        private String name;
        private double dayPrice;
        private double days;

        public Accommodation(String name, double dayPrice) {
            this.name = name;
            this.dayPrice = dayPrice;
        }

        public Accommodation(String name, double dayPrice, double days) {
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


