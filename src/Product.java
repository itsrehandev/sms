public class Product {
    private final String productId;
    private String productName;
    private double costPrice;
    private double sellingPrice;
    private int quantity;
    private final String category;

    private static int totalProducts = 0;
    // Constructor 1: Default
    public Product() {
        this.productId = "P000";
        this.productName = "Unknown";
        this.costPrice = 0;
        this.sellingPrice = 0;
        this.quantity = 0;
        this.category = "General";
    }
    
    // Constructor 2: Basic info
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = 0;
        this.sellingPrice = 0;
        this.quantity = 0;
        this.category = "General";
    }
    
    // Constructor 3: Complete info
    public Product(String productId, String productName, double costPrice, 
                   double sellingPrice, int quantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.category = category;
    }
    
    // Constructor 4: Copy Constructor
    public Product(Product other) {
        this.productId = other.productId;
        this.productName = other.productName;
        this.costPrice = other.costPrice;
        this.sellingPrice = other.sellingPrice;
        this.quantity = other.quantity;
        this.category = other.category;
    }
    
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public double getCostPrice() {
        return costPrice;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
    
    // setters with Validation
    public void setSellingPrice(double price) {
        if (price > 0 && price > costPrice/2) {
            this.sellingPrice = price;
        } else {
            System.out.println("Selling price must be greater than half of cost price!");
        }
    }
    
    public void setQuantity(int qty) {
        if (qty >= 0) {
            this.quantity = qty;
        } else {
            System.out.println("Quantity cannot be negative!");
        }
    }
    
    // Profit calculation - with no parameters
    public double calculateProfit() {
        return (sellingPrice - costPrice) * quantity;
    }
    
    // Profit calculation - with discount parameter
    public double calculateProfit(double discountPercent) {
        double discountAmount = (sellingPrice * discountPercent) / 100;
        double finalPrice = sellingPrice - discountAmount;
        return (finalPrice - costPrice) * quantity;
    }

    // Static variable getter
    public static int getTotalProducts() {
        return totalProducts;
    }
    // Static method to reset counter
    public static void resetProductCounter() {
        totalProducts = 0;
    }

    final public void printProductInfo() {
        System.out.println("\n========== PRODUCT INFO ==========");
        System.out.println("ID: " + this.productId);
        System.out.println("Name: " + this.productName);
        System.out.println("Cost Price: Rs. " + this.costPrice);
        System.out.println("Selling Price: Rs. " + this.sellingPrice);
        System.out.println("Quantity: " + this.quantity);
        System.out.println("Category: " + this.category);
        System.out.println("Profit per unit: Rs. " + (this.sellingPrice - this.costPrice));
        System.out.println("Total Profit: Rs. " + this.calculateProfit());
        System.out.println("==================================\n");
    }
}