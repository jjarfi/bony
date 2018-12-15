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
            dash = FXMLLoader.load(getClass().getResource(Fungsi.DASH));
            pengarang = FXMLLoader.load(getClass().getResource(Fungsi.PENGARANG));
            penerbit = FXMLLoader.load(getClass().getResource(Fungsi.PENERBIT));
            buku = FXMLLoader.load(getClass().getResource(Fungsi.BUKU));
            laporan = FXMLLoader.load(getClass().getResource(Fungsi.LAPORAN));
        } catch (IOException ex) {

        }
        setNode(dash);
    }

    @FXML
    public void dash(ActionEvent event) {
        setNode(dash);
    }

    @FXML
    public void dtPengarang(ActionEvent event) {
        setNode(pengarang);
    }

    @FXML
    public void dtPenerbit(ActionEvent event) {
        setNode(penerbit);
    }

    @FXML
    public void dtBuku(ActionEvent event) {
        setNode(buku);
    }
     @FXML
    public void dtLaporan(ActionEvent event) {
        setNode(laporan);
    }
}
