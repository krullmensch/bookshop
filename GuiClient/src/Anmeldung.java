import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Anmeldung extends JFrame {

    private ShopGUI gui;
    private Shopclient client;

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


    public Anmeldung(ShopGUI gui){
        this.gui = gui;

        initialise();

        btnVerbinden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new Shopclient(txtServerIP.getText(), Integer.parseInt(txtServerPort.getText()), gui);
                gui.referenceClient(client); //Erst hier wird eine Objekt der Klasse ShopClient erstellt

            }
        });

        btnAnmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFields()) {
                    String passwort = "";
                    for(int i = 0; i < txtPasswort.getPassword().length; i++){
                        passwort = passwort + txtPasswort.getPassword()[i];
                    }
                    String msg = "ANMELDUNG:" + txtBenutzer.getText() + ":" + passwort;
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

    private Boolean checkFields(){
        if(txtBenutzer.getText().equals("") && txtPasswort.getPassword().length == 0){
            showMessageDialog("Bitte gebe Benutzername und Passwort ein!");
            return false;
        }
        else if(txtBenutzer.getText().equals("")){
            showMessageDialog("Bitte gebe deinen Benutzernamen ein!");
            return false;
        }else if(txtPasswort.getPassword().length == 0){
            showMessageDialog("Bitte gebe dein Passwort ein!");
            return false;
        }else return true;
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