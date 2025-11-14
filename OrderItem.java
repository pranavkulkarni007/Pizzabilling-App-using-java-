public class OrderItem {

    private Pizza pizza;
    private int quantity;

    public OrderItem(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public double lineTotal() {
        double singlePrice = pizza.priceForSingle();
        double total = singlePrice * quantity;
        return Math.round(total * 100.0) / 100.0;
    }
}
