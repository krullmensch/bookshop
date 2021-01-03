import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;

public class Registrierung extends JFrame {

    private Shopclient client;

    private JPanel panel1;

    //components
    private JTextField txtEmail;
    private JTextField txtTel;
    private JButton btnSendReg;
    private JTextField txtStrasse;
    private JTextField txtHsnr;
    private JTextField txtOrt;
    private JTextField txtPlz;
    private JTextField txtRegBenutzer;
    private JPasswordField txtRegPasswort;
    private JTextField txtVorname;
    private JTextField txtNachname;
    private JComboBox cBoxGeschlecht;
    private JTextField txtGeburtstag;

    public Registrierung(Shopclient shopclient){
        this.client = shopclient;
        initialise();

        /**
         * Sendet die Registrierung falls vollständig an den Server
         */
        btnSendReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFields()){
                    String msg = "REG:" + txtRegBenutzer.getText() + ":" + parsePasswort() + txtVorname.getText() + ":" + txtNachname.getText() + ":" +
                            txtGeburtstag.getText() + ":" + cBoxGeschlecht.getSelectedItem().toString() + ":" + txtStrasse.getText() + ":" +
                            txtHsnr.getText() + ":" + txtOrt.getText() + ":" + txtPlz.getText() + ":" + txtEmail.getText() + txtTel.getText();
                    client.send(msg);
                }
            }
        });
    }

    private void initialise() {
        setTitle("Registrierung");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private Boolean checkFields(){
        if(Pruefer.checkField(txtRegBenutzer.getText()) && !parsePasswort().equals("") && Pruefer.checkField(txtVorname.getText()) &&
                Pruefer.checkField(txtNachname.getText()) && Pruefer.checkField(txtGeburtstag.getText()) && Pruefer.checkField(txtStrasse.getText())
                && Pruefer.isInteger(txtHsnr.getText()) && Pruefer.checkField(txtOrt.getText()) && Pruefer.isInteger(txtPlz.getText())
                && Pruefer.emailCheck(txtEmail.getText()) && Pruefer.isInteger(txtTel.getText())){
            return true;
        }
        else{
            showMessageDialog("Eingabe nicht korrekt!" + "\n" + "Beachte dass, die Eingabefelder nicht leer seien dürfen und " + "\n" +
                    "die Zeichen ':', '/' und ';' nicht in unserem Shop erlaubt sind.");
            return false;
        }
    }


    private String parsePasswort(){
        String passwort = "";
        for(int i = 0; i < txtRegPasswort.getPassword().length; i++){
            passwort = passwort + txtRegPasswort.getPassword()[i];
        }
        return passwort;
    }


    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }


}
