public class Sale extends Transaction {
    private String productId;
    private String productName;
    private int quantitySold;
    private double unitPrice;
    private double discountPercent;
    private String customerName;

    // Constructor 1: Basic sale
    public Sale(String saleId, String productId, String productName, 
                int quantitySold, double unitPrice) {
        super(saleId, unitPrice * quantitySold);
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.discountPercent = 0;
        this.customerName = "Guest Customer";
    }
    
    // Constructor 2: With discount
    public Sale(String saleId, String productId, String productName, 
                int quantitySold, double unitPrice, double discountPercent) {
        super(saleId, unitPrice * quantitySold * (1 - discountPercent/100));
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.discountPercent = discountPercent;
        this.customerName = "Guest Customer";
    }
    
    // Constructor 3: Complete information
    public Sale(String saleId, String productId, String productName, 
                int quantitySold, double unitPrice, double discountPercent,
                String customerName) {
        super(saleId, unitPrice * quantitySold * (1 - discountPercent/100));
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.discountPercent = discountPercent;
        this.customerName = customerName;
    }
    
    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + super.getTransactionId());
        System.out.println("Date: " + super.getTransactionDate());
        System.out.println("Amount: Rs. " + super.getAmount());
    }
    
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantitySold() {
        return quantitySold;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }
    public String getCustomerName() {
        return customerName;
    }
    

    // Calculate total price without discount
    public double calculateTotal() {
        return unitPrice * quantitySold;
    }
    // Calculate total with discount
    public double calculateTotal(boolean applyDiscount) {
        if (applyDiscount) {
            double discountAmount = (unitPrice * quantitySold * discountPercent) / 100;
            return (unitPrice * quantitySold) - discountAmount;
        }
        return unitPrice * quantitySold;
    }
    
    @Override
    void recordTransaction() {
        System.out.println("\n========== SALE RECORDED ==========");
        System.out.println("Sale ID: " + transactionId);
        System.out.println("Product: " + productName + " (" + productId + ")");
        System.out.println("Quantity: " + quantitySold);
        System.out.println("Unit Price: Rs. " + unitPrice);
        System.out.println("Discount: " + discountPercent + "%");
        System.out.println("Total Amount: Rs. " + amount);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + transactionDate);
        System.out.println("===================================\n");
    }
    
}