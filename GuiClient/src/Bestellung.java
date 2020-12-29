import datenstrukturklassen.linear.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bestellung extends JFrame {
    private ShopGUI gui;
    private Shopclient client;

    private DefaultListModel listEinkaufswagenModel;
    private Einkaufswagen einkaufswagen;

    private JPanel panel1;
    private JTextField txtSumme;
    private JButton btnBestellen;
    private JList list1;
    private JTextField txtPreis;
    private JTextField txtMenge;
    private JTextField txtZsumme;
    private JTextArea txtaInfo;
    private JLabel informationenLabel;
    private JTextField txtBestand;
    private JButton btnAbbrechen;
    private JButton btnLeeren;
    private JButton btnBearbeiten;
    private JButton btnEntfernen;

    public Bestellung(Einkaufswagen e) {
        this.einkaufswagen = e;

        initialise();
        listEinkaufswagenModel = new DefaultListModel<>();
        list1.setModel(listEinkaufswagenModel);

        displayEinkaufswagen();

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int produktNummer = list1.getSelectedIndex();
                if (produktNummer >= 0) {
                    Produkt p = get(einkaufswagen.getL(), produktNummer).getKey();
                    txtPreis.setText(p.getPreis() + "€");
                    txtMenge.setText(String.valueOf(get(einkaufswagen.getL(), produktNummer).getValue()));
                    txtBestand.setText(p.getLagerbestand());
                    int zsumme = Integer.parseInt(get(einkaufswagen.getL(), produktNummer).getKey().getPreis()) *
                    get(einkaufswagen.getL(), produktNummer).getValue();
                    txtZsumme.setText(String.valueOf(zsumme) + "€");
                    txtaInfo.setText(p.getTitel() + "\n" + p.getAutor() + "\n" + p.getErscheinungsdatum() + "\n" + p.getVerlag() + "\n" + p.getIsbn()
                            + "\n" + p.getGenre()+ "\n"  + p.getSprache() + "\n" +
                            p.getAltersfreigabe() + "\n" + p.getBeschreibung());

                    btnBearbeiten.setEnabled(true);
                    btnEntfernen.setEnabled(true);
                    txtMenge.requestFocusInWindow();

                }
            }
        });
        btnAbbrechen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.displaySuche();
            }
        });
        btnEntfernen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int produktnummer = list1.getSelectedIndex();
                einkaufswagen.removeItem(get(einkaufswagen.getL(), produktnummer));
                resetInterface();
                displayEinkaufswagen();

            }
        });
        btnBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int produktnummer = list1.getSelectedIndex();
                if(Pruefer.isInteger(txtMenge.getText()) && Integer.parseInt(txtBestand.getText()) > -1){
                    if(Integer.parseInt(txtMenge.getText()) == 0 ) {
                        einkaufswagen.removeItem(get(einkaufswagen.getL(), produktnummer));
                        resetInterface();
                        displayEinkaufswagen();
                    }else if(Integer.parseInt(txtMenge.getText()) < Integer.parseInt(txtBestand.getText())){
                        einkaufswagen.editItem(get(einkaufswagen.getL(), list1.getSelectedIndex()).getKey(), Integer.parseInt(txtMenge.getText()));
                        displayEinkaufswagen();
                        list1.setSelectedIndex(produktnummer);
                    }else showMessageDialog("So viele Exemplare von diesem Buch haben wir leider nicht!");
                }else showMessageDialog("Die Menge muss eine natürliche Zahl sein!");
            }
        });
        btnLeeren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                einkaufswagen.clearList();
                listEinkaufswagenModel.removeAllElements();
                btnLeeren.setEnabled(false);
                btnBestellen.setEnabled(false);
                resetInterface();
                displayEinkaufswagen();
            }
        });
        btnBestellen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(einkaufswagen.isEmpty()) showMessageDialog("Dein Einkaufswagen ist leer!");
                else{
                    List<Paar<Produkt, Integer>> en = einkaufswagen.getL();
                    String msg = "BESTELLUNG:" + einkaufswagen.getSumme() + ":";
                    for(en.toFirst();en.hasAccess();en.next()){
                        msg = msg + en.getContent().getKey().getArtikelid() + "/" + en.getContent().getValue() + ":";
                    }client.send(msg);
                    showMessageDialog("Vielen Dank für deine Bestellung!");
                    gui.displayAnmeldung();
                }
            }
        });
    }

    private void initialise() {
        setTitle("Bestellung");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void resetInterface(){
        list1.setSelectedIndex(-1);
        txtPreis.setText("");
        txtMenge.setText("");
        txtZsumme.setText("");
        txtBestand.setText("");
        txtaInfo.setText("");
        txtSumme.setText("");
        btnBearbeiten.setEnabled(false);
        btnEntfernen.setEnabled(false);
    }

    public Paar<Produkt, Integer> get(List<Paar<Produkt, Integer>> l, int index) {
        String res = "";
        int i = 0;

        l.toFirst();
        for (l.toFirst(); l.hasAccess() && i < index; l.next()) {
            i++;
        }
        return l.getContent();
    }

    public void displayEinkaufswagen() {
        if (einkaufswagen.isEmpty()){
            showMessageDialog("Dein Einkaufswagen ist leer!");
            resetInterface();
            btnLeeren.setEnabled(false);
            btnBestellen.setEnabled(false);
        }
        else {
            List<Paar<Produkt, Integer>> en = einkaufswagen.getL();

            listEinkaufswagenModel.removeAllElements();
            for (en.toFirst(); en.hasAccess(); en.next()) {

                listEinkaufswagenModel.addElement(en.getContent().getKey().getTitel());
            }
            txtSumme.setText(String.valueOf(einkaufswagen.getSumme())+ "€");

            btnLeeren.setEnabled(true);
            btnBestellen.setEnabled(true);
        }

    }

    public void showMessageDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
