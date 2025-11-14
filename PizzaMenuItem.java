public class PizzaMenuItem {
    private int id;
    private String name;
    private double basePrice;
    private boolean veg;

    public PizzaMenuItem(int id, String name, double basePrice, boolean veg) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.veg = veg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public boolean isVeg() {
        return veg;
    }
}
