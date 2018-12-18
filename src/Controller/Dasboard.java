/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Koneksi;
import Model.MoDashboard;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class Dasboard implements Initializable {

    private ObservableList<MoDashboard> data;
    private Koneksi koneksi;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    String query = "";
    @FXML
    private TextField cari;

    @FXML
    private Label lblidbuku,lbljumlahbuku;

    @FXML
    private Label lblidpengarang, lbljumlahpengarang;

    @FXML
    private Label lblidpenerbit,lbljumlahpenerbit;

    @FXML
    private TextField txtjudulbuku;

    @FXML
    private TextField txtnamapengarang;

    @FXML
    private TextField txtpenerbit;

    @FXML
    private TextField txtjumlahbuku;

    @FXML
    private TextField txttahunterbit;

    @FXML
    private TextField txtalamatpengarang;

    @FXML
    private TextField txtalamatpenerbit;

    @FXML
    private TableView<MoDashboard> tabdata;

    @FXML
    private TableColumn<String, MoDashboard> kolidbuku;

    @FXML
    private TableColumn<String, MoDashboard> kolidpengarang;

    @FXML
    private TableColumn<String, MoDashboard> kolidpenerbit;

    @FXML
    private TableColumn<String, MoDashboard> koljudulbuku;

    @FXML
    private TableColumn<String, MoDashboard> kolnamapengarang;

    @FXML
    private TableColumn<String, MoDashboard> kolnamapenerbit;

    @FXML
    private TableColumn<String, MoDashboard> koljumlahbuku;

    @FXML
    private TableColumn<String, MoDashboard> koltahunterbit;

    @FXML
    private TableColumn<String, MoDashboard> kolalamatpengarang;

    @FXML
    private TableColumn<String, MoDashboard> kolalamatpenerbit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = new Koneksi();
        refresh();
        totalPengarang();
        totalPenerbit();
        totalBuku();
    }

    public void konek() {
        try {
            conn = koneksi.konekDB();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Koneksi Error" + ex);
            alert.setContentText("Pastikan MySQL anda sudah running ..");
        }

    }

    public void refresh() {
        konek();
        tabdata.getItems().clear();
        query = "SELECT\n"
                + "    `buku`.`idbuku`\n"
                + "    , `buku`.`idpenerbit`\n"
                + "    , `buku`.`idpengarang`\n"
                + "    , `buku`.`judul`\n"
                + "    , `pengarang`.`nama`\n"
                + "    , `penerbit`.`nama`\n"
                + "    , `buku`.`jumlah`\n"
                + "    , `buku`.`tahun`\n"
                + "    , `pengarang`.`alamat`\n"
                + "    , `penerbit`.`alamat`\n"
                + "FROM\n"
                + "    `perpus`.`buku`\n"
                + "    INNER JOIN `perpus`.`pengarang` \n"
                + "        ON (`buku`.`idpengarang` = `pengarang`.`idpengarang`)\n"
                + "    INNER JOIN `perpus`.`penerbit` \n"
                + "        ON (`buku`.`idpenerbit` = `penerbit`.`idpenerbit`);";
        tabelper();
    }

    public void tabelper() {

        konek();
        try {
            data = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idbuku = set.getString(1);
                String idpengarang = set.getString(2);
                String idpenerbit = set.getString(3);
                String judulbuku = set.getString(4);
                String namapengarang = set.getString(5);
                String penerbit = set.getString(6);
                String jumlahbuku = set.getString(7);
                String tahunterbit = set.getString(8);
                String alamatpengarang = set.getString(9);
                String alamatpenerbit = set.getString(10);
                data.add(new MoDashboard(idbuku, idpengarang, idpenerbit, judulbuku, namapengarang, penerbit,
                        jumlahbuku, tahunterbit, alamatpengarang, alamatpenerbit));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolidbuku.setCellValueFactory(new PropertyValueFactory<>("idbuku"));
        kolidpengarang.setCellValueFactory(new PropertyValueFactory<>("idpengarang"));
        kolidpenerbit.setCellValueFactory(new PropertyValueFactory<>("idpenerbit"));
        koljudulbuku.setCellValueFactory(new PropertyValueFactory<>("judulbuku"));
        kolnamapengarang.setCellValueFactory(new PropertyValueFactory<>("namapengarang"));
        kolnamapenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        koljumlahbuku.setCellValueFactory(new PropertyValueFactory<>("jumlahbuku"));
        koltahunterbit.setCellValueFactory(new PropertyValueFactory<>("tahunterbit"));
        kolalamatpengarang.setCellValueFactory(new PropertyValueFactory<>("alamatpengarang"));
        kolalamatpenerbit.setCellValueFactory(new PropertyValueFactory<>("alamatpenerbit"));
        tabdata.setItems(null);
        tabdata.setItems(data);

    }

    private void totalPengarang() {
        try {
            konek();
            String angka = "SELECT COUNT(*) AS jum FROM pengarang";
            rs = conn.createStatement().executeQuery(angka);

            while (rs.next() == true) {
                String to = rs.getString("jum");
                lbljumlahpengarang.setText(" : " + to);

            }
        } catch (SQLException ex) {

        }
    }
      private void totalPenerbit() {
        try {
            konek();
            String angka = "SELECT COUNT(*) AS jum FROM penerbit";
            rs = conn.createStatement().executeQuery(angka);

            while (rs.next() == true) {
                String to = rs.getString("jum");
                lbljumlahpenerbit.setText(" : " + to);

            }
        } catch (SQLException ex) {

        }
    }
      
      private void totalBuku() {
        try {
            konek();
            String angka = "SELECT COUNT(*) AS jum FROM buku";
            rs = conn.createStatement().executeQuery(angka);

            while (rs.next() == true) {
                String to = rs.getString("jum");
                lbljumlahbuku.setText(" : " + to);

            }
        } catch (SQLException ex) {

        }
    }
}
