import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginScreen loginScreen;
    private Dashboard dashboard;
    private QuizScreen quizScreen;
    private ResultScreen resultScreen;

    public MainApp() {
        setTitle("Online Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginScreen = new LoginScreen(this);
        dashboard = new Dashboard(this);
        quizScreen = new QuizScreen(this);
    resultScreen = new ResultScreen(this);

        mainPanel.add(loginScreen, "Login");
        mainPanel.add(dashboard, "Dashboard");
        mainPanel.add(quizScreen, "Quiz");
        mainPanel.add(resultScreen, "Result");

        add(mainPanel);
        showScreen("Login");
    }

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

    public Dashboard getDashboard() { return dashboard; }
    public QuizScreen getQuizScreen() { return quizScreen; }
    public ResultScreen getResultScreen() { return resultScreen; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
