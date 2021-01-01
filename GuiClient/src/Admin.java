import datenstrukturklassen.linear.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private AdminClient client;
    private String letzteSuche;

    private List<Produkt> listErgebnis;
    private  DefaultListModel listProduktModel;

    private JPanel panel1;
    private JList list1;
    private JTextField txtSuche;
    private JComboBox cSuchtyp;
    private JButton btnSuchen;
    private JTextArea txtaBeschreibung;
    private JTextArea txtaBewertung;
    private JTextField txtTitel;
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
    private JTextField txtServerIP;
    private JTextField txtServerPort;
    private JButton btnVerbinden;
    private JTextField txtBenutzer;
    private JPasswordField txtPasswort;
    private JButton btnAnmelden;
    private JButton btnAlle;
    private JButton btnEntfernen;
    private JButton btnBearbeiten;
    private JButton btnAbmelden;
    private JButton btnNeuerArtikel;
    private JButton btnAdd;


    public Admin() {
        Admin admin = this;
        initialise();
        listProduktModel = new DefaultListModel<>();
        list1.setModel(listProduktModel);

        btnVerbinden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new AdminClient(txtServerIP.getText(), Integer.parseInt(txtServerPort.getText()), admin);

            }
        });

        btnAnmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFieldsAnm()) {
                    String msg = "ANM:" +  txtBenutzer.getText() + ":" + parsePasswort();
                    client.send(msg);
                }
            }
        });


        btnSuchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Pruefer.checkField(txtSuche.getText())){
                    showMessageDialog("Unzulässige Sucheingabe!");
                }else{
                    letzteSuche = "SUCHE:" + cSuchtyp.getSelectedItem().toString() + ":" + txtSuche.getText();
                    client.send(letzteSuche);
                }

            }
        });

        btnAlle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letzteSuche = "SELECTALL";
                client.send(letzteSuche);

            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int produktNummer = list1.getSelectedIndex();
                if(produktNummer >= 0){
                    Produkt p = get(listErgebnis, produktNummer);
                    txtTitel.setText(p.getTitel());
                    txtAuthor.setText(p.getAutor());
                    txtErschienen.setText(p.getErscheinungsdatum());
                    txtISBN.setText(p.getIsbn());
                    txtAlter.setText(p.getAltersfreigabe());
                    txtGenre.setText(p.getGenre());
                    txtSprache.setText(p.getSprache());
                    txtVerlag.setText(p.getVerlag());
                    txtPreis.setText(p.getPreis());
                    txtBestand.setText(p.getLagerbestand());
                    txtAvgBewertung.setText(p.getDurschnitssbewertung());
                    txtaBeschreibung.setText(p.getBeschreibung());

                    String b = "";
                    for(int i = 0;i < p.getBewertung().length; i++) {
                        b = b + p.getBewertung()[i] + "\n" + "\n";
                    }
                    txtaBewertung.setText(b);

                    btnEntfernen.setEnabled(true);
                    btnBearbeiten.setEnabled(true);
                    btnNeuerArtikel.setEnabled(true);
                    btnAdd.setEnabled(false);
                }


            }
        });
        btnAbmelden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send("ABM");
            }
    });
        btnBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int produktnummer = list1.getSelectedIndex();
                if(checkFieldsProdukt()){
                client.send("PRODUKTEDIT:"+get(listErgebnis, produktnummer).getArtikelid() + ":" + txtTitel.getText() + ":" + txtAuthor.getText() + ":"
                        + txtErschienen.getText() + ":" + txtVerlag.getText() + ":" + txtISBN.getText() + ":" + txtGenre.getText() + ":" +
                         txtSprache.getText() + ":" + txtaBeschreibung.getText() + ":" + txtPreis.getText() + ":" + txtBestand.getText()); }
                refreshSuche();
            }
        });
        btnEntfernen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int produktnummer = list1.getSelectedIndex();
                client.send("PRODUKTDELETE:"+get(listErgebnis, produktnummer).getArtikelid());
                refreshSuche();

            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFieldsProdukt()){
                    client.send("PRODUKTADD:" + txtTitel.getText() + ":" + txtAuthor.getText() + ":"
                            + txtErschienen.getText() + ":" + txtVerlag.getText() + ":" + txtISBN.getText() + ":" + txtGenre.getText() + ":" +
                            txtSprache.getText() + ":" + txtaBeschreibung.getText() + ":" + txtPreis.getText() + ":" + txtBestand.getText()); }
            }
        });
        btnNeuerArtikel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetProduktansicht();
                btnAdd.setEnabled(true);
                btnNeuerArtikel.setEnabled(false);
            }
        });
    }

    public void resetSuche(){
        listProduktModel.removeAllElements();
        txtTitel.setText("");
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
        btnAdd.setEnabled(false);
        btnEntfernen.setEnabled(false);
        btnBearbeiten.setEnabled(false);
        //btn false
    }

    public void resetProduktansicht(){
        list1.setSelectedIndex(-1);
        txtTitel.setText("");
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
        btnAdd.setEnabled(false);
        btnEntfernen.setEnabled(false);
        btnBearbeiten.setEnabled(false);
        btnNeuerArtikel.setEnabled(true);
    }

    public void resetAnmeldung(){
        listProduktModel.removeAllElements();
        txtTitel.setText("");
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
        txtSuche.setText("");
        btnSuchen.setEnabled(false);
        btnAlle.setEnabled(false);
        btnNeuerArtikel.setEnabled(false);
        btnAdd.setEnabled(false);
        btnAnmelden.setEnabled(false);
        btnEntfernen.setEnabled(false);
        btnBearbeiten.setEnabled(false);
    }


    private Boolean checkFieldsAnm(){
        if(txtBenutzer.getText().equals("") && txtPasswort.getPassword().length == 0){
            showMessageDialog("Bitte gib Benutzername und Passwort ein!");
            return false;
        }else if(txtBenutzer.getText().equals("") ){
            showMessageDialog("Bitte gib deinen Benutzernamen ein!");
            return false;
        }else if(txtPasswort.getPassword().length == 0){
            showMessageDialog("Bitte gib dein Passwort ein!");
            return false;
        }else if(!Pruefer.checkField(txtBenutzer.getText()) || !Pruefer.checkField(parsePasswort())){
            showMessageDialog("Eingabe eines ungültigen Zeichens" );
            return false;
        }else return true;
    }

    private Boolean checkFieldsProdukt(){
        if(Pruefer.checkField(txtTitel.getText()) && Pruefer.checkField(txtAuthor.getText()) && Pruefer.checkField(txtErschienen.getText())
                && Pruefer.checkField(txtVerlag.getText()) && Pruefer.checkField(txtISBN.getText()) && Pruefer.checkField(txtGenre.getText())
                && Pruefer.checkField(txtSprache.getText()) && Pruefer.checkField(txtaBeschreibung.getText()) && Pruefer.isPreis(txtPreis.getText())
                && Pruefer.isInteger(txtBestand.getText())){
            return true;
        }else{
            showMessageDialog("Eingabe nicht korrekt!" + "\n" + "Beachte dass, die Eingabefelder nicht leer seien dürfen und " + "\n" +
                    "die Zeichen ':', '/' und ';' nicht in unserem Shop erlaubt sind.");
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
        setTitle("Admin");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void displayErgebnis(List<Produkt> ergebnis){
        resetSuche();
        this.listErgebnis = ergebnis;

        listProduktModel.removeAllElements();
        for(listErgebnis.toFirst();listErgebnis.hasAccess();listErgebnis.next()){
            listProduktModel.addElement(listErgebnis.getContent().getTitel());
        }

    }

    public void showMessageDialog(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    public void enableAnmeldung() {
        btnVerbinden.setEnabled(false);
        btnAnmelden.setEnabled(true);
        btnSuchen.setEnabled(false);
        btnAlle.setEnabled(false);
    }

    public void enableSuche() {
        btnAnmelden.setEnabled(false);
        btnAbmelden.setEnabled(true);
        btnSuchen.setEnabled(true);
        btnAlle.setEnabled(true);
        btnNeuerArtikel.setEnabled(true);
    }

    public void refreshSuche() {
        client.send(letzteSuche);
    }
}
