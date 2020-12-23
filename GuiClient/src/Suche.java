import javax.swing.*;
import java.util.Formatter;

public class Suche extends JFrame {
    private JPanel panel1;
    private JList list1;
    private JTextField textSuche;
    private JComboBox comboBox1;
    private JButton sucheButton;


    public Suche(){ initialise();
    }

    private void initialise() {
        setTitle("Suche");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
