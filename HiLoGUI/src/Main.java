import javax.swing.*;

public class Main extends JFrame {

    private JLabel jsFirstPanel;
    private JPanel MainPanel;
    private JLabel info;
    private JTextField yourNumberTextField;
    private JButton guessButton;

    public Main() {
        setContentPane(MainPanel);
        setTitle("Hello hello");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
