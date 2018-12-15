/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Function.Fungsi;
import Implementasi.Aimpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class B implements Initializable {
    
    //Kelas B merupakan controller dari form B untuk tampilan navigator aplikasi

    //  Mimpl m = new Mimpl();
    Fungsi f = new Fungsi();
    Aimpl a = new Aimpl();

    AnchorPane dash, pengarang, penerbit, buku, laporan;

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane frame;

    @FXML
    public void drag(MouseEvent event) {
        a.drag(root, event);
    }

    @FXML
    public void press(MouseEvent event) {
        a.pres(root, event);
    }

    @FXML
    public void close(MouseEvent event) {
        try {
            a.tutup(root, event);
        } catch (IOException ex) {

        }
    }

    @FXML
    public void minimize(MouseEvent event) {
        a.minimize(root, event);
    }

    public void setNode(Node node) {
        frame.getChildren().clear();
        frame.getChildren().add(node);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            dash = FXMLLoader.load(getClass().getResource(Fungsi.DASH)); //load form dashboard dari kelas fungsi pada package Function
            pengarang = FXMLLoader.load(getClass().getResource(Fungsi.PENGARANG)); //load form pengarang dari kelas fungsi pada package Function
            penerbit = FXMLLoader.load(getClass().getResource(Fungsi.PENERBIT)); //load form penerbit dari kelas fungsi pada package Function
            buku = FXMLLoader.load(getClass().getResource(Fungsi.BUKU)); //load form buku dari kelas fungsi pada package Function
            laporan = FXMLLoader.load(getClass().getResource(Fungsi.LAPORAN)); //load form laporan dari kelas fungsi pada package Function
        } catch (IOException ex) {

        }
        setNode(dash);
    }

    @FXML
    public void dash(ActionEvent event) {
        //event klik untuk menampilkan form dashboard
        setNode(dash);
    }

    @FXML
    public void dtPengarang(ActionEvent event) {
        //event klik untuk menampilkan form pengarang
        setNode(pengarang);
    }

    @FXML
    public void dtPenerbit(ActionEvent event) {
        //event klik untuk menampilkan form penerbit
        setNode(penerbit);
    }

    @FXML
    public void dtBuku(ActionEvent event) {
        //event klik untuk menampilkan form buku
        setNode(buku);
    }
     @FXML
    public void dtLaporan(ActionEvent event) {
        //event klik untuk menampilkan form laporan
        setNode(laporan);
    }
}
