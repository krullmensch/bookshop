import datenstrukturklassen.linear.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Suche extends JFrame {
    private ShopGUI gui;
    private Shopclient client;

    private List<Produkt> listErgebnis;
    private  DefaultListModel listProduktModel;
    private Einkaufswagen einkaufswagen;
    private String letzeSuche;

    private JPanel panel1;
    private JList list1;
    private JTextField txtSuche;
    private JComboBox cSuchtyp;
    private JButton sucheButton;
    private JTextArea txtaBeschreibung;
    private JButton btnEinkaufswagen;
    private JTextArea txtaBewertung;
    private JTextField txtMenge;
    private JLabel lbTitel;
    private JTextField txtAlter;
    private JTextField txtSprache;
    private JTextField txtGenre;
    private JTextField txtISBN;
    private JTextField txtVerlag;
    private JTextField txtErschienen;
    private JTextField txtAuthor;
    private JTextField txtPreis;
    private JTextField txtBestand;
    private JTextField txtAvgBewertung;
    private JMenuItem btnBestellung;
    private JMenuItem btnAbmelden;
    private JTextArea txtaBewertungschreiben;
    private JComboBox cbSterne;
    private JButton btnBewertung;


    public Suche() { //ShopGUI gui, Shopclient shopclient
        //this.gui = gui;
        //this.client = shopclient;
        initialise();
        listProduktModel = new DefaultListModel<>();
        list1.setModel(listProduktModel);

        if(einkaufswagen == null) {
            einkaufswagen = new Einkaufswagen();
            //gui.referenceEinkaufswagen(einkaufswagen);
        }


        sucheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Pruefer.checkField(txtSuche.getText())){
                    showMessageDialog("Unzulässige Sucheingabe!");
                }else{
                    client.send("SUCHE:" + cSuchtyp.getSelectedItem().toString() + ":" + txtSuche.getText());
                }

            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int produktNummer = list1.getSelectedIndex();
                if(produktNummer >= 0){
                    Produkt p = get(listErgebnis, produktNummer);
                    lbTitel.setText(p.getTitel());
                    txtAuthor.setText(p.getAutor());
                    txtErschienen.setText(p.getErscheinungsdatum());
                    txtISBN.setText(p.getIsbn());
                    txtAlter.setText(p.getAltersfreigabe());
                    txtGenre.setText(p.getGenre());
                    txtSprache.setText(p.getSprache());
                    txtVerlag.setText(p.getVerlag());
                    txtPreis.setText(p.getPreis() + "€");
                    txtBestand.setText(p.getLagerbestand());
                    txtAvgBewertung.setText(p.getDurschnitssbewertung());
                    txtaBeschreibung.setText(p.getBeschreibung());
                    txtaBewertungschreiben.setText("");

                    String b = "";
                    for(int i = 0;i < p.getBewertung().length; i++) {
                        b = b + p.getBewertung()[i] + "\n" + "\n";
                    }
                    txtaBewertung.setText(b);
                    btnEinkaufswagen.setEnabled(true);
                    btnBewertung.setEnabled(true);
                }


            }
        });
        btnEinkaufswagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Pruefer.isInteger(txtMenge.getText())){
                    if(Integer.parseInt(txtMenge.getText()) >= 0 && Integer.parseInt(txtMenge.getText()) <= Integer.parseInt(txtBestand.getText())){
                    einkaufswagen.addItem(get(listErgebnis, list1.getSelectedIndex()), Integer.parseInt(txtMenge.getText()));
                    } else showMessageDialog("Die Menge muss größer als 0 sein und zu unserem Lagerbestand passen!");
                }else showMessageDialog("Die Menge muss eine natürliche Zahl sein!");
            }
        });
        btnBestellung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.displayBestellung();
            }
        });
        btnAbmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("ABM");
            }
        });
        btnBewertung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtaBewertungschreiben.getText().equals("")) showMessageDialog("Um eine Bewertung abzuschicken, musst du erst einmal eine schreiben!");
                else if(!Pruefer.checkField(txtaBewertungschreiben.getText())) showMessageDialog("Deine Bewertung konnte nicht abgeschickt werden, da mindestens eines " +
                        "der folgenden Zeichen benutzt wurde: ':' '/' ';' ");
                else{
                    int produktNummer = list1.getSelectedIndex();
                    String msg = "BEWERTUNG:" + get(listErgebnis, produktNummer).getArtikelid() + ":" + cbSterne.getSelectedItem().toString() + ":" + txtaBewertungschreiben.getText();
                    client.send("BEWERTUNG:" + get(listErgebnis, produktNummer).getArtikelid() + ":" + cbSterne.getActionCommand() + txtaBewertungschreiben.getText());
                    showMessageDialog("Bewertung abgeschckt!");
                    client.send(letzeSuche);
                }
            }
        });
    }

    private void resetInterface(){
        listProduktModel.removeAllElements();
        lbTitel.setText("");
        txtAuthor.setText("");
        txtErschienen.setText("");
        txtISBN.setText("");
        txtAlter.setText("");
        txtGenre.setText("");
        txtSprache.setText("");
        txtVerlag.setText("");
        txtPreis.setText("");
        txtBestand.setText("");
        txtAvgBewertung.setText("");
        txtaBeschreibung.setText("");
        txtaBewertung.setText("");
        txtaBewertungschreiben.setText("");
        btnEinkaufswagen.setEnabled(false);
        btnBewertung.setEnabled(false);

    }

    public Produkt get(List<Produkt> l, int index) {
        String res = "";
        int i = 0;

        l.toFirst();
        for (l.toFirst(); l.hasAccess() && i < index; l.next()) {
            i++;
        }
        return l.getContent();
    }

    private void initialise() {
        setTitle("Suche");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void displayErgebnis(List<Produkt> ergebnis){
        resetInterface();
        this.listErgebnis = ergebnis;

        listProduktModel.removeAllElements();
        for(listErgebnis.toFirst();listErgebnis.hasAccess();listErgebnis.next()){
            listProduktModel.addElement(listErgebnis.getContent().getTitel());
        }



    }

    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
