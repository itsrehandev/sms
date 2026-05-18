# Quick Start Guide - Sales Management System

## Installation & Running

### Step 1: Open Terminal/Command Prompt
Navigate to the project directory:
```bash
cd C:\Users\HP\IdeaProjects\SMS\src
```

### Step 2: Start the Application
```bash
java Test
```

The login window will appear!

---

## Login Credentials

Use **one of these** to login:

| Admin ID | Password |
|----------|----------|
| admin | admin |
| admin009 | secure123 |

---

## Main Dashboard - Quick Navigation

```
┌─────────────────────────────────────┐
│  Sales Management System Dashboard  │
├─────────────────────────────────────┤
│                                     │
│  ┌──────────────────────────────┐   │
│  │   Manage Products            │   │ Click to manage inventory
│  └──────────────────────────────┘   │
│                                     │
│  ┌──────────────────────────────┐   │
│  │   Record Sales               │   │ Click to record transactions
│  └──────────────────────────────┘   │
│                                     │
│  ┌──────────────────────────────┐   │
│  │   View Reports               │   │ Click to see monthly reports
│  └──────────────────────────────┘   │
│                                     │
│  ┌──────────────────────────────┐   │
│  │   Logout                     │   │ Click to exit
│  └──────────────────────────────┘   │
│                                     │
└─────────────────────────────────────┘
```

---

## Common Tasks

### ✏️ Add a Product
1. Dashboard → Click **Manage Products**
2. Click **Add Product** button
3. Fill in:
   - Product ID (e.g., P009)
   - Product Name (e.g., Tablet)
   - Cost Price
   - Selling Price
   - Quantity in stock
   - Category
4. Click OK
5. Product is added to inventory

### 🛒 Record a Sale
1. Dashboard → Click **Record Sales**
2. Click **Record Sale** button
3. Fill in:
   - Product ID (or click "View Products" to see available IDs)
   - Quantity to sell
   - Unit Price
   - Discount % (optional, default is 0)
4. Click OK
5. Stock automatically updated
6. Sale recorded with current date

### 📊 View Reports
1. Dashboard → Click **View Reports**
2. Options:
   - **Current Month**: Shows this month's sales
   - **Previous Month**: Shows last month's sales
   - **Export to File**: Saves report to Desktop
3. Report shows:
   - All transactions for the month
   - Total sales amount
   - Total items sold
   - Number of transactions

### 🗑️ Remove a Product
1. Dashboard → Click **Manage Products**
2. Click **Remove Product** button
3. Enter Product ID
4. Confirm deletion
5. Product removed from inventory

---

## Understanding the Data Files

### Products.csv
- Stores all products in inventory
- Format: ID, Name, CostPrice, SellingPrice, Quantity, Category
- Can be opened in Excel

### Sales.csv
- Stores all sales transactions
- Format: SaleID, ProductID, ProductName, QuantitySold, UnitPrice, TotalAmount, Date
- Can be opened in Excel

### LoginCredentials.csv
- Stores admin login details
- Format: AdminID, Password
- Edit to add more admins

---

## Sample Products Available

Try these Product IDs for testing:
- **P001** - Laptop (Limited stock: 5)
- **P002** - Mouse (Stock: 50)
- **P003** - Keyboard (Stock: 30)
- **P004** - Monitor (Stock: 10)
- **P005** - Shirts (Stock: 100)
- **P006** - Pants (Stock: 80)
- **P007** - Shoes (Stock: 50)
- **P008** - Books (Stock: 100)

---

## Tips & Tricks

💡 **Stock Management**
- The system prevents selling more than available stock
- Stock automatically decreases after each sale
- View current stock in Products panel

💡 **Discounts**
- Enter discount as percentage (0-100)
- Automatically calculated in total

💡 **Dates**
- All sales recorded with current date
- Reports filter by month/year automatically

💡 **Data Backup**
- CSV files can be copied for backup
- All data is human-readable

---

## Troubleshooting

### Issue: "Incorrect ID or Password"
**Solution**: Use correct credentials (admin/admin or admin009/secure123)

### Issue: "Product not found"
**Solution**: Check Product ID in "View Products" section or "Product List"

### Issue: "Not enough stock"
**Solution**: Available stock exceeded. Check current quantity in Products panel.

### Issue: Files not created
**Solution**: Ensure write permissions in src directory. System auto-creates files.

---

## Error Messages & Meanings

| Message | What's Wrong | How to Fix |
|---------|------------|-----------|
| "Please enter valid numbers" | Non-numeric input for price/quantity | Use only numbers |
| "Product not found" | Product ID doesn't exist | Check Product ID in View Products |
| "Not enough stock available" | Trying to sell more than available | Reduce quantity |
| "Cannot add product with zero quantity" | Adding product with 0 stock | Enter quantity > 0 |
| "Please fill all fields" | Empty fields in form | Fill all required fields |

---

## File Location Info

All data files are stored in:
```
C:\Users\HP\IdeaProjects\SMS\src\
```

Reports exported to:
```
C:\Users\[YourUsername]\Desktop\
```

---

## System Information

| Detail | Value |
|--------|-------|
| Application | Sales Management System v1.0 |
| Language | Java |
| UI Framework | Swing |
| Data Storage | CSV Files |
| User Type | Single Admin per Session |
| Multi-Admin Support | Yes (different logins) |

---

## Key Features at a Glance

✅ **Inventory Management** - Add/Remove/View products  
✅ **Sales Tracking** - Record transactions automatically  
✅ **Stock Management** - Auto update based on sales  
✅ **Monthly Reports** - Generate and export reports  
✅ **Multi-Admin** - Multiple admin accounts  
✅ **Data Persistence** - CSV file-based storage  
✅ **User-Friendly GUI** - Clean, color-coded interface  
✅ **Error Handling** - Validates all inputs  

---

**Need More Help?** Check README.md for detailed documentation!

EOF

