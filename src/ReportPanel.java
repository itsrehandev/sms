import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.YearMonth;

public class ReportPanel extends JPanel {
    private JFrame parentFrame;
    private String adminId;
    private Inventory inventory;
    private JTextArea reportDisplayArea;

    public ReportPanel(JFrame parentFrame, String adminId, Inventory inventory) {
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
        topPanel.setBackground(new Color(255, 152, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Monthly Sales Reports");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel - Report Display
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(245, 245, 250));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Report View"));

        reportDisplayArea = new JTextArea(15, 50);
        reportDisplayArea.setEditable(false);
        reportDisplayArea.setFont(new Font("Courier", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(reportDisplayArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Display current month report by default
        displayCurrentMonthReport();

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel - Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setBackground(new Color(245, 245, 250));

        JButton currentMonthButton = createButton("Current Month", new Color(76, 175, 80));
        currentMonthButton.addActionListener(e -> displayCurrentMonthReport());

        JButton previousMonthButton = createButton("Previous Month", new Color(33, 150, 243));
        previousMonthButton.addActionListener(e -> displayPreviousMonthReport());

        JButton exportButton = createButton("Export to File", new Color(156, 39, 176));
        exportButton.addActionListener(e -> exportReport());

        JButton backButton = createButton("Back to Menu", new Color(158, 158, 158));
        backButton.addActionListener(e -> goBackToMenu());

        bottomPanel.add(currentMonthButton);
        bottomPanel.add(previousMonthButton);
        bottomPanel.add(exportButton);
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
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

    private void displayCurrentMonthReport() {
        Report report = new Report(inventory);
        reportDisplayArea.setText(report.getDetailedReport());
    }

    private void displayPreviousMonthReport() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        Report report = new Report(previousMonth, inventory);
        reportDisplayArea.setText(report.getDetailedReport());
    }

    private void exportReport() {
        try {
            String userHome = System.getProperty("user.home");
            String desktopPath = userHome + File.separator + "Desktop";
            String filename = "Report_" + YearMonth.now().toString() + ".txt";
            String fullPath = desktopPath + File.separator + filename;
            
            Report report = new Report(inventory);
            
            // Check if there are any sales recorded
            if (report.getTotalTransactions() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No sales recorded for this month.\nNo report file created.", 
                    "No Data", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            report.exportToFile(fullPath);
            
            JOptionPane.showMessageDialog(this, 
                "Report exported successfully!\n\nFile: " + fullPath, 
                "Export Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error exporting report: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToMenu() {
        parentFrame.setContentPane(new MainPanel(parentFrame, adminId));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}

