import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrierung extends JFrame {

    private ShopGUI gui;
    private Shopclient client;
    private StateGUI state;

    private JPanel panel1;

    //components
    private JTextField txtEmail;
    private JTextField txtTel;
    private JButton btnSendReg;
    private JTextField txtStraße;
    private JTextField txtHsnr;
    private JTextField txtOrt;
    private JTextField txtPlz;
    private JTextField txtRegBenutzer;
    private JPasswordField txtRegPasswort;
    private JTextField txtVorname;
    private JTextField txtNachname;
    private JComboBox cBoxGeschlecht;
    private JTextField txtGeburtstag;

    public Registrierung(ShopGUI gui, Shopclient shopclient, StateGUI state){
        this.gui = gui;
        this.state = state;
        this.client = shopclient;

        initialise();

        btnSendReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.displayAnmeldung();
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

    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, "msg");
    }


    public void printOut(String out){

    }
}
