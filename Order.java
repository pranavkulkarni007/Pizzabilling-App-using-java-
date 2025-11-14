import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> items = new ArrayList<>();
    private double gstPercent = 5.0;
    private double discountPercent = 0.0;

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void applyCoupon(String coupon) {
        if (coupon == null || coupon.isEmpty()) return;

        coupon = coupon.trim().toUpperCase();

        if (coupon.startsWith("PZ")) {
            try {
                int percent = Integer.parseInt(coupon.substring(2));
                discountPercent = percent;
            } catch (Exception e) {
                // Invalid coupon → ignored
            }
        }
    }

    public double subTotal() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.lineTotal();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    public double discountAmount() {
        double amount = (subTotal() * discountPercent) / 100.0;
        return Math.round(amount * 100.0) / 100.0;
    }

    public double gstAmount(double afterDiscount) {
        double amount = (afterDiscount * gstPercent) / 100.0;
        return Math.round(amount * 100.0) / 100.0;
    }

    public String generateItemizedBill() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== ITEMIZED BILL ===\n");
        sb.append(String.format("%-4s %-20s %-6s %-6s %-8s\n",
                "QTY", "ITEM", "SIZE", "PRICE", "TOTAL"));
        sb.append("------------------------------------------------\n");

        for (OrderItem item : items) {
            Pizza p = item.getPizza();

            sb.append(String.format("%-4d %-20s %-6s %-6.2f %-8.2f\n",
                    item.getQuantity(),
                    p.getName() + (p.isVeg() ? " (V)" : " (NV)"),
                    p.getSize(),
                    p.priceForSingle(),
                    item.lineTotal()
            ));

            if (p.isExtraCheeseAdded() || p.isExtraToppingsAdded() || p.isTakeaway()) {
                sb.append("      Extras:");
                if (p.isExtraCheeseAdded()) sb.append(" +Cheese");
                if (p.isExtraToppingsAdded()) sb.append(" +Toppings");
                if (p.isTakeaway()) sb.append(" +Pack");
                sb.append("\n");
            }
        }

        double subtotal = subTotal();
        sb.append("------------------------------------------------\n");
        sb.append(String.format("%-30s : ₹%.2f\n", "Subtotal", subtotal));

        if (discountPercent > 0) {
            double dis = discountAmount();
            sb.append(String.format("%-30s : -₹%.2f (%s%%)\n",
                    "Discount", dis, (int) discountPercent));
            subtotal -= dis;
        }

        double gst = gstAmount(subtotal);
        sb.append(String.format("%-30s : ₹%.2f (GST %.1f%%)\n", "Tax", gst, gstPercent));

        double grandTotal = subtotal + gst;
        sb.append(String.format("%-30s : ₹%.2f\n", "GRAND TOTAL", grandTotal));
        sb.append("-------------------\n");

        return sb.toString();
    }
}
