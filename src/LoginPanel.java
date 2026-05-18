import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.*;
import java.util.*;

public class LoginPanel extends JPanel {

    private final JTextField adminIdField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton loginButton = new JButton("Login");
    private final JLabel statusLabel = new JLabel(" ");
    private final JFrame parentFrame;
    private ArrayList<String> adminIds = new ArrayList<String>();
    private ArrayList<String> passwords = new ArrayList<String>();

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        loadCredentials();
        initializeUI();
    }

    public LoginPanel() {
        this.parentFrame = null;
        loadCredentials();
        initializeUI();
    }

    private void initializeUI() {
        // Main panel with gradient background
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // ========== WELCOME SECTION ==========
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(240, 240, 245));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcomeTitle = new JLabel("Welcome to Sales Management System");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeTitle.setForeground(new Color(25, 45, 85));
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeSubtitle = new JLabel("Admin Login Portal");
        welcomeSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeSubtitle.setForeground(new Color(100, 100, 120));
        welcomeSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(welcomeTitle);
        welcomePanel.add(Box.createVerticalStrut(5));
        welcomePanel.add(welcomeSubtitle);
        welcomePanel.add(Box.createVerticalStrut(10));

        add(welcomePanel, BorderLayout.NORTH);

        // ========== FORM SECTION ==========
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Admin ID Field
        JLabel adminIdLabel = new JLabel("Admin ID:");
        adminIdLabel.setFont(new Font("Arial", Font.BOLD, 12));
        adminIdLabel.setForeground(new Color(25, 45, 85));
        adminIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        adminIdField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        adminIdField.setMaximumSize(new Dimension(300, 40));
        adminIdField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setForeground(new Color(25, 45, 85));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(adminIdLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(adminIdField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));

        // Status Label (Error Message)
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(220, 20, 60)); // Crimson Red
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(statusLabel);

        add(formPanel, BorderLayout.CENTER);

        // ========== BUTTON SECTION ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));

        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(30, 120, 160));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 45));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(20, 100, 140));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(30, 120, 160));
            }
        });

        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ========== EVENT LISTENERS ==========
        loginButton.addActionListener(e -> attemptLogin());
        passwordField.addActionListener(e -> attemptLogin());
    }

    private void loadCredentials() {
        try {
            // List of possible file locations to try
            String[] possiblePaths = {
                "LoginCredentials.csv",                    // Current directory
                "src/LoginCredentials.csv",                // If run from project root
                "../src/LoginCredentials.csv",             // Parent directory  
                "C:/Users/HP/IdeaProjects/SMS/src/LoginCredentials.csv" // Absolute path
            };
            
            File file = null;
            System.out.println("\n=== SEARCHING FOR CREDENTIALS FILE ===");
            for (String path : possiblePaths) {
                File f = new File(path);
                System.out.println("Checking: " + f.getAbsolutePath() + " -> " + (f.exists() ? "✅ FOUND" : "❌ NOT FOUND"));
                if (f.exists()) {
                    file = f;
                    break;
                }
            }
            
            if (file != null && file.exists()) {
                System.out.println("\n✅ LoginCredentials.csv found at: " + file.getAbsolutePath());
                Scanner fileScanner = new Scanner(file);
                int count = 0;
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {  // Skip empty lines
                        String[] parts = line.split(",");
                        if (parts.length >= 2) {
                            String id = parts[0].trim();
                            String pass = parts[1].trim();
                            adminIds.add(id);
                            passwords.add(pass);
                            System.out.println("  ✅ Loaded: ID='" + id + "' | Password='" + pass + "'");
                            count++;
                        }
                    }
                }
                fileScanner.close();
                System.out.println("✅ Total credentials loaded: " + count + "\n");
                
                if (count == 0) {
                    System.err.println("⚠️ WARNING: File found but no credentials could be loaded!");
                }
            } else {
                System.err.println("\n❌ CRITICAL: LoginCredentials.csv NOT FOUND in any location!");
                System.err.println("📁 Current working directory: " + System.getProperty("user.dir"));
                System.err.println("Please ensure LoginCredentials.csv is in the src/ directory\n");
            }

        } catch (FileNotFoundException ex) {
            System.err.println("❌ Error reading credentials file: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private void attemptLogin() {
        String adminId = adminIdField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Validation
        if (adminId.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter Admin ID and Password.");
            return;
        }

        // Check credentials
        boolean loginSuccess = false;
        for (int i = 0; i < adminIds.size(); i++) {
            if (adminIds.get(i).equals(adminId) && passwords.get(i).equals(password)) {
                loginSuccess = true;
                break;
            }
        }

        if (loginSuccess) {
            statusLabel.setForeground(new Color(34, 139, 34));
            statusLabel.setText("Login successful! Navigating...");
            
            Timer timer = new Timer(1000, e -> {
                navigateToMainScreen(adminId);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            statusLabel.setForeground(new Color(220, 20, 60));
            statusLabel.setText("Incorrect ID or Password. Please try again.");
            passwordField.setText("");
            adminIdField.requestFocus();
        }
    }

    private void navigateToMainScreen(String adminId) {
        if (parentFrame != null) {
            parentFrame.setContentPane(new MainPanel(parentFrame, adminId));
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }
}