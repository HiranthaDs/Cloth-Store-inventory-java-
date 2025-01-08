// File: Database.java

import java.util.ArrayList;
import java.util.List;

public class Database {
    // Simulated product list (as an in-memory database)
    private static List<Product> productList = new ArrayList<>();

    // Add a product to the database
    public static void addProduct(Product product) {
        productList.add(product);
    }

    // Get all products in the database
    public static List<Product> getAllProducts() {
        return productList;
    }

    // Delete a product by name
    public static boolean deleteProduct(String productName) {
        return productList.removeIf(product -> product.getName().equals(productName));
    }

    // Update a product in the database
    public static boolean updateProduct(String oldName, Product newProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(oldName)) {
                productList.set(i, newProduct);
                return true;
            }
        }
        return false;
    }
}
