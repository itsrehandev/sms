import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesPanel extends JPanel {
    private JFrame parentFrame;
    private String adminId;
    private Inventory inventory;
    private JTextArea salesDisplayArea;
    private ArrayList<String> salesRecords;

    public SalesPanel(JFrame parentFrame, String adminId, Inventory inventory) {
        this.parentFrame = parentFrame;
        this.adminId = adminId;
        this.inventory = inventory;
        this.salesRecords = new ArrayList<>();
        loadSalesRecords();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel - Header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(33, 150, 243));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Record Sales Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(245, 245, 250));

        // Left side - Sales History
        JPanel historyPanel = new JPanel(new BorderLayout(10, 10));
        historyPanel.setBackground(new Color(245, 245, 250));
        historyPanel.setBorder(BorderFactory.createTitledBorder("Recent Sales"));

        salesDisplayArea = new JTextArea(15, 30);
        salesDisplayArea.setEditable(false);
        salesDisplayArea.setFont(new Font("Courier", Font.PLAIN, 10));
        JScrollPane scrollPane = new JScrollPane(salesDisplayArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        refreshSalesDisplay();

        // Right side - Buttons
        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        rightPanel.setBackground(new Color(245, 245, 250));

        JButton recordButton = createButton("Record Sale", new Color(76, 175, 80));
        recordButton.addActionListener(e -> openRecordSaleDialog());

        JButton viewButton = createButton("View Products", new Color(255, 152, 0));
        viewButton.addActionListener(e -> showProductsList());

        JButton backButton = createButton("Back to Menu", new Color(158, 158, 158));
        backButton.addActionListener(e -> goBackToMenu());

        rightPanel.add(recordButton);
        rightPanel.add(viewButton);
        rightPanel.add(backButton);

        centerPanel.add(historyPanel, BorderLayout.CENTER);
        centerPanel.add(rightPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void loadSalesRecords() {
        ArrayList<String[]> allSales = FileHandler.readSalesFromCSV("Sales.csv");
        for (String[] sale : allSales) {
            if (sale.length >= 7) {
                StringBuilder record = new StringBuilder();
                record.append(sale[0]).append(",").append(sale[1]).append(",").append(sale[2]).append(",");
                record.append(sale[3]).append(",").append(sale[4]).append(",").append(sale[5]).append(",").append(sale[6]);
                salesRecords.add(record.toString());
            }
        }
    }

    private void refreshSalesDisplay() {
        StringBuilder display = new StringBuilder();
        display.append("==========================================\n");
        display.append("RECENT SALES (Total: ").append(salesRecords.size()).append(")\n");
        display.append("==========================================\n\n");

        if (salesRecords.isEmpty()) {
            display.append("No sales recorded yet.\n");
        } else {
            // Show last 10 sales
            int startIndex = Math.max(0, salesRecords.size() - 10);
            for (int i = salesRecords.size() - 1; i >= startIndex; i--) {
                String[] parts = salesRecords.get(i).split(",");
                if (parts.length >= 7) {
                    display.append("Sale ID: ").append(parts[0]).append("\n");
                    display.append("Product: ").append(parts[2]).append(" (ID: ").append(parts[1]).append(")\n");
                    display.append("Qty: ").append(parts[3]).append(" | Price: Rs.").append(parts[4]).append("\n");
                    display.append("Total: Rs.").append(parts[5]).append(" | Date: ").append(parts[6]).append("\n");
                    display.append("------------------------------------------\n\n");
                }
            }
        }

        salesDisplayArea.setText(display.toString());
    }

    private void openRecordSaleDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField saleIdField = new JTextField("SALE" + System.currentTimeMillis() % 10000);
        saleIdField.setEditable(false);
        
        JTextField productIdField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField discountField = new JTextField("0");

        panel.add(new JLabel("Sale ID (Auto):"));
        panel.add(saleIdField);
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Quantity Sold:"));
        panel.add(quantityField);
        panel.add(new JLabel("Discount (%):"));
        panel.add(discountField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Record New Sale", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String saleId = saleIdField.getText().trim();
                String productId = productIdField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());
                double discount = Double.parseDouble(discountField.getText().trim());
                if (productId.isEmpty() || quantity <= 0 || !InputValidator.isValidDiscount(discount) || !InputValidator.hasSufficientStock(productId,quantity,inventory)) {
                    JOptionPane.showMessageDialog(this, "Please enter valid product details!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Product product = inventory.searchProduct(productId);
                if (product == null) {
                    JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!product.decreaseStock(quantity)) {
                    JOptionPane.showMessageDialog(this, "Not enough stock available!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get unit price from product
                double unitPrice = product.getSellingPrice();
                
                // Calculate total
                double totalAmount = (unitPrice * quantity) * (1 - discount / 100);
                LocalDate today = LocalDate.now();

                // Create sale record
                String saleRecord = saleId + "," + productId + "," + product.getProductName() + "," + 
                                  quantity + "," + unitPrice + "," + totalAmount + "," + today;
                
                salesRecords.add(saleRecord);

                // Save updated inventory and sales
                FileHandler.saveProductsToCSV("Products.csv", inventory.getProducts());
                FileHandler.saveSalesToCSV("Sales.csv", salesRecords);

                refreshSalesDisplay();
                JOptionPane.showMessageDialog(this, 
                    "Sale recorded successfully!\nTotal: Rs." + String.format("%.2f", totalAmount), 
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showProductsList() {
        StringBuilder products = new StringBuilder("Available Products:\n\n");
        for (Product p : inventory.getProducts()) {
            products.append("ID: ").append(p.getProductId()).append(" | ")
                   .append("Name: ").append(p.getProductName()).append(" | ")
                   .append("Price: Rs.").append(p.getSellingPrice()).append(" | ")
                   .append("Stock: ").append(p.getQuantity()).append("\n");
        }

        JTextArea textArea = new JTextArea(products.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Products List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void goBackToMenu() {
        parentFrame.setContentPane(new MainPanel(parentFrame, adminId));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}

