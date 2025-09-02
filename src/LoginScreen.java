import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {
    public LoginScreen(MainApp app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 249, 251));

        // Logo/banner
        JLabel logo = new JLabel("Welcome", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        logo.setForeground(new Color(32, 33, 36));
        logo.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(logo, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);

        RoundedPanel formPanel = new RoundedPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackgroundColor(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(420, 240));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(new Color(32, 33, 36));
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(new Color(32, 33, 36));
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginBtn = new RoundedButton("Login");
        loginBtn.setToolTipText("Click to login");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                app.showScreen("Dashboard");
            }
        });

        JButton registerBtn = new RoundedButton("Register");
        registerBtn.setToolTipText("Click to register");
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passLabel);
        formPanel.add(passField);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(loginBtn);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(registerBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; gbc.insets = new Insets(0,0,0,0);
        center.add(formPanel, gbc);
    }
}
