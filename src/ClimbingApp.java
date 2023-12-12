import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Climbing app.
 */
public class ClimbingApp {
    private JFrame frame;
    private UserData userData;

    /**
     * Show login screen.
     */
    public void showLoginScreen() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String userId = userIdField.getText();
            // Perform user authentication here and set hasHistory accordingly
            userData = new UserData(userId,5);
            frame.dispose();
            try {
                showDifficultyCheckScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(loginButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    private void showDifficultyCheckScreen() throws IOException {
        frame.dispose();
        if (userData.hasHistory()) {
            MatchDifficulty m2 = new MatchDifficulty("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\cleaned_climbing_data.csv", userData.getDifficulty());
            frame.dispose();
            showClimbOfTheDayScreen(m2.getCotd());
        } else {
            showIntegerInputScreen();
        }
        frame.setVisible(true);
    }

    private void showIntegerInputScreen() {
        frame = new JFrame("Integer Input");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel integerLabel = new JLabel("Enter an Integer:");
        JTextField integerField = new JTextField();
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> {
            // Perform validation and handling of the entered integer
            int enteredInteger = Integer.parseInt(integerField.getText());
            try {
                MatchDifficulty m1 = new MatchDifficulty("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\cleaned_climbing_data.csv", enteredInteger);
                frame.dispose();
                showClimbOfTheDayScreen(m1.getCotd());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(integerLabel);
        panel.add(integerField);
        panel.add(okButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    private void showClimbOfTheDayScreen(String m1) {
        frame = new JFrame("Climb of the Day");
        JPanel cotdpanel = new JPanel();
        JLabel label = new JLabel(m1);
        cotdpanel.add(label);
        frame.add(cotdpanel);
        frame.repaint();

        JButton generateDifficultyButton = new JButton("Generate Difficulty");
        generateDifficultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showBoardScreen();
            }
        });

        frame.getContentPane().add(BorderLayout.SOUTH, generateDifficultyButton);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    private void showBoardScreen() {
        SwingUtilities.invokeLater(() ->{
            Board board = new Board();
            board.setVisible(true);
        });
    }
}
