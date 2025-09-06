import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizScreen extends JPanel {
    private MainApp mainApp;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton, skipButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JProgressBar progressBar;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Rome", "Paris"},
            {"Which language runs in a web browser?", "Java", "C", "Python", "JavaScript", "JavaScript"},
            {"Which company developed Java?", "Apple", "Sun Microsystems", "Microsoft", "Google", "Sun Microsystems"}
    };

    public QuizScreen(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(248, 249, 251));

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        questionLabel.setForeground(new Color(32, 33, 36));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(16,16,0,16));
        add(questionLabel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);

        RoundedPanel optionsPanel = new RoundedPanel();
        optionsPanel.setLayout(new GridLayout(6, 1, 8, 8));
        optionsPanel.setPreferredSize(new Dimension(520, 240));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            options[i].setOpaque(true);
            options[i].setBackground(Color.WHITE);
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }

        progressBar = new JProgressBar(0, questions.length);
        progressBar.setValue(1);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(33,150,243));
        optionsPanel.add(progressBar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        center.add(optionsPanel, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 16));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 16, 0));

        JButton backBtn = new RoundedButton("Back");
        backBtn.setToolTipText("Return to dashboard");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(QuizScreen.this, "Exit quiz and return to Dashboard?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    resetQuiz();
                    mainApp.showScreen("Dashboard");
                }
            }
        });

        skipButton = new RoundedButton("Skip");
        skipButton.setToolTipText("Skip this question");
        nextButton = new RoundedButton("Next");
        nextButton.setToolTipText("Submit answer and go next");

        buttonPanel.add(backBtn);
        buttonPanel.add(skipButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                nextQuestion();
            }
        });

        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestion();
            }
        });

        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestionIndex][i + 1]);
            }
            optionGroup.clearSelection();
            if (progressBar != null) {
                progressBar.setValue(currentQuestionIndex + 1);
                progressBar.setString("Question " + (currentQuestionIndex + 1) + " of " + questions.length);
            }
        } else {
            int total = questions.length;
            int[] userAnswers = new int[total];
            for (int i = 0; i < total; i++) userAnswers[i] = -2;
            Question[] qs = new Question[total];
            for (int i = 0; i < total; i++) {
                String[] opts = {questions[i][1], questions[i][2], questions[i][3], questions[i][4]};
                int correctIndex = 0;
                for (int j = 1; j <= 4; j++) {
                    if (questions[i][j].equals(questions[i][5])) { correctIndex = j - 1; break; }
                }
                qs[i] = new Question(questions[i][0], opts, correctIndex);
            }
            mainApp.getResultScreen().setResult(score, total, userAnswers, qs, 0);
            mainApp.showScreen("Result");
        }
    }


    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        loadQuestion();
    }

    private void checkAnswer() {
        String correctAnswer = questions[currentQuestionIndex][5];
        for (JRadioButton option : options) {
            if (option.isSelected() && option.getText().equals(correctAnswer)) {
                score++;
                break;
            }
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        loadQuestion();
    }
}
