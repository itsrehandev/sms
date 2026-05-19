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
        totalProducts++ ; 
    }
    
    // Constructor 2: Basic info
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = 0;
        this.sellingPrice = 0;
        this.quantity = 0;
        this.category = "General";
        totalProducts++;
    }
    
    // Constructor 3: Complete info
    public Product(String productId, String productName, double costPrice, double sellingPrice, int quantity, String category) 
    {
        this.productId = productId;

        //Syed Mujahid After Mids
        if(productName.isEmpty())
        {
            System.out.println("Product Name cannot be Empty");
            this.productName = "Unknown";
        }
        else
        {
            this.productName = productName;
        }

        if(costPrice <= 0)
            
        {
            System.out.println("price should be greater than 0");
        }
        else
            
        {
            this.costPrice = costPrice;
        }
        if(sellingPrice < 0)
        {
            System.out.println("Selling Price cannot be Negative");
        }
        else
        {
            this.sellingPrice = sellingPrice;
        }
        if(quantity < 0)
        {
            System.out.println("Quantity cannot be negative");
        }
        else
        {
            this.quantity = quantity;
        }
        totalProducts++ ;
        //Syed Mujahid Ends
        this.category = category;
    }
    
    // Copy Constructor
    public Product(Product other) {
        this.productId = other.productId;
        this.productName = other.productName;
        this.costPrice = other.costPrice;
        this.sellingPrice = other.sellingPrice;
        this.quantity = other.quantity;
        this.category = other.category;
        totalProducts++;
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
        if (price > 0 ) {
            this.sellingPrice = price;
        } else {
            System.out.println("Selling price must be Positive!");
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
        System.out.println("Total Products Created: " + totalProducts);
        System.out.println("==================================\n");
        
    }


//Syed Mujahid after Mids

public void setProductName(String productName)
{
    if(!productName.isEmpty())
    {
        this.productName = productName;
    }
    
}

public void increaseStock(int qty)
{
    if(qty > 0)
    {
        quantity += qty;
    }
}

public boolean decreaseStock(int qty)
{
    if(qty > 0 && qty <= quantity)
    {
        quantity -= qty;
        return true;
    }
    return false;
}

public String toCSV()
{
    return productId + "," +productName + "," +costPrice + "," +sellingPrice + "," +quantity + "," +category;
}



}