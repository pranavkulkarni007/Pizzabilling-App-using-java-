import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<PizzaMenuItem> items = new ArrayList<>();

    public void addSampleItems() {
        items.add(new PizzaMenuItem(1, "Margherita", 200.0, true));
        items.add(new PizzaMenuItem(2, "Farmhouse", 350.0, true));
        items.add(new PizzaMenuItem(3, "Pepperoni", 400.0, false));
        items.add(new PizzaMenuItem(4, "Chicken Dominator", 420.0, false));
    }

    public void printMenu() {
        System.out.println("\n--- MENU ---");
        for (PizzaMenuItem it : items) {
            System.out.printf("%d) %s - ₹%.2f %s\n",
                    it.getId(),
                    it.getName(),
                    it.getBasePrice(),
                    it.isVeg() ? "(V)" : "(NV)"
            );
        }
    }

    public PizzaMenuItem getById(int id) {
        for (PizzaMenuItem it : items) {
            if (it.getId() == id) return it;
        }
        return null;
    }
}
