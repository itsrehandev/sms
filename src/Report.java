import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class Report {
    private YearMonth reportMonth;
    private ArrayList<String[]> monthlySales;
    private Inventory inventory;

    // Constructor
    public Report(YearMonth month, Inventory inventory) {
        this.reportMonth = month;
        this.inventory = inventory;
        this.monthlySales = new ArrayList<>();
        filterSalesByMonth();
    }

    // Default constructor for current month
    public Report(Inventory inventory) {
        this.reportMonth = YearMonth.now();
        this.inventory = inventory;
        this.monthlySales = new ArrayList<>();
        filterSalesByMonth();
    }

    // Filter sales by month from CSV data
    private void filterSalesByMonth() {
        ArrayList<String[]> allSales = FileHandler.readSalesFromCSV("Sales.csv");
        
        for (String[] saleData : allSales) {
            if (saleData.length >= 7) {
                try {
                    String dateStr = saleData[6].trim();
                    LocalDate saleDate = LocalDate.parse(dateStr);
                    YearMonth saleMonth = YearMonth.from(saleDate);
                    
                    if (saleMonth.equals(this.reportMonth)) {
                        monthlySales.add(saleData);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing date: " + saleData[6]);
                }
            }
        }
    }

    // Calculate total sales for the month
    public double calculateTotalSales() {
        double total = 0;
        for (String[] sale : monthlySales) {
            try {
                double amount = Double.parseDouble(sale[5].trim());
                total += amount;
            } catch (NumberFormatException e) {
                System.err.println("Error parsing amount");
            }
        }
        return total;
    }

    // Calculate total items sold
    public int calculateTotalItemsSold() {
        int total = 0;
        for (String[] sale : monthlySales) {
            try {
                int quantity = Integer.parseInt(sale[3].trim());
                total += quantity;
            } catch (NumberFormatException e) {
                System.err.println("Error parsing quantity");
            }
        }
        return total;
    }

    // Get detailed report as string
    public String getDetailedReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("=====================================\n");
        report.append("   MONTHLY SALES REPORT\n");
        report.append("   Month: "+ reportMonth+ "\n");
        report.append("=====================================\n\n");
        
        report.append("SALES TRANSACTIONS:\n");
        report.append("-----------------------------------\n");
        
        if (monthlySales.isEmpty()) {
            report.append("No sales recorded for this month.\n");
        } else {
            int count = 1;
            for (String[] sale : monthlySales) {
                if (sale.length >= 7) {
                    report.append(count).append(". Product: ").append(sale[2]).append(" (ID: ").append(sale[1]).append(")\n");
                    report.append("   Quantity: ").append(sale[3]).append(" | Price: Rs.").append(sale[4]).append("\n");
                    report.append("   Total: Rs.").append(sale[5]).append(" | Date: ").append(sale[6]).append("\n");
                    report.append("\n");
                    count++;
                }
            }
        }
        
        report.append("-----------------------------------\n");
        report.append("SUMMARY:\n");
        report.append("Total Transactions: ").append(monthlySales.size()).append("\n");
        report.append("Total Items Sold: ").append(calculateTotalItemsSold()).append("\n");
        report.append("Total Sales Amount: Rs.").append(String.format("%.2f", calculateTotalSales())).append("\n");
        report.append("=====================================\n");
        
        return report.toString();
    }

    // Get simple report as string
    public String getSimpleReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("MONTHLY REPORT - ").append(reportMonth).append("\n");
        report.append("Total Sales: Rs.").append(String.format("%.2f", calculateTotalSales())).append("\n");
        report.append("Items Sold: ").append(calculateTotalItemsSold()).append("\n");
        report.append("Transactions: ").append(monthlySales.size()).append("\n");
        
        return report.toString();
    }

    // Export report to file
    public void exportToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.print(getDetailedReport());
            writer.close();
            System.out.println("Report exported to " + filename);
        } catch (Exception e) {
            System.err.println("Error exporting report: " + e.getMessage());
        }
    }

    // Getters
    public YearMonth getReportMonth() {
        return reportMonth;
    }

    public ArrayList<String[]> getMonthlySales() {
        return monthlySales;
    }

    public int getTotalTransactions() {
        return monthlySales.size();
    }

    public void print() {
        System.out.println(getDetailedReport());
    }
}



