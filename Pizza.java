public class Pizza {

    public enum Size { S, M, L;

        public static Size fromChar(String s) {
            if (s == null || s.isEmpty()) return M;
            char c = s.toUpperCase().charAt(0);
            if (c == 'S') return S;
            if (c == 'L') return L;
            return M;
        }
    }

    private String name;
    private double basePrice;
    private boolean veg;
    private Size size;

    private static final double EXTRA_CHEESE = 50.0;
    private static final double EXTRA_TOPPINGS = 70.0;
    private static final double TAKEAWAY = 20.0;

    private boolean extraCheeseAdded = false;
    private boolean extraToppingsAdded = false;
    private boolean takeaway = false;

    public Pizza(String name, double basePrice, boolean veg, Size size) {
        this.name = name;
        this.basePrice = basePrice;
        this.veg = veg;
        this.size = size;
    }

    public void addExtraCheese() { extraCheeseAdded = true; }
    public void addExtraToppings() { extraToppingsAdded = true; }
    public void addTakeaway() { takeaway = true; }

    public double priceForSingle() {
        double price = basePrice;

        switch (size) {
            case S: price = basePrice - 20; break;
            case M: price = basePrice; break;
            case L: price = basePrice + 40; break;
        }

        if (extraCheeseAdded) price += EXTRA_CHEESE;
        if (extraToppingsAdded) price += EXTRA_TOPPINGS;
        if (takeaway) price += TAKEAWAY;

        return Math.round(price * 100.0) / 100.0;
    }

    public String getName() { return name; }
    public boolean isVeg() { return veg; }
    public Size getSize() { return size; }
    public boolean isExtraCheeseAdded() { return extraCheeseAdded; }
    public boolean isExtraToppingsAdded() { return extraToppingsAdded; }
    public boolean isTakeaway() { return takeaway; }
}
