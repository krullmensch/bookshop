import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anmeldung extends JFrame {

    private ShopGUI gui;
    private Shopclient client;

    //Komponenten des Fensters
    private JPanel panel1;
    private JPasswordField txtPasswort;
    private JTextField txtBenutzer;
    private JButton btnAnmelden;
    private JButton btnRegistrieren;
    private JTextField txtServerIP;
    private JTextField txtServerPort;
    private JButton btnVerbinden;
    private JTextArea txtOut;


    public Anmeldung(ShopGUI g) {
        this.gui = g;

        initialise();

        btnVerbinden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new Shopclient(txtServerIP.getText(), Integer.parseInt(txtServerPort.getText()), gui);
                gui.referenceClient(client);
                /**
                 * Da erst hier ein Objekt von ShopClient erstellt wird und die ShopGUI eine Referenz benötigt,
                 * wird der client an die ShopGUI übermittelt
                 */


            }
        });

        btnAnmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields()) {
                    String msg = "ANM:" + txtBenutzer.getText() + ":" + parsePasswort();
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

    private void initialise() { //Erstellt ein Fenster mit panel1 und den dazugehörigen Komponenten
        setTitle("Anmeldung");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private Boolean checkFields() { //Überprüft die Texteingabe
        if (txtBenutzer.getText().equals("") && txtPasswort.getPassword().length == 0) {
            showMessageDialog("Bitte gib Benutzername und Passwort ein!");
            return false;
        } else if (txtBenutzer.getText().equals("")) {
            showMessageDialog("Bitte gib deinen Benutzernamen ein!");
            return false;
        } else if (txtPasswort.getPassword().length == 0) {
            showMessageDialog("Bitte gib dein Passwort ein!");
            return false;
        } else if (!Pruefer.checkField(txtBenutzer.getText()) || !Pruefer.checkField(parsePasswort())) {
            showMessageDialog("Eingabe nicht korrekt!" + "\n" + "Beachte dass, die Eingabefelder nicht leer seien dürfen und " + "\n" +
                    "die Zeichen ':', '/' und ';' nicht in unserem Shop erlaubt sind.");
            return false;
        } else return true;
    }

    private String parsePasswort() { //Wandelt das Passwort in einen String um
        String passwort = "";
        for (int i = 0; i < txtPasswort.getPassword().length; i++) {
            passwort = passwort + txtPasswort.getPassword()[i];
        }
        return passwort;
    }

    public void showMessageDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void enableAnmeldung() { //Freigabe der Button, damit sich ein User anmelden kann
        btnAnmelden.setEnabled(true);
        btnRegistrieren.setEnabled(true);
        btnVerbinden.setEnabled(false);
    }


}