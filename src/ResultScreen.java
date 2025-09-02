import javax.swing.*;
import java.awt.*;

public class ResultScreen extends JPanel {
    private JLabel scoreLabel, correctLabel, wrongLabel, skippedLabel, emojiLabel;
    private JButton retryBtn, exitBtn;
    private JTable leaderboard;
    private JScrollPane leaderboardScroll;
    private MainApp app;

    public ResultScreen(MainApp app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(new Color(36, 41, 47));

        // Title
        JLabel title = new JLabel("Quiz Result", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(title, BorderLayout.NORTH);

        // Center panel for result details and buttons
        JPanel centerWrap = new JPanel(new GridBagLayout());
        centerWrap.setOpaque(false);
        add(centerWrap, BorderLayout.CENTER);

        RoundedPanel centerPanel = new RoundedPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(520, 300));

        emojiLabel = new JLabel();
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(emojiLabel);
        centerPanel.add(Box.createVerticalStrut(10));

        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(scoreLabel);
        centerPanel.add(Box.createVerticalStrut(10));

        correctLabel = new JLabel();
        correctLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        correctLabel.setForeground(new Color(46, 204, 113));
        correctLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(correctLabel);
        centerPanel.add(Box.createVerticalStrut(5));

        wrongLabel = new JLabel();
        wrongLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        wrongLabel.setForeground(new Color(231, 76, 60));
        wrongLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(wrongLabel);
        centerPanel.add(Box.createVerticalStrut(5));

        skippedLabel = new JLabel();
        skippedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        skippedLabel.setForeground(new Color(241, 196, 15));
        skippedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(skippedLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        // Retry Button
        retryBtn = new RoundedButton("Retry Quiz");
        retryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        retryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                app.getQuizScreen().resetQuiz();
                app.showScreen("Quiz");
            }
        });

        // Exit Button
        exitBtn = new RoundedButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });

        centerPanel.add(retryBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(exitBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        centerWrap.add(centerPanel, gbc);

        // Dummy leaderboard at bottom
        String[] columns = {"Username", "Score"};
        Object[][] data = {
                {"Alice", 9},
                {"Bob", 8},
                {"Charlie", 7},
                {"You", 0}  // Will update dynamically
        };
        leaderboard = new JTable(data, columns);
        leaderboard.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        leaderboard.setRowHeight(24);
        leaderboard.setEnabled(false);
        leaderboard.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        leaderboardScroll = new JScrollPane(leaderboard);
        leaderboardScroll.setBorder(BorderFactory.createTitledBorder("Leaderboard (Sample)"));
        leaderboardScroll.setOpaque(false);
        add(leaderboardScroll, BorderLayout.SOUTH);
    }

    // ✅ Method to set quiz result (matches QuizScreen call)
    public void setResult(int score, int total, int[] userAnswers, Question[] questions, int skipped) {
        scoreLabel.setText("Your Score: " + score + " / " + total);

        int correct = score;
        int wrong = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] != -2 && userAnswers[i] != questions[i].getCorrectIndex()) wrong++;
        }

        correctLabel.setText("Correct Answers: " + correct);
        wrongLabel.setText("Wrong Answers: " + wrong);
        skippedLabel.setText("Skipped: " + skipped);

        emojiLabel.setText(score >= total * 0.6 ? "\uD83D\uDE0E Pass!" : "\uD83D\uDE22 Fail");

        // Update leaderboard dynamically
        leaderboard.setValueAt(score, 3, 1);  // Update 'You' row
    }
}
