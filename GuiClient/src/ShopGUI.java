import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShopGUI {

    private ShopGUI gui;
    private Shopclient client;
    private StateGUI state;

    private JPanel panel1;
    private JPanel panelSignin;
    private JPanel panelSearch;
    private JPanel panelProduct;
    private JPanel panelRegistration;
    private JPasswordField txtPassword;
    private JTextField txtUsername;
    private JButton btnSignin;
    private JButton btnRegister;
    private JTextField txtServerIP;
    private JTextField txtServerPort;
    private JButton btnConnect;
    private JTextArea txtOutStart;

    public ShopGUI(){
        gui = this;

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new Shopclient(txtServerIP.getText(), Integer.parseInt(txtServerPort.getText()), gui, state);

            }
        });
        btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "ANMELDUNG:" + txtUsername.getText() + txtPassword.getName();
                client.send(msg);
            }
        });
    }

    public void printOut (String msg) {
        if(state.getStateType() == StateGUIType.SIGNIN) txtOutStart.setText(msg);
        else if (state.getStateType() == StateGUIType.REGISTRATION) txtOutStart.setText(msg);
    }

    public void refreshInterface(){
        if(state.getStateType() == StateGUIType.CONNECTION){
            btnSignin.setEnabled(true);
            btnRegister.setEnabled(true);
        }if(state.getStateType() == StateGUIType.SIGNIN) {
            panelSignin.setVisible(true);
            panelSearch.setVisible(false);
            panelRegistration.setVisible(false);
            panelProduct.setVisible(false);
        } else if(state.getStateType() == StateGUIType.SEARCH) {
            panelSignin.setVisible(false);
            panelSearch.setVisible(true);
            panelRegistration.setVisible(false);
            panelProduct.setVisible(false);
        } else if(state.getStateType() == StateGUIType.REGISTRATION) {
            panelSignin.setVisible(false);
            panelSearch.setVisible(false);
            panelRegistration.setVisible(true);
            panelProduct.setVisible(false);
        } else if(state.getStateType() == StateGUIType.PRODUCT) {
            panelSignin.setVisible(false);
            panelSearch.setVisible(false);
            panelRegistration.setVisible(false);
            panelProduct.setVisible(true);
        }




    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Bookshop");
        frame.setContentPane(new ShopGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}