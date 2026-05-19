import java.util.ArrayList;

public class Inventory {
    private final String inventoryId;
    private final ArrayList<Product> products;
    private static int totalInventories = 0;
    
    public Inventory(String inventoryId) {
        this.inventoryId = inventoryId;
        this.products = new ArrayList<>();
    }
    
    //  Copy Constructor
    public Inventory(Inventory other) {
        this.inventoryId = other.inventoryId;
        this.products = new ArrayList<>();
        // Deep copy each product
        for (Product p : other.products) {
            this.products.add(new Product(p));
        }
    }
    
    public String getInventoryId() {
        return inventoryId;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    
    // Add product - takes Product object
    public void addProduct(Product product) {
        if (product.getQuantity() > 0) {
            products.add(product);
            System.out.println(product.getProductName()+" Added.");
        } else {
            System.out.println("Cannot add product with zero quantity!");
        }
    }
    
    // Add product - takes individual parameters
    public void addProduct(String id, String name, double cost, 
                          double selling, int qty, String category) {
        Product p = new Product(id, name, cost, selling, qty, category);
        addProduct(p);
    }
    
    // Search by Product ID
    public Product searchProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        System.out.println("Product not found!");
        return null;
    }
    
    // Search by Product Name
    public Product searchProduct(String productName, boolean byName) {
        if (byName) {
            for (Product p : products) {
                if (p.getProductName().equalsIgnoreCase(productName)) {
                    return p;
                }
            }
        }
        return null;
    }
    
    public static int getTotalInventories() {
        return totalInventories;
    }
    
    // Get low stock products with custom limit
    public ArrayList<Product> getLowStockProducts(int limit) {
        ArrayList<Product> lowStock = new ArrayList<>();
        for (Product p : products) {
            if (p.getQuantity() < limit) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }
    
    public double getTotalInventoryValue() {
        double total = 0;
        for (Product p : products) {
            total += p.getSellingPrice() * p.getQuantity();
        }
        return total;
    }
    
    // Remove product
    public void removeProduct(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.remove(i);
                System.out.println("Product removed!");
            }
        }
    }
    
    // Print all products
    public void displayAllProducts() {
        System.out.println("\n          INVENTORY: " + inventoryId);
        if (products.isEmpty()) {
            System.out.println("No products in inventory!");
        } else {
            for (Product p : products) {
                System.out.println(p.getProductId() + " | " + p.getProductName() + 
                                 " | Qty: " + p.getQuantity() + 
                                 " | Rs. " + p.getSellingPrice());
            }
        }
        System.out.println("Total Value: Rs. " + this.getTotalInventoryValue());
    }
}