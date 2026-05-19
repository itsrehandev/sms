import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    
    // String reading and writing with PrintWriter
    public static void saveToFile(String filename, String content, boolean append) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, append));
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }

    // Save ArrayList of strings to file
    public static void saveListToFile(String filename, ArrayList<String> lines) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read all lines from file using Scanner
    public static ArrayList<String> readFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
        return lines;
    }

    // Read products from CSV file
    public static ArrayList<Product> readProductsFromCSV(String filename) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            boolean firstLine = true;
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip header line
                if (firstLine) {
                    firstLine = false;
                    if (line.contains("ProductID") || line.contains("ID")) {
                        continue;
                    }
                }
                
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    try {
                        String id = parts[0].trim();
                        String name = parts[1].trim();
                        double costPrice = Double.parseDouble(parts[2].trim());
                        double sellingPrice = Double.parseDouble(parts[3].trim());
                        int quantity = Integer.parseInt(parts[4].trim());
                        String category = parts[5].trim();
                        
                        Product product = new Product(id, name, costPrice, sellingPrice, quantity, category);
                        products.add(product);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing product data: " + line);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Products file not found: " + filename);
        }
        return products;
    }

    // Save products to CSV file
    public static void saveProductsToCSV(String filename, ArrayList<Product> products) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println("ProductID,ProductName,CostPrice,SellingPrice,Quantity,Category");
            
            for (Product product : products) {
                writer.println(product.toCSV());
            }
            writer.close();
            System.out.println("Products saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }

    // Read sales transactions from CSV file
    public static ArrayList<String[]> readSalesFromCSV(String filename) {
        ArrayList<String[]> sales = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            boolean firstLine = true;
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                if (firstLine) {
                    firstLine = false;
                    if (line.contains("SaleID") || line.contains("TransactionID")) {
                        continue;
                    }
                }
                
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    sales.add(parts);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sales file not found. " + filename);
        }
        return sales;
    }

    // Save sales to CSV file
    public static void saveSalesToCSV(String filename, ArrayList<String> salesData) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println("SaleID,ProductID,ProductName,QuantitySold,UnitPrice,TotalAmount,Date");
            
            for (String sale : salesData) {
                writer.println(sale);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving sales: " + e.getMessage());
        }
    }

    // Check if file exists

    // Create header for new CSV if it doesn't exist
    public static void createProductsFileIfNotExists(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(f));
                writer.println("ProductID,ProductName,CostPrice,SellingPrice,Quantity,Category");
                writer.close();
                System.out.println("Created new products file: " + filename);
            } catch (IOException e) {
                System.err.println("Error creating products file: " + e.getMessage());
            }
        }
    }

    public static void createSalesFileIfNotExists(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(f));
                writer.println("SaleID,ProductID,ProductName,QuantitySold,UnitPrice,TotalAmount,Date");
                writer.close();
                System.out.println("Created new sales file: " + filename);
            } catch (IOException e) {
                System.err.println("Error creating sales file: " + e.getMessage());
            }
        }
    }
}

