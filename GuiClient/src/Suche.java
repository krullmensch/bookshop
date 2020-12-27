import datenstrukturklassen.linear.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;

public class Suche extends JFrame {
    private ShopGUI gui;
    private Shopclient client;

    private List<Produkt> listErgebnis;
    private  DefaultListModel listProduktModel;
    private Einkaufswagen einkaufswagen;

    private JPanel panel1;
    private JList list1;
    private JTextField txtSuche;
    private JComboBox cSuchtyp;
    private JButton sucheButton;
    private JTextArea beschreibungTextArea;
    private JButton inDenEinkaufswagenButton;
    private JTextArea bewertungenTextArea;
    private JSpinner spinner1;
    private JLabel lbTitel;
    private JLabel lbAlter;
    private JLabel lbSprache;
    private JLabel lbGenre;
    private JLabel lbISBN;
    private JLabel lbVerlag;
    private JLabel lbErschienen;
    private JLabel lbAuthor;
    private JLabel lbPreis;
    private JLabel lbBestand;
    private JLabel lbAvgBewertung;


    public Suche() { //ShopGUI gui, Shopclient shopclient
        //this.gui = gui;
        //this.client = shopclient;
        initialise();
        listProduktModel = new DefaultListModel<>();
        list1.setModel(listProduktModel);
        einkaufswagen = new Einkaufswagen();
        gui.referenceEinkaufswagen(einkaufswagen);

        spinner1.setValue(1);
        sucheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtSuche.getText().equals("")){
                    showMessageDialog("Keine Sucheingabe!");
                }else{
                    client.send("SUCHE:" + cSuchtyp.getActionCommand() + ":" + txtSuche.getText());
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
                    lbAuthor.setText(p.getAutor());
                    lbErschienen.setText(p.getErscheinungsdatum());
                    lbISBN.setText(p.getIsbn());
                    lbAlter.setText(p.getAltersfreigabe());
                    lbGenre.setText(p.getGenre());
                    lbSprache.setText(p.getSprache());
                    lbVerlag.setText(p.getVerlag());
                    lbPreis.setText(p.getPreis());
                    lbBestand.setText(p.getLagerbestand());
                    lbAvgBewertung.setText(p.getDurschnitssbewertung());
                    inDenEinkaufswagenButton.setEnabled(true);
                }


            }
        });
        inDenEinkaufswagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((Integer) spinner1.getValue() >= 1){
                    einkaufswagen.addItem(get(listErgebnis, list1.getSelectedIndex()), (Integer) spinner1.getValue());
                }
            }
        });
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
