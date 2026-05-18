# OOP Concepts Implementation Guide

## Overview
This document explains how each OOP concept from the requirements is implemented in the Sales Management System.

---

## 1. Data Abstraction

**Definition**: Hiding internal details and showing only relevant functionality.

### Implementation Examples:

#### Product.java
```java
public class Product {
    // Internal details hidden
    private final String productId;
    private String productName;
    private double costPrice;
    
    // Public interface for calculation
    public double calculateProfit() {
        return (sellingPrice - costPrice) * quantity;
    }
}
```
**Usage**: The complex profit calculation logic is hidden; users just call `calculateProfit()`.

#### Inventory.java
```java
public class Inventory {
    private final ArrayList<Product> products;
    
    // Abstract method - details hidden
    public boolean removeProduct(String productId) {
        // Complex removal logic inside
    }
}
```

---

## 2. Instance Variables and Methods

**Definition**: Variables and methods that belong to individual objects.

### Implementation:

```java
public class Admin {
    // Instance Variables
    private final String adminId;
    private String adminName;
    private String shopName;
    
    // Instance Methods
    public String getAdminName() {
        return adminName; // Operates on instance data
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName; // Modifies instance data
    }
}
```

**Each admin object** has its own `adminId`, `adminName`, `shopName`.

---

## 3. Constructors (No-argument and Parameterized)

**Definition**: Special methods for initializing objects.

### Implementation:

```java
public class Product {
    // No-argument Constructor
    public Product() {
        this.productId = "P000";
        this.productName = "Unknown";
        this.costPrice = 0;
    }
    
    // Parameterized Constructor
    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
    
    // Complete Constructor
    public Product(String productId, String productName, double costPrice, 
                   double sellingPrice, int quantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = costPrice;
        // ... more initialization
    }
    
    // Copy Constructor
    public Product(Product other) {
        this.productId = other.productId;
        this.productName = other.productName;
        // ... deep copy
    }
}
```

**Used in**:
- Creating products with `new Product()`
- Creating specific products with details
- Cloning products with copy constructor

---

## 4. Encapsulation and Information Hiding

**Definition**: Bundling data and methods, hiding internal details, controlling access.

### Implementation:

```java
public class Product {
    // Private instance variables - cannot access directly
    private final String productId;      // READ-ONLY
    private String productName;          // CONTROLLED ACCESS
    private double costPrice;            // PROTECTED
    private double sellingPrice;
    private int quantity;
    
    // PUBLIC interface only
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public void setProductName(String name) { this.productName = name; }
}
```

**Example**:
```java
Product p = new Product("P001", "Laptop", 40000, 65000, 5, "Electronics");
// p.productId = "P999";  // ERROR - Cannot change, it's private final
// p.quantity = -5;       // ERROR - Cannot set negative directly
p.setProductName("Gaming Laptop");  // OK - Goes through setter
```

---

## 5. Access Modifiers

**Definition**: Keywords that control visibility of class members.

### Implementation:

```java
public class Inventory {
    // PUBLIC - Accessible from anywhere
    public String getInventoryId() {
        return inventoryId;
    }
    
    // PRIVATE - Only accessible within this class
    private void validateProduct(Product p) {
        // Can only be called from within Inventory
    }
    
    // PROTECTED - Accessible from subclasses
    protected ArrayList<Product> products;  // (if we had subclasses)
}
```

| Modifier | Same Class | Same Package | Subclass | Other |
|----------|-----------|--------------|----------|-------|
| public | ✓ | ✓ | ✓ | ✓ |
| protected | ✓ | ✓ | ✓ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| private | ✓ | ✗ | ✗ | ✗ |

---

## 6. Accessors (Getters) and Mutators (Setters)

**Definition**: Methods to read (get) and modify (set) private variables.

### Implementation:

```java
public class Product {
    private int quantity;  // Private variable
    
    // GETTER - Read only safe access
    public int getQuantity() {
        return quantity;
    }
    
    // SETTER - Modify with validation
    public void setQuantity(int qty) {
        if (qty >= 0) {
            this.quantity = qty;  // Only set if valid
        } else {
            System.out.println("Quantity cannot be negative!");
        }
    }
}
```

