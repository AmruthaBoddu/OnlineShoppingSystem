import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        Cart cart = new Cart();

        System.out.println("===== Welcome to Online Shopping System =====");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty. Exiting.");
            sc.close();
            return;
        }

        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("No user found. Creating a new account...");
            userService.register(username, password);
        } else {
            System.out.println("Login successful. Welcome back, " + user.getUsername() + "!");
        }

        int choice;
        do {
            System.out.println("\n1. View Products\n2. Add to Cart\n3. View Cart\n4. Checkout\n5. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    productService.displayProducts();
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter a number: ");
                        sc.next();
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    Product p = productService.getProductById(id);
                    if (p != null) {
                        cart.addProduct(p);
                        System.out.println(p.getName() + " added to cart.");
                    } else {
                        System.out.println("Invalid ID!");
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    cart.checkout();
                    System.out.println("Thank you for shopping!");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
