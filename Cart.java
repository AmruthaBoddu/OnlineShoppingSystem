import java.util.*;

public class Cart {
    private List<Product> cartItems = new ArrayList<>();

    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\nYour Cart:");
        for (Product p : cartItems) {
            System.out.println(p.getName() + " - ₹" + p.getPrice());
        }
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }
        double total = 0;
        for (Product p : cartItems)
            total += p.getPrice();
        System.out.println("\nTotal Bill: ₹" + total);
        cartItems.clear();
    }
}
