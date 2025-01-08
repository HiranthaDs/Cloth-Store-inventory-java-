
public class Inventory {
    public static void main(String[] args) {
        Product product1 = new Product("T-Shirt", "Cotton T-shirt", "M", "Red", 19.99, 50);
        Product product2 = new Product("Jeans", "Denim Jeans", "L", "Blue", 39.99, 30);
        Product product3 = new Product("Jacket", "Winter Jacket", "M", "Black", 59.99, 20);

        System.out.println("Product 1 Details:");
        product1.displayProduct();
        System.out.println("\nProduct 2 Details:");
        product2.displayProduct();
        System.out.println("\nProduct 3 Details:");
        product3.displayProduct();

        System.out.println("\nUsing toString():");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
    }
}
