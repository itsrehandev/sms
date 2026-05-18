import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductPanel extends JPanel {
    private JFrame parentFrame;
    private String adminId;
    private Inventory inventory;
    private JTextArea productDisplayArea;

    public ProductPanel(JFrame parentFrame, String adminId, Inventory inventory) {
        this.parentFrame = parentFrame;
        this.adminId = adminId;
        this.inventory = inventory;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel - Header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(76, 175, 80));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Manage Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Display Products and Forms
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(245, 245, 250));

        // Left side - Product List
        JPanel listPanel = new JPanel(new BorderLayout(10, 10));
        listPanel.setBackground(new Color(245, 245, 250));
        listPanel.setBorder(BorderFactory.createTitledBorder("Current Products"));

        productDisplayArea = new JTextArea(15, 30);
        productDisplayArea.setEditable(false);
        productDisplayArea.setFont(new Font("Courier", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(productDisplayArea);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        refreshProductDisplay();

        // Right side - Buttons
        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        rightPanel.setBackground(new Color(245, 245, 250));

        JButton addButton = createButton("Add Product", new Color(33, 150, 243));
        addButton.addActionListener(e -> openAddProductDialog());

        JButton removeButton = createButton("Remove Product", new Color(244, 67, 54));
        removeButton.addActionListener(e -> openRemoveProductDialog());

        JButton backButton = createButton("Back to Menu", new Color(158, 158, 158));
        backButton.addActionListener(e -> goBackToMenu());

        rightPanel.add(addButton);
        rightPanel.add(removeButton);
        rightPanel.add(backButton);

        centerPanel.add(listPanel, BorderLayout.CENTER);
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

    private void refreshProductDisplay() {
        StringBuilder display = new StringBuilder();
        display.append("==========================================\n");
        display.append("PRODUCT INVENTORY (Total: ").append(inventory.getProducts().size()).append(")\n");
        display.append("==========================================\n\n");

        ArrayList<Product> products = inventory.getProducts();
        if (products.isEmpty()) {
            display.append("No products in inventory.\n");
        } else {
            for (Product p : products) {
                display.append("ID: ").append(p.getProductId()).append("\n");
                display.append("Name: ").append(p.getProductName()).append("\n");
                display.append("Category: ").append(p.getCategory()).append("\n");
                display.append("Selling Price: Rs.").append(p.getSellingPrice()).append("\n");
                display.append("Stock: ").append(p.getQuantity()).append(" units\n");
                display.append("------------------------------------------\n\n");
            }
        }

        productDisplayArea.setText(display.toString());
    }

    private void openAddProductDialog() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField costField = new JTextField();
        JTextField sellingField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField categoryField = new JTextField();

        panel.add(new JLabel("Product ID:"));
        panel.add(idField);
        panel.add(new JLabel("Product Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Cost Price (Rs.):"));
        panel.add(costField);
        panel.add(new JLabel("Selling Price (Rs.):"));
        panel.add(sellingField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Product", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                double costPrice = Double.parseDouble(costField.getText().trim());
                double sellingPrice = Double.parseDouble(sellingField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());
                String category = categoryField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Product newProduct = new Product(id, name, costPrice, sellingPrice, quantity, category);
                inventory.addProduct(newProduct);
                
                // Save to CSV
                ArrayList<Product> allProducts = inventory.getProducts();
                FileHandler.saveProductsToCSV("Products.csv", allProducts);
                
                refreshProductDisplay();
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for prices and quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openRemoveProductDialog() {
        String productId = JOptionPane.showInputDialog(this, "Enter Product ID to remove:", "Remove Product", JOptionPane.QUESTION_MESSAGE);

        if (productId != null && !productId.isEmpty()) {
            Product p = inventory.searchProduct(productId);
            if (p != null) {
                int result = JOptionPane.showConfirmDialog(this, 
                    "Remove product: " + p.getProductName() + "?", 
                    "Confirm Removal", 
                    JOptionPane.YES_NO_OPTION);
                
                if (result == JOptionPane.YES_OPTION) {
                    inventory.removeProduct(productId);
                    FileHandler.saveProductsToCSV("Products.csv", inventory.getProducts());
                    refreshProductDisplay();
                    JOptionPane.showMessageDialog(this, "Product removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void goBackToMenu() {
        parentFrame.setContentPane(new MainPanel(parentFrame, adminId));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}

