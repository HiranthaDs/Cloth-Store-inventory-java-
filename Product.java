
public class Product {
    private String name;
    private String description;
    private String size;
    private String color;
    private double price;
    private int quantityInStock;

    public Product(String name, String description, String size, String color, double price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }

    public void displayProduct() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Price: $" + price);
        System.out.println("Stock Quantity: " + quantityInStock);
    }

    @Override
    public String toString() {
        return name + " - " + description + " - " + size + " - " + color + " - $" + price + " - " + quantityInStock + " items in stock";
    }
}
