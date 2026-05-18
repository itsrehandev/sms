# Sales Management System (SMS)

## Project Overview
A complete **Sales Management System** built using **Java Swing GUI** and **OOP Concepts**. This system allows multiple admins to manage shop sales, inventory, transactions, and generate monthly reports.

---

## Key Features

### 1. **Admin Authentication**
- Multiple admins can login with credentials stored in `LoginCredentials.csv`
- Secured login panel with error messages
- Session-based dashboard per admin

### 2. **Product Management**
- **Add Products**: Create new products with details (ID, Name, Cost Price, Selling Price, Quantity, Category)
- **View Products**: Display all products in inventory with stock information
- **Remove Products**: Delete products from inventory
- **Stock Management**: Track stock levels for each product

### 3. **Sales Recording**
- **Record Sales Transactions**: Log sales by product ID and quantity
- **Calculate Totals**: Automatically calculate sale amounts with optional discounts
- **Stock Deduction**: Automatically reduce stock when sale is recorded
- **Transaction History**: View recent sales transactions
- **CSV Storage**: All transactions saved to `Sales.csv`

### 4. **Reporting System**
- **Monthly Reports**: Generate detailed sales reports for current month
- **Previous Month Reports**: View reports from previous months
- **Report Export**: Export reports to desktop as formatted text files
- **Statistics**: Display total sales amount, items sold, and transaction count

### 5. **Data Persistence**
- **CSV File Storage**: All data stored in simple CSV format
- **Products.csv**: Inventory data
- **Sales.csv**: Sales transaction data
- **LoginCredentials.csv**: Admin login credentials
- **Easy Data Access**: Simple `.split()` logic for CSV parsing

---

## System Architecture

### Core Classes

#### 1. **Product** (Product.java)
- Represents individual products
- Properties: ID, Name, Cost Price, Selling Price, Quantity, Category
- Methods: Getters, Setters, Stock management, CSV conversion
- **OOP Concepts**: Data Abstraction, Encapsulation, Constructors, Access Modifiers

#### 2. **Inventory** (Inventory.java)
- Manages collection of products
- Uses ArrayList for product storage
- Methods: Add/Search/Remove products, Calculate inventory value
- **OOP Concepts**: Composition, ArrayList, Collection management

#### 3. **Transaction** (Transaction.java)
- Abstract base class for all transactions
- Properties: Transaction ID, Date, Amount
- **OOP Concepts**: Abstract classes, Abstraction, Protected access

#### 4. **Sale** (Sale.java)
- Extends Transaction class
- Represents individual sales
- Includes discount calculation
- **OOP Concepts**: Inheritance, Method Overriding, Polymorphism

#### 5. **Admin** (Admin.java)
- Represents admin users
- Properties: Admin ID, Name, Shop Name, Email
- **OOP Concepts**: Data Abstraction, Constructors, Encapsulation

#### 6. **Report** (Report.java)
- Generates monthly sales reports
- Filters sales by month/year
- Calculates statistics (total sales, items sold)
- Exports reports to file
- **OOP Concepts**: Date handling, Collection processing, ArrayList

#### 7. **FileHandler** (FileHandler.java)
- Centralized file I/O operations
- Uses Scanner for reading, PrintWriter for writing
- Methods for reading/writing CSV files
- **OOP Concepts**: Static methods, Exception handling, File I/O

#### 8. **InputValidator** (InputValidator.java)
- Validates user input
- Methods for validating IDs, prices, quantities
- Checks business rules (sufficient stock, valid discounts)
- **OOP Concepts**: Static utility methods, Validation logic

### GUI Classes

#### 1. **LoginPanel** (LoginPanel.java)
- Login interface with credentials validation
- Reads from LoginCredentials.csv
- Uses ArrayList for storing credentials
- Beautiful UI with colors and proper layout
- **Concepts**: FlowLayout, BorderLayout, Event handling

#### 2. **MainPanel** (MainPanel.java)
- Main dashboard after successful login
- Menu buttons for different operations
- Displays admin info and system status
- **Concepts**: GridLayout, Color scheme, Button styling

#### 3. **ProductPanel** (ProductPanel.java)
- Product management interface
- Add/Remove/View products
- Real-time inventory display
- CSV synchronization
- **Concepts**: BorderLayout, TextArea, File operations

#### 4. **SalesPanel** (SalesPanel.java)
- Sales transaction recording
- Product selection and quantity input
- Stock validation
- Recent sales history
- **Concepts**: GridLayout, Dialog boxes, Data validation

#### 5. **ReportPanel** (ReportPanel.java)
- View and export reports
- Current month and previous month options
- Export to desktop functionality
- **Concepts**: Date handling, File export, Report generation

#### 6. **Test** (Test.java)
- Application entry point
- Initializes file system and launches GUI
- **Concepts**: SwingUtilities, Frame initialization

---

## CSV File Formats

### LoginCredentials.csv
```
admin,admin
admin009,secure123
```

