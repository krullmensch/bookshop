import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Anmeldung extends ShopGUI {

    private JFrame frame;
    private JPanel panel1;

    //Components
    private JPasswordField txtPasswort;
    private JTextField txtBenutzer;
    private JButton btnAnmelden;
    private JButton btnRegistrieren;
    private JTextField txtServerIP;
    private JTextField txtServerPort;
    private JButton btnVerbinden;
    private JTextArea txtOut;

    public Anmeldung(){
        initialise();

        btnVerbinden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new Shopclient(txtServerIP.getText(), Integer.parseInt(txtServerPort.getText()), gui, state);

            }
        });
        btnAnmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "ANMELDUNG:" + txtBenutzer.getText() + txtPasswort.getName();
                client.send(msg);
            }
        });
    }

    private void initialise() {
        frame = new JFrame("Bookshop");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, "Anmeldung erflogreich!");
    }

    @Override
    public void printOut (String msg) {
        txtOut.setText(msg);

    }

    @Override
    public void enableAnmeldung() {
        btnAnmelden.setEnabled(true);
        btnRegistrieren.setEnabled(true);
    }

    public void close(){
        frame.dispose();
    }

    public void display(){
        frame.setVisible(true);
    }


}