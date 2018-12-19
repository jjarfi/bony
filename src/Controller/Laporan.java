/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Koneksi;
import Model.MoReport;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class Laporan implements Initializable {
     @FXML
    private TextField cari;

    private ObservableList<MoReport> data;

    private Koneksi koneksi;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    String query = "";
    @FXML
    private TableView<MoReport> tabdata;

    @FXML
    private TableColumn<String, MoReport> kolidpengarang;

    @FXML
    private TableColumn<String, MoReport> kolnamapengarang;

    @FXML
    private TableColumn<String, MoReport> kolidpenerbit;

    @FXML
    private TableColumn<String, MoReport> kolnamapenerbit;

    @FXML
    private TableColumn<String, MoReport> kolidbuku;

    @FXML
    private TableColumn<String, MoReport> koljudulbuku;
    @FXML
    private JFXCheckBox ckpenerbit;

    @FXML
    private JFXCheckBox ckpengarang;

    @FXML
    private JFXCheckBox ckbuku;

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
                + "    `buku`.`idpengarang`\n"
                + "    , `pengarang`.`nama`\n"
                + "    , `buku`.`idpenerbit`\n"
                + "    , `penerbit`.`nama`\n"
                + "    , `buku`.`idbuku`\n"
                + "    , `buku`.`judul`\n"
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
                String idpengarang = set.getString(1);
                String namapengarang = set.getString(2);
                String idpenerbit = set.getString(3);
                String namapenerbit = set.getString(4);
                String idbuku = set.getString(5);
                String judulbuku = set.getString(6);
                data.add(new MoReport(idpengarang, namapengarang, idpenerbit, namapenerbit, idbuku, judulbuku));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolidbuku.setCellValueFactory(new PropertyValueFactory<>("idbuku"));
        kolidpengarang.setCellValueFactory(new PropertyValueFactory<>("idpengarang"));
        kolidpenerbit.setCellValueFactory(new PropertyValueFactory<>("idpenerbit"));
        koljudulbuku.setCellValueFactory(new PropertyValueFactory<>("judulbuku"));
        kolnamapengarang.setCellValueFactory(new PropertyValueFactory<>("namapengarang"));
        kolnamapenerbit.setCellValueFactory(new PropertyValueFactory<>("namapenerbit"));
        tabdata.setItems(null);
        tabdata.setItems(data);

    }

    private void ctkPenerbit() {
        try {
            konek();
            String report = "src//Report//penerbit.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.err.println(ex);

        }

    }

    private void ctkPengarang() {
        try {
            konek();
            String report = "src//Report//pengarang.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.err.println(ex);

        }

    }

    private void ctkBuku() {
        try {
            konek();
            String report = "src//Report//buku.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.err.println(ex);

        }

    }

    @FXML
    private void PrintBTN(ActionEvent event) {
        if (ckpenerbit.isSelected() == true) {
            ctkPenerbit();
        } else if (ckpengarang.isSelected() == true) {
            ctkPengarang();
        } else if (ckbuku.isSelected() == true) {
            ctkBuku();
        }

    }
     @FXML
    public void cariper(KeyEvent event) {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from buku where idbuku LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from buku where judul LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from buku where tahun LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new MoReport(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

                }
                tabdata.setItems(data);
            } catch (SQLException ex) {
            }
        }

    }
}
