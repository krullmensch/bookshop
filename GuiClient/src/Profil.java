import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profil extends JFrame {

    private Shopclient client;
    private String[] profil;

    private JPanel panel1;

    //components
    private JTextField txtEmail;
    private JTextField txtTel;
    private JButton btnBearbeiten;
    private JTextField txtStrasse;
    private JTextField txtHsnr;
    private JTextField txtOrt;
    private JTextField txtPlz;
    private JTextField txtBenutzer;
    private JPasswordField txtPasswort;
    private JTextField txtVorname;
    private JTextField txtNachname;
    private JComboBox cBoxGeschlecht;
    private JTextField txtGeburtstag;

    public Profil(String[] profil){ //Shopclient shopclient
        //this.client = shopclient;
        this.profil = profil;
        initialise();

        //profil[0] = "PROFIL"
        txtBenutzer.setText(profil[1]);
        txtPasswort.setText(profil[2]);
        txtVorname.setText(profil[3]);
        txtNachname.setText(profil[4]);
        txtGeburtstag.setText(profil[5]);
        cBoxGeschlecht.setSelectedItem(profil[6]);
        txtStrasse.setText(profil[7]);
        txtHsnr.setText(profil[8]);
        txtOrt.setText(profil[9]);
        txtPlz.setText(profil[10]);
        txtEmail.setText(profil[11]);
        txtTel.setText(profil[12]);


        btnBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFields()){
                    String msg = "PROFILEDIT:" + txtBenutzer.getText() + ":" + parsePasswort() + txtVorname.getText() + ":" + txtNachname.getText() + ":" +
                            txtGeburtstag.getText() + ":" + cBoxGeschlecht.getSelectedItem().toString() + ":" + txtStrasse.getText() + ":" +
                            txtHsnr.getText() + ":" + txtOrt.getText() + ":" + txtPlz.getText() + ":" + txtEmail.getText() + txtTel.getText();
                    client.send(msg);
                }
            }
        });
    }

    private void initialise() {
        setTitle("Profil");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private Boolean checkFields(){
        if(Pruefer.checkField(txtBenutzer.getText()) && !parsePasswort().equals("") && Pruefer.checkField(txtVorname.getText()) &&
                Pruefer.checkField(txtNachname.getText()) && Pruefer.checkField(txtGeburtstag.getText()) && Pruefer.checkField(txtStrasse.getText())
                && Pruefer.isInteger(txtHsnr.getText()) && Pruefer.checkField(txtOrt.getText()) && Pruefer.isInteger(txtPlz.getText())
                && Pruefer.emailCheck(txtEmail.getText()) && Pruefer.isInteger(txtTel.getText())){
            return true;
        }
        else{
            showMessageDialog("Eingabe nicht korrekt! Beachte dass, die Eingabefelder nicht leer seien dürfen und" +
                    " die Zeichen ':', '/' und ';' nicht in unserem Shop erlaubt sind.");
            return false;
        }
    }

    private String parsePasswort(){
        String passwort = "";
        for(int i = 0; i < txtPasswort.getPassword().length; i++){
            passwort = passwort + txtPasswort.getPassword()[i];
        }
        return passwort;
    }

    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

}
