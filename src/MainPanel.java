import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private JFrame parentFrame;
    private String adminId;
    private Inventory inventory;
    private ArrayList<String> salesTransactions;

    public MainPanel(JFrame parentFrame, String adminId) {
        this.parentFrame = parentFrame;
        this.adminId = adminId;
        
        // Load inventory from CSV
        FileHandler.createProductsFileIfNotExists("Products.csv");
        FileHandler.createSalesFileIfNotExists("Sales.csv");
        
        ArrayList<Product> products = FileHandler.readProductsFromCSV("Products.csv");
        this.inventory = new Inventory("INV001");
        for (Product p : products) {
            this.inventory.addProduct(p);
        }
        
        this.salesTransactions = new ArrayList<>();
        
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel - Header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30, 120, 160));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Sales Management System - Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.WEST);

        JLabel adminLabel = new JLabel("Welcome " + adminId);
        adminLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        adminLabel.setForeground(Color.WHITE);
        topPanel.add(adminLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Menu Buttons
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        centerPanel.setBackground(new Color(245, 245, 250));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Products Button
        JButton productsButton = createMenuButton("Manage Products", new Color(76, 175, 80));
        productsButton.addActionListener(e -> openProductPanel());

        // Sales Button
        JButton salesButton = createMenuButton("Record Sales", new Color(33, 150, 243));
        salesButton.addActionListener(e -> openSalesPanel());

        // Reports Button
        JButton reportsButton = createMenuButton("View Reports", new Color(255, 152, 0));
        reportsButton.addActionListener(e -> openReportPanel());

        // Logout Button
        JButton logoutButton = createMenuButton("Logout", new Color(244, 67, 54));
        logoutButton.addActionListener(e -> logout());

        centerPanel.add(productsButton);
        centerPanel.add(salesButton);
        centerPanel.add(reportsButton);
        centerPanel.add(logoutButton);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Status
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(245, 245, 250));
        
        JLabel statusLabel = new JLabel("Status: System Ready | Products: " + inventory.getProducts().size());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        bottomPanel.add(statusLabel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(darkenColor(color));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private Color darkenColor(Color c) {
        return new Color(
            Math.max(0, c.getRed() - 30),
            Math.max(0, c.getGreen() - 30),
            Math.max(0, c.getBlue() - 30)
        );
    }

    private void openProductPanel() {
        parentFrame.setContentPane(new ProductPanel(parentFrame, adminId, inventory));
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void openSalesPanel() {
        parentFrame.setContentPane(new SalesPanel(parentFrame, adminId, inventory));
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void openReportPanel() {
        parentFrame.setContentPane(new ReportPanel(parentFrame, adminId, inventory));
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void logout() {
        int result = JOptionPane.showConfirmDialog(
            parentFrame, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (result == JOptionPane.YES_OPTION) {
            parentFrame.setContentPane(new LoginPanel(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }
}



