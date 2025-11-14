import java.util.Scanner;

public class PizzaBillApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to Pranav's Pizza Shop ===");

        Menu menu = new Menu();
        menu.addSampleItems();

        Order order = new Order();
        boolean ordering = true;

        while (ordering) {

            menu.printMenu();

            System.out.print("Enter pizza id to add to cart (or 0 to finish): ");
            int id = readInt(sc);

            if (id == 0) break;

            PizzaMenuItem item = menu.getById(id);

            if (item == null) {
                System.out.println("Invalid id! Try again.\n");
                continue;
            }

            System.out.println("Selected: " + item.getName());

            System.out.print("Choose size (S/M/L) [default M]: ");
            String sizeInput = sc.next().trim().toUpperCase();
            Pizza.Size size = Pizza.Size.fromChar(sizeInput.isEmpty() ? "M" : sizeInput);

            System.out.print("Quantity: ");
            int qty = readInt(sc);
            if (qty <= 0) qty = 1;

            boolean extraCheese = askYesNo(sc, "Add extra cheese (₹50)? (y/n): ");
            boolean extraToppings = askYesNo(sc, "Add extra toppings (₹70)? (y/n): ");
            boolean takeaway = askYesNo(sc, "Takeaway packing (₹20)? (y/n): ");

            Pizza pizza = new Pizza(item.getName(), item.getBasePrice(), item.isVeg(), size);

            if (extraCheese) pizza.addExtraCheese();
            if (extraToppings) pizza.addExtraToppings();
            if (takeaway) pizza.addTakeaway();

            order.addItem(new OrderItem(pizza, qty));

            System.out.println("Added to cart.\n");
        }

        if (order.getItems().isEmpty()) {
            System.out.println("No items selected. Goodbye!");
            sc.close();
            return;
        }

        System.out.print("Apply coupon? (e.g., PZ10 for 10% off) or press Enter: ");
        sc.nextLine(); 
        String coupon = sc.nextLine().trim();
        order.applyCoupon(coupon);

        System.out.println("\n--- Generating Bill ---");
        System.out.println(order.generateItemizedBill());
        System.out.println("Thank you! Visit again.");

        sc.close();
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please enter a number: ");
        }
        return sc.nextInt();
    }

    private static boolean askYesNo(Scanner sc, String prompt) {
        System.out.print(prompt);
        String ans = sc.next().trim().toLowerCase();
        return ans.equals("y") || ans.equals("yes");
    }
}