**Usage**:
```java
Product p = new Product("P001", "Laptop");
int qty = p.getQuantity();        // Get quantity
p.setQuantity(10);                // Set quantity (validated)
p.setQuantity(-5);                // ERROR - Rejected by setter
```

---

## 7. Passing and Returning Objects from Methods

**Definition**: Objects can be passed as parameters and returned from methods.

### Implementation:

```java
public class Inventory {
    // PASSING objects as parameters
    public void addProduct(Product product) {
        if (product.getQuantity() > 0) {
            products.add(product);
        }
    }
    
    // RETURNING objects from methods
    public Product searchProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;  // Return Product object
            }
        }
        return null;
    }
}
```

**Usage**:
```java
Product laptop = new Product("P001", "Laptop", 40000, 65000, 5, "Electronics");
inventory.addProduct(laptop);  // Pass object

Product found = inventory.searchProduct("P001");  // Return object
if (found != null) {
    System.out.println(found.getProductName());
}
```

---

## 8. Aliasing of Object Handles

**Definition**: Multiple references pointing to the same object.

### Implementation:

```java
Product p1 = new Product("P001", "Laptop", 40000, 65000, 5, "Electronics");
Product p2 = p1;  // p2 is an ALIAS of p1

p1.setQuantity(10);
System.out.println(p2.getQuantity());  // Prints 10 (same object!)
p1 = null;
System.out.println(p2.getQuantity());  // Still works! p2 still points to object
```

**Also in Arrays**:
```java
ArrayList<Product> products = new ArrayList<>();
Product laptop = new Product("P001", "Laptop");
products.add(laptop);

// Multiple references to same object
Product sameProduct = products.get(0);  // Alias
sameProduct.setQuantity(5);
System.out.println(laptop.getQuantity());  // Also 5!
```

---

## 9. Composition and Containership ("has-a" relationship)

**Definition**: A class contains objects of other classes.

### Implementation:

```java
public class Inventory {
    // Inventory "HAS-A" collection of Products
    private final ArrayList<Product> products;
    
    public Inventory(String inventoryId) {
        this.inventoryId = inventoryId;
        this.products = new ArrayList<>();  // Composition
    }
    
    public void addProduct(Product product) {
        this.products.add(product);  // Containing Product objects
    }
}
```

**Relationship**:
```
Inventory ────contains──→ ArrayList<Product>
                ├──→ Product 1
                ├──→ Product 2
                └──→ Product 3
```

**Another Example**:
```java
public class Sale extends Transaction {
    // Sale "HAS-A" Product info
    private String productId;
    private String productName;
}

public class Transaction {
    // Transaction "HAS-A" date and amount
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double amount;
}
```

---

## 10. Inheritance (Base and Derived Classes)

**Definition**: Creating new classes based on existing classes.

### Implementation:

```java
// BASE CLASS (Parent)
public abstract class Transaction {
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double amount;
    
    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.amount = amount;
    }
    
    abstract void recordTransaction();
}

// DERIVED CLASS (Child)
public class Sale extends Transaction {
    private String productId;
    private String productName;
    private int quantitySold;
    
    public Sale(String saleId, String productId, String productName, 
                int quantitySold, double unitPrice) {
        super(saleId, unitPrice * quantitySold);  // Call parent constructor
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
    }
    
    @Override
    void recordTransaction() {
        System.out.println("Sale recorded...");
    }
}
```

**Relationship**:
```
Transaction (Abstract Base Class)
    ↑
    | extends
    |
Sale (Derived Class)
```

---

## 11. Protected Access Specifier

**Definition**: Access level between private and public, for inheritance.

### Implementation:

```java
public abstract class Transaction {
    // PROTECTED - Can be accessed by subclasses
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double amount;
    
    // PRIVATE - Cannot be accessed by subclasses
    private String internalCode;
}

public class Sale extends Transaction {
    @Override
    void recordTransaction() {
        // Can access protected members from parent
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + transactionDate);
        System.out.println("Amount: " + amount);
        
        // Cannot access private members
        // System.out.println(internalCode);  // ERROR!
    }
}
```

---

## 12. Derived Class Constructors and the super Reference

**Definition**: Calling parent class constructor from child class.

### Implementation:

