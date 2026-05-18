import java.awt.*;
import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        // Print startup information
        System.out.println("==================================================");
        System.out.println("Sales Management System - Starting");
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        System.out.println("User Home: " + System.getProperty("user.home"));
        System.out.println("==================================================");
        
        // Initialize file system if needed
        FileHandler.createProductsFileIfNotExists("Products.csv");
        FileHandler.createSalesFileIfNotExists("Sales.csv");

        // Launch Login Panel
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Sales Management System");
            window.setContentPane(new LoginPanel(window));
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setSize(600, 500);
            window.setLocationRelativeTo(null);
            window.setResizable(true);
            window.setVisible(true);
            window.setMinimumSize(new Dimension(600, 500));
        });
    }
}