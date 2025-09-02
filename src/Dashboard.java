import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    public Dashboard(MainApp app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 249, 251));

        JLabel title = new JLabel("Quiz Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(32, 33, 36));
        title.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);

        RoundedPanel btnPanel = new RoundedPanel();
        btnPanel.setLayout(new GridLayout(2,2,20,20));
        btnPanel.setPreferredSize(new Dimension(480, 260));

        JButton startBtn = new RoundedButton("Start Quiz");
        startBtn.setToolTipText("Click to start quiz");
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                app.showScreen("Quiz");
            }
        });

        JButton leaderboardBtn = new RoundedButton("Leaderboard");
        leaderboardBtn.setToolTipText("View leaderboard");
        leaderboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                app.showScreen("Result"); // For demo, show result as leaderboard
            }
        });

        JButton aboutBtn = new RoundedButton("About");
        aboutBtn.setToolTipText("About this app");
        aboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "Online Quiz App\nBy JavaSwing-FrontEnd\n2025", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton exitBtn = new RoundedButton("Logout");
        exitBtn.setToolTipText("Exit application");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                app.showScreen("Login");
            }
        });

        btnPanel.add(startBtn);
        btnPanel.add(leaderboardBtn);
        btnPanel.add(aboutBtn);
        btnPanel.add(exitBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        center.add(btnPanel, gbc);
    }
}