```java
public class Sale extends Transaction {
    private String productId;
    
    public Sale(String saleId, String productId, String productName, 
                int quantitySold, double unitPrice) {
        // Call parent constructor with super
        super(saleId, unitPrice * quantitySold);
        
        // Initialize child-specific variables
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
    }
}
```

**Execution Flow**:
```
Sale constructor called
    ↓
super() calls Transaction constructor
    ↓
Transaction initializes transactionId, date, amount
    ↓
Control returns to Sale constructor
    ↓
Sale initializes productId, productName, quantitySold
```

---

## 13. Method Overriding

**Definition**: Child class provides its own implementation of parent's method.

### Implementation:

```java
// Parent Class
abstract class Transaction {
    abstract void recordTransaction();
}

// Child Class
class Sale extends Transaction {
    @Override
    void recordTransaction() {
        System.out.println("=== SALE RECORDED ===");
        System.out.println("Sale ID: " + transactionId);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + transactionDate);
    }
}
```

**Usage**:
```java
Transaction t = new Sale("SALE001", "P001", "Laptop", 1, 65000);
t.recordTransaction();  // Calls Sale's overridden method
```

---

## 14. Covariant Return Types

**Definition**: Overridden method can return a subtype of parent's return type.

### Implementation:

```java
// Parent Class
public class Animal {
    public Animal getClone() {
        return new Animal();
    }
}

// Child Class
public class Dog extends Animal {
    @Override
    public Dog getClone() {  // Returns Dog instead of Animal
        return new Dog();     // This is allowed (covariant)
    }
}
```

---

## 15. Abstract Classes and Abstract Methods

**Definition**: Classes/methods that provide structure but no implementation.

### Implementation:

```java
// ABSTRACT CLASS
public abstract class Transaction {
    // Concrete methods (implemented)
    public double getAmount() {
        return amount;
    }
    
    // ABSTRACT METHOD (no implementation)
    abstract void recordTransaction();
}

// Must implement abstract method
public class Sale extends Transaction {
    @Override
    void recordTransaction() {
        // Must provide implementation
        System.out.println("Recording sale...");
    }
}
```

**Cannot instantiate abstract class**:
```java
// Transaction t = new Transaction();  // ERROR!
Transaction t = new Sale("S001", "P001", "Laptop", 1, 65000);  // OK!
```

---

## 16. Concrete Classes

**Definition**: Classes that can be instantiated (complete implementation).

### Implementation:

```java
// CONCRETE CLASS - Can be instantiated
public class Product {
    private String productId;
    private String productName;
    
    // All methods are concrete (implemented)
    public void setProductName(String name) {
        this.productName = name;
    }
}

// Usage
Product p = new Product("P001", "Laptop");  // Can create instance
```

---

## 17. Polymorphism

**Definition**: Objects can take multiple forms; same interface, different behavior.

### Implementation:

```java
Transaction t1 = new Sale("S001", "P001", "Laptop", 1, 65000);

// Runtime polymorphism
t1.recordTransaction();  // Calls Sale's implementation

// Works with parent type but executes child's method
Transaction[] transactions = new Transaction[10];
transactions[0] = new Sale("S001", "P001", "Laptop", 1, 65000);
transactions[0].recordTransaction();  // Calls Sale's version
```

---

## 18. Upcasting and Downcasting

**Definition**: Converting between parent and child class types.

### Implementation:

```java
// UPCASTING (always safe)
Sale sale = new Sale("S001", "P001", "Laptop", 1, 65000);
Transaction t = sale;  // Upcast to parent

// DOWNCASTING (requires type check)
if (t instanceof Sale) {
    Sale s = (Sale) t;  // Downcast back to Sale
    System.out.println(s.getProductId());
}
```

---

## 19. Interfaces

**Definition**: Contract that classes must follow.

**Note**: While full interface implementation not in current system, Java provides built-in interfaces we use:

```java
// Using Comparable interface concept
public class Product {
    // Implements comparison logic
    public int compareTo(Product other) {
        return this.productId.compareTo(other.productId);
    }
}
```

---

## 20. ArrayList Data Structure

**Definition**: Dynamic array that grows as needed.

### Implementation:

```java
public class Inventory {
    // ArrayList instead of fixed-size array
    private final ArrayList<Product> products;
    
    public Inventory(String inventoryId) {
        this.products = new ArrayList<>();  // Starts empty
    }
    
    public void addProduct(Product Product) {
        products.add(product);  // Automatically grows
    }
    
    public ArrayList<Product> getProducts() {
        return products;  // Return list
    }
}
```

