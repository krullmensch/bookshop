import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Anmeldung extends JFrame {

    private ShopGUI gui;
    private Shopclient client;
    private StateGUI state;

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


    public Anmeldung(ShopGUI gui, Shopclient shopclient, StateGUI state){
        this.gui = gui;
        this.state = state;
        this.client = shopclient;

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
                if(txtBenutzer.getText().equals("") && txtPasswort.getPassword().equals("")) {
                    printOut("Gebe deinen Benutzernamen und Passwort ein, um dich anzumelden!");
                }else if(txtBenutzer.getText().equals("")) {
                    printOut("Du hast vergessen einen Benutzernamen einzugeben!");
                }else if(txtPasswort.getPassword().equals("")){
                    printOut("Ohne Passwort gehts nicht!");
                }else {
                    String msg = "ANMELDUNG:" + txtBenutzer.getText() + txtPasswort.getPassword();
                    client.send(msg);
                }
            }
        });
        btnRegistrieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.displayRegistrierung();
            }
        });
    }

    private void initialise() {
        setTitle("Anmeldung");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    public void printOut (String msg) {
        txtOut.setText(msg);

    }

    public void enableAnmeldung() {
        btnAnmelden.setEnabled(true);
        btnRegistrieren.setEnabled(true);
    }


}