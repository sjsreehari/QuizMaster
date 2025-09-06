Java Swing Quiz App
===================

A simple, modern Java Swing quiz application showcasing core OOP concepts with a clean, light UI theme.

Features
--------
- Login, Dashboard, Quiz, and Result screens
- Modern rounded buttons and cards
- Quiz navigation: Back, Skip, Next; no dead-ends
- Result summary and sample leaderboard
- Small, readable codebase (pure Java + Swing only)

How to Run
----------
1. Build
   - Windows PowerShell (project root):
     - `javac -d bin src\\*.java`
2. Launch GUI
   - `javaw -cp bin MainApp`
   - Use `java -cp bin MainApp` if you want console output.

Project Structure
-----------------
```
src/
  MainApp.java          // App entry; CardLayout + screens
  LoginScreen.java      // Login form with rounded card
  Dashboard.java        // Start/Leaderboard/About/Logout
  QuizScreen.java       // Question, options, progress, Back/Skip/Next
  ResultScreen.java     // Score, breakdown, retry, back to dashboard
  RoundedButton.java    // Custom gradient button (extends JButton)
  RoundedPanel.java     // Rounded card panel (extends JPanel)
  Question.java         // Question model
```

OOP Concepts Demonstrated
-------------------------
- Encapsulation
  - `Question` stores data and exposes getters only.
  - Each screen manages its own UI state and event handlers.
- Inheritance
  - `RoundedButton extends JButton` to inherit button behavior and add custom look/feel.
  - `RoundedPanel extends JPanel` to reuse layout mechanics and customize rendering.
- Polymorphism (Method Overriding)
  - `RoundedButton#paintComponent` and `RoundedPanel#paintComponent` override Swing paint to draw gradients and rounded backgrounds.
- Composition
  - `MainApp` composes screens inside a `CardLayout` container.
  - Screens compose Swing components (labels, fields, buttons) to build larger behaviors.
- Abstraction
  - UI concerns split by screens; `MainApp#showScreen(String)` abstracts navigation without exposing internals of each screen.
- Event-driven design
  - Actions (login, start, next, skip, back, retry) are handled via listeners, decoupling UI from flow control.

UI/UX Choices
-------------
- Light theme (`#F8F9FB` background) for clarity and contrast
- Rounded cards for content blocks and consistent spacing
- Gradient primary buttons (blue) with hover/pressed states
- Progress bar on quiz to show position
- Confirm dialog when leaving a quiz in progress

Extending the App
-----------------
- Load questions from a database or JSON file
- Add timing, per-question feedback, categories, and difficulty
- Persist leaderboard to a file/DB
- Add theming toggle (light/dark)

Development Notes
-----------------
- Keep components self-contained and readable
- Prefer layout managers over fixed sizes
- Avoid blocking the EDT; use `SwingUtilities.invokeLater` for UI initialization