### Products.csv
```
ProductID,ProductName,CostPrice,SellingPrice,Quantity,Category
P001,Laptop,40000,65000,5,Electronics
P002,Mouse,800,1500,50,Electronics
```

### Sales.csv
```
SaleID,ProductID,ProductName,QuantitySold,UnitPrice,TotalAmount,Date
SALE001,P001,Laptop,1,65000,65000,2025-05-10
```

---

## How to Use

### 1. **Start the Application**
```bash
cd src
java Test
```

### 2. **Login**
- Enter Admin ID: `admin`
- Enter Password: `admin`
- Click "Login" button

### 3. **Manage Products**
- Click "Manage Products" from dashboard
- **Add Product**: Fill in product details and click "Add Product"
- **View Products**: Products displayed automatically
- **Remove Product**: Enter product ID and confirm removal

### 4. **Record Sales**
- Click "Record Sales" from dashboard
- Enter Product ID (e.g., P001)
- Enter quantity and unit price
- Optional: Add discount percentage
- Click "Record Sale" button
- Stock automatically updated

### 5. **View Reports**
- Click "View Reports" from dashboard
- View current month report
- Click "Previous Month" for historical data
- Click "Export to File" to save report on desktop

### 6. **Logout**
- Click "Logout" button on dashboard
- Confirm logout
- Redirected to login screen

---

## OOP Concepts Implemented

✓ **Data Abstraction**: Classes abstract complex business logic
✓ **Encapsulation**: Private variables with public getters/setters
✓ **Constructors**: Multiple constructors in all entity classes
✓ **Access Modifiers**: public, private, protected used appropriately
✓ **Inheritance**: Sale extends Transaction
✓ **Polymorphism**: Method overriding in report generation
✓ **Abstract Classes**: Transaction as abstract base class
✓ **Interfaces**: Comparable-like pattern in sorting
✓ **Composition**: Inventory "has-a" Products
✓ **ArrayList**: Used instead of arrays for dynamic collections
✓ **Exception Handling**: Try-catch for file operations and parsing
✓ **File Handling**: Scanner and PrintWriter for I/O
✓ **GUI Concepts**: BorderLayout, FlowLayout, GridLayout, Panels, Event handling

---

## File Structure

```
SMS/
├── src/
│   ├── Admin.java
│   ├── Customer.java
│   ├── FileHandler.java
│   ├── InputValidator.java
│   ├── Inventory.java
│   ├── LoginPanel.java
│   ├── LoginCredentials.csv
│   ├── MainPanel.java
│   ├── Product.java
│   ├── ProductPanel.java
│   ├── Products.csv
│   ├── Report.java
│   ├── ReportPanel.java
│   ├── Sale.java
│   ├── Sales.csv
│   ├── SalesPanel.java
│   ├── Test.java
│   ├── Transaction.java
│   └── README.md
└── SMS.iml
```

---

## Features Highlights

### ✨ User-Friendly Interface
- Clean and professional UI design
- Color-coded buttons for easy navigation
- Clear error messages
- Responsive dialog boxes

### 🔒 Secure Authentication
- CSV-based credential storage
- Simple but effective validation
- Session-based access per admin

### 📊 Comprehensive Reporting
- Monthly sales statistics
- Filterable reports by month/year
- Desktop file export
- Formatted report display

### 💾 Persistent Data Storage
- All data in simple CSV format
- Easy to backup and transfer
- Can be opened in Excel/Notepad
- No database required

### 📱 Flexible Inventory Management
- Add products anytime
- Remove outdated products
- Real-time stock tracking
- Automatic stock decrease on sales

### 📈 Transaction Tracking
- Every sale recorded with timestamp
- Easy to audit transactions
- Generate reports for analysis
- Calculate profit/loss

---

## Error Handling

The system includes comprehensive error handling:
- **File Not Found**: Creates new files if needed
- **Invalid Input**: Validates all numeric inputs
- **Insufficient Stock**: Prevents overselling
- **Duplicate Product IDs**: Unique constraints
- **Parse Errors**: Graceful handling of CSV parsing failures

---

## Sample Login Credentials

| Admin ID | Password |
|----------|-----------|
| admin | admin |
| admin009 | secure123 |

---

## Future Enhancements

- Database integration (MySQL/PostgreSQL)
- User role management (manager, clerk, owner)
- Advanced analytics and charts
- Customer management
- Supplier management
- Multi-store support
- Mobile app version

---

## Technical Requirements

- **Java Version**: Java 8 or higher
- **Libraries**: javax.swing (included with JDK)
- **Storage**: CSV files in src directory
- **RAM**: Minimum 256MB
- **Disk**: Minimum 10MB

---

## Support and Documentation

- All code is well-commented
- Classes follow naming conventions
- Methods are self-explanatory
- CSV format is human-readable

---

## Author Notes

This system demonstrates:
- Professional Java programming practices
- Complete OOP implementation
- Practical GUI development
- File-based data persistence
- Clean and maintainable code structure

---

**Version**: 1.0  
**Created**: May 2025  
**Status**: Production Ready ✅

