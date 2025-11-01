import java.io.*;
import java.util.*;

public class ProductService {
    private List<Product> products = new ArrayList<>();
    private final String FILE = "data/products.txt";

    public ProductService() {
        // Try load from data/products.txt; if missing or empty, use defaults
        boolean loaded = loadFromFile();
        if (!loaded) {
            products.add(new Product(1, "Laptop", 55000));
            products.add(new Product(2, "Smartphone", 25000));
            products.add(new Product(3, "Headphones", 2000));
            products.add(new Product(4, "Smartwatch", 4000));
            // Save defaults for convenience
            saveDefaults();
        }
    }

    private boolean loadFromFile() {
        File f = new File(FILE);
        if (!f.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                // format: id,name,price
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    products.add(new Product(id, name, price));
                } catch (NumberFormatException e) {
                    // skip bad lines
                }
            }
            return !products.isEmpty();
        } catch (IOException e) {
            return false;
        }
    }

    private void saveDefaults() {
        File f = new File(FILE);
        try {
            f.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                for (Product p : products) {
                    bw.write(p.getId() + "," + p.getName() + "," + p.getPrice());
                    bw.newLine();
                }
            }
        } catch (IOException ignored) {}
    }

    public void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }
}
