package rehan;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends java.awt.Container {

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JButton loginButton = new JButton("Login");
    private final JLabel statusLabel = new JLabel(" ");

    public LoginPanel() {
        setLayout(new BorderLayout(10, 10)); // gaps between regions


        // Title
        JLabel title = new JLabel("Sales System Login");
        Font modifiedFont = title.getFont().deriveFont(Font.BOLD, 18f);
        title.setFont(modifiedFont);
        add(title, BorderLayout.NORTH);

        // Form (center)
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.add(new JLabel("Username:"));
        form.add(usernameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);
        add(form, BorderLayout.CENTER);

        // Bottom area: status + button
        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        statusLabel.setForeground(Color.RED);
        bottom.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonRow.add(loginButton);
        bottom.add(buttonRow, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        // Events
        loginButton.addActionListener(e -> attemptLogin());

        // Nice UX: pressing Enter in password triggers login
        passwordField.addActionListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();

        if (username.isEmpty() || password.length == 0) {
            statusLabel.setText("Please enter username and password.");
        }

    }
}