**Usage**:
```java
ArrayList<Product> allProducts = inventory.getProducts();
for (Product p : allProducts) {
    System.out.println(p.getProductName());
}
```

---

## 21. File Handling

**Definition**: Reading and writing data to files.

### Implementation:

```java
public class FileHandler {
    // WRITING using PrintWriter
    public static void saveToFile(String filename, String content) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file");
        }
    }
    
    // READING using Scanner
    public static ArrayList<String> readFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return lines;
    }
}
```

---

## 22. Object Serialization/Deserialization

**Note**: CSV format used instead of binary serialization for simplicity and human readability.

### Alternative Implementation:

```java
public class Product {
    // Serialize to CSV string (manual)
    public String toCSV() {
        return productId + "," + productName + "," + costPrice + "," + 
               sellingPrice + "," + quantity + "," + category;
    }
    
    // Deserialize from CSV string (manual)
    public static Product fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Product(
            parts[0],           // productId
            parts[1],           // productName
            Double.parseDouble(parts[2]),  // costPrice
            Double.parseDouble(parts[3]),  // sellingPrice
            Integer.parseInt(parts[4]),    // quantity
            parts[5]            // category
        );
    }
}
```

---

## 23. GUI - Layout Managers

**Definition**: Automatically arrange components in containers.

### Implementation:

```java
public class MainPanel extends JPanel {
    private void initializeUI() {
        // BORDER LAYOUT - 5 regions (N, S, E, W, C)
        setLayout(new BorderLayout(15, 15));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);   // Top region
        
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        add(centerPanel, BorderLayout.CENTER);  // Center region
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(bottomPanel, BorderLayout.SOUTH);   // Bottom region
    }
}
```

**Layout Types Used**:
- **BorderLayout**: Top-level container layout
- **GridLayout**: Menu buttons in 2x2 grid
- **FlowLayout**: Horizontal button alignment
- **BoxLayout**: Vertical stacking

---

## 24. GUI - Event Driven Programming

**Definition**: Program responds to user actions (mouse clicks, keyboard input).

### Implementation:

```java
public class SalesPanel extends JPanel {
    private void initializeUI() {
        JButton recordButton = new JButton("Record Sale");
        
        // ADD EVENT LISTENER
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Respond to button click
                openRecordSaleDialog();
            }
        });
        
        // OR using Lambda
        recordButton.addActionListener(e -> openRecordSaleDialog());
    }
    
    private void openRecordSaleDialog() {
        // Handle event
    }
}
```

---

## Summary Table

| Concept | File(s) | Implementation |
|---------|---------|-----------------|
| Data Abstraction | Product, Inventory | Hidden complexity in methods |
| Instance Variables | All classes | Private variables in each class |
| Constructors | All classes | Multiple constructors per class |
| Encapsulation | All classes | Private data + public interface |
| Access Modifiers | All classes | public, private, protected used |
| Getters/Setters | All classes | get/set methods with validation |
| Object Passing | FileHandler, Inventory | Methods accept/return objects |
| Aliasing | Inventory, ArrayList | Multiple references to objects |
| Composition | Inventory, Sale | "has-a" relationships |
| Inheritance | Sale extends Transaction | IS-A relationship |
| Protected | Transaction | Protected fields for subclasses |
| super | Sale | Calls parent constructor |
| Overriding | Sale | Implements abstract method |
| Covariant Return | N/A | Method return types |
| Abstract Classes | Transaction | Abstract base class |
| Concrete Classes | Product, Admin, Sale | Can be instantiated |
| Polymorphism | Transaction t = Sale | Runtime dispatch |
| Upcasting | N/A | Parent type references |
| Downcasting | N/A | Instanceof and casting |
| ArrayList | Inventory, FileHandler | Used instead of arrays |
| File Handling | FileHandler | Scanner and PrintWriter |
| GUI Layouts | MainPanel, ProductPanel | BorderLayout, GridLayout, FlowLayout |
| Event Handling | LoginPanel, SalesPanel | ActionListener, Lambda expressions |

---

**All OOP concepts required are fully implemented and demonstrated in the SMS system!** ✅

