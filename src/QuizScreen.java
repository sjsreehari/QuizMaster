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

    // Sample questions (you can later load from SQL or file)
    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Rome", "Paris"},
            {"Which language runs in a web browser?", "Java", "C", "Python", "JavaScript", "JavaScript"},
            {"Which company developed Java?", "Apple", "Sun Microsystems", "Microsoft", "Google", "Sun Microsystems"}
    };

    public QuizScreen(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(36, 41, 47));

        // Question Label
        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(16,16,0,16));
        add(questionLabel, BorderLayout.NORTH);

        // Options
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);

        RoundedPanel optionsPanel = new RoundedPanel();
        optionsPanel.setLayout(new GridLayout(5, 1, 10, 10));
        optionsPanel.setPreferredSize(new Dimension(520, 260));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            options[i].setBackground(new Color(0, 0, 0, 0));
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }

        progressBar = new JProgressBar(0, questions.length);
        progressBar.setValue(1);
        progressBar.setStringPainted(true);
        optionsPanel.add(progressBar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        center.add(optionsPanel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        skipButton = new RoundedButton("Skip");
        skipButton.setToolTipText("Skip this question");
        nextButton = new RoundedButton("Next");
        nextButton.setToolTipText("Submit answer and go next");

        buttonPanel.add(skipButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
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
            // Finished quiz -> show results
            int total = questions.length;
            int[] userAnswers = new int[total];
            for (int i = 0; i < total; i++) userAnswers[i] = -2; // unknown for demo
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

    // (removed obsolete setScore wrapper)

    // Added for retry functionality
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
