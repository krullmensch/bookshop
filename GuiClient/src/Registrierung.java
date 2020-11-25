import javax.swing.*;

public class Registrierung extends ShopGUI {

    private JPanel panel1;
    JFrame frame;

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

    public Registrierung(){
        initialise();
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

    public void close(){
        frame.dispose();
    }

    public void display(){
        frame.setVisible(true);
    }
}
