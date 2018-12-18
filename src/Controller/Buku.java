/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Koneksi;
import Model.MoBuku;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class Buku implements Initializable {

    private ObservableList<MoBuku> data;
    final ObservableList optionPenerbit = FXCollections.observableArrayList();
    final ObservableList optionPengarang = FXCollections.observableArrayList();
    private Koneksi koneksi;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    String query = "";
    @FXML
    private TextField idbuku;
    
    @FXML
    private TextField cari;

    @FXML
    private TextField judul;

    @FXML
    private ComboBox idpenerbit;

    @FXML
    private TextField namapenerbit;

    @FXML
    private ComboBox idpengarang;

    @FXML
    private TextField namapengarang;

  
    @FXML
    private Spinner thnbuku;

    @FXML
    private Spinner jumlah;

    @FXML
    private DatePicker tglpengadaan;

    @FXML
    private TableView<MoBuku> tabdata;

    @FXML
    private TableColumn<String, MoBuku> kolidbuku;

    @FXML
    private TableColumn<String, MoBuku> koljudulbuku;

    @FXML
    private TableColumn<String, MoBuku> kolidpenerbit;

    @FXML
    private TableColumn<String, MoBuku> kolidpengarang;

    @FXML
    private TableColumn<String, MoBuku> koltahunbuku;

    @FXML
    private TableColumn<String, MoBuku> koljumlahbuku;

    @FXML
    private TableColumn<String, MoBuku> koltanggal;


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
        spinner();
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
    @FXML
    private void refreshTable(ActionEvent event){
        hapus();
        refresh();
    }

    private void simpan() {
        konek();

        if (idbuku.getText().isEmpty()) {
            TrayNotification tn = new TrayNotification("WANING", "ID Penerbit Tidak Boleh Kosong", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));
        } else if (judul.getText().isEmpty()) {
            TrayNotification tn = new TrayNotification("WANING", "Nama Penerbit Tidak Boleh Kosong", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));

        } else {

            try {
                Statement stt = conn.createStatement();
                String sql = "select * from buku where idbuku = '" + idbuku.getText() + "'";
                rs = stt.executeQuery(sql);
                if (rs.next() == true) {
                    TrayNotification tn = new TrayNotification("WARNING", "Kode Perangkat Sudah Terdaftar", NotificationType.NOTICE);
                    tn.setAnimationType(AnimationType.SLIDE);
                    tn.showAndDismiss(Duration.seconds(1));
                } else {
                    konek();
                    String insert = "insert into buku(idbuku,judul,idpenerbit,idpengarang,tahun,jumlah,tgl) values (?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(insert);
                        pst.setString(1, idbuku.getText());
                        pst.setString(2, judul.getText());
                        pst.setString(3, idpenerbit.getValue().toString());
                        pst.setString(4, idpengarang.getValue().toString());
                        pst.setString(5, thnbuku.getEditor().getText());
                        pst.setString(6, jumlah.getValue().toString());
                        pst.setString(7, tglpengadaan.getEditor().getText());
                        int berhasil = pst.executeUpdate();
                        if (berhasil == 1) {
                            hapus();
                            refresh();
                            TrayNotification tn = new TrayNotification("SUCCESS", "Data Berhasil Tersimpan", NotificationType.SUCCESS);
                            tn.setAnimationType(AnimationType.FADE);
                            tn.showAndDismiss(Duration.seconds(2));
                        } else {
                            TrayNotification tn = new TrayNotification("ERROR", "Simpan Data GAGAL", NotificationType.ERROR);
                            tn.setAnimationType(AnimationType.FADE);
                            tn.showAndDismiss(Duration.seconds(2));
                        }

                    } catch (SQLException ex) {
                        System.err.println("Error inserting" + ex);
                        TrayNotification tn = new TrayNotification("WARNING", "Periksa Kembali Inputan Anda", NotificationType.WARNING);
                        tn.setAnimationType(AnimationType.FADE);
                        tn.showAndDismiss(Duration.seconds(2));
                    }
                }

            } catch (SQLException ex) {
                System.err.println("Error inserting" + ex);
                TrayNotification tn = new TrayNotification("WARNING", "Periksa Kembali Inputan Anda", NotificationType.WARNING);
                tn.setAnimationType(AnimationType.FADE);
                tn.showAndDismiss(Duration.seconds(2));
            }
        }
    }

    @FXML
    private void btnsimpan(ActionEvent event) {
        try {
            simpan();
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    private void update() {

        konek();
        String insert = "update buku set judul=?,idpenerbit=?,idpengarang=?,tahun=?,jumlah=?,tgl=? where idbuku=?";
        try {
            pst = conn.prepareStatement(insert);
            pst.setString(7, idbuku.getText());
            pst.setString(1, judul.getText());
            pst.setString(2, idpenerbit.getValue().toString());
            pst.setString(3, idpengarang.getValue().toString());
            pst.setString(4, thnbuku.getEditor().getText());
            pst.setString(5, jumlah.getValue().toString());
            pst.setString(6, tglpengadaan.getEditor().getText());
            int berhasil = pst.executeUpdate();
            if (berhasil == 1) {
                hapus();
                refresh();
                TrayNotification tn = new TrayNotification("SUCCESS", "Update Data Berhasil", NotificationType.SUCCESS);
                tn.setAnimationType(AnimationType.FADE);
                tn.showAndDismiss(Duration.seconds(2));
            } else {
                TrayNotification tn = new TrayNotification("ERROR", "Update GAGAL", NotificationType.ERROR);
                tn.setAnimationType(AnimationType.FADE);
                tn.showAndDismiss(Duration.seconds(2));
            }

        } catch (SQLException ex) {
            System.err.println("Error inserting" + ex);
            TrayNotification tn = new TrayNotification("WARNING", "Periksa Kembali Inputan Anda", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.FADE);
            tn.showAndDismiss(Duration.seconds(2));
        }
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
        try {
            update();
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    private void delete() {
        konek();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("HAPUS");
        alert.setHeaderText("ID Buku : " + idbuku.getText() + "\nJudul : " + judul.getText());
        alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
        String sql = "delete from buku where idbuku = ? ";
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, idbuku.getText());
                int i = pst.executeUpdate();
                if (i == 1) {
                    hapus();
                    refresh();
                    TrayNotification tn = new TrayNotification("SUKSES", "Data Berhasil Terhapus", NotificationType.SUCCESS);
                    tn.setAnimationType(AnimationType.POPUP);
                    tn.showAndDismiss(Duration.seconds(2));
                } else {
                    TrayNotification tn = new TrayNotification("GAGAL", "Tidak ada data yang terhapus", NotificationType.WARNING);
                    tn.setAnimationType(AnimationType.POPUP);
                    tn.showAndDismiss(Duration.seconds(2));

                }
            } catch (SQLException ex) {

            }
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        delete();
    }

    private void hapus() {
        idbuku.setText("");
        judul.setText("");
        namapenerbit.setText("");
        namapengarang.setText("");
        idpenerbit.setValue(null);
        idpengarang.setValue(null);
        thnbuku.getEditor().setText("");
        tglpengadaan.getEditor().setText("");
        jumlah.getEditor().setText("");

    }

    private void refresh() {
        konek();
        tabdata.getItems().clear();
        query = "select * from buku";
        tabelper();
    }

    private void tabelper() {

        konek();
        try {
            data = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idbuk = set.getString(1);
                String judulbuk = set.getString(2);
                String idpen = set.getString(3);
                String idpenga = set.getString(4);
                String tahunbuk = set.getString(5);
                String jumlahbuk = set.getString(6);
                String tglbuk = set.getString(7);
                data.add(new MoBuku(idbuk, judulbuk, idpen, idpenga, tahunbuk, jumlahbuk, tglbuk));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolidbuku.setCellValueFactory(new PropertyValueFactory<>("idbuk"));
        koljudulbuku.setCellValueFactory(new PropertyValueFactory<>("judulbuk"));
        kolidpenerbit.setCellValueFactory(new PropertyValueFactory<>("idpen"));
        kolidpengarang.setCellValueFactory(new PropertyValueFactory<>("idpenga"));
        koltahunbuku.setCellValueFactory(new PropertyValueFactory<>("tahunbuk"));
        koljumlahbuku.setCellValueFactory(new PropertyValueFactory<>("jumlahbuk"));
        koltanggal.setCellValueFactory(new PropertyValueFactory<>("tglbuk"));
        tabdata.setItems(null);
        tabdata.setItems(data);

    }

    private void spinner() {
        SpinnerValueFactory jmlh = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        jumlah.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        jumlah.setValueFactory(jmlh);
        
        SpinnerValueFactory thn = new SpinnerValueFactory.IntegerSpinnerValueFactory(1999, 2018);
        thnbuku.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        thnbuku.setValueFactory(thn);
    }

    @FXML
    private void loadKDPenerbit(ActionEvent event) {
        loadkdpenerbit();
    }

    private void loadkdpenerbit() {
        konek();
        try {
            String sql = "select * from penerbit where idpenerbit = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String) idpenerbit.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            while (rs.next()) {

                String namaprg = rs.getString("nama");
                namapenerbit.setText(namaprg);

            }
        } catch (SQLException ex) {

        }
    }

    private void penerbit() {
        konek();
        optionPenerbit.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from penerbit");
            while (rs.next()) {
                optionPenerbit.add(rs.getString("idpenerbit"));
                idpenerbit.setItems(optionPenerbit);

            }

        } catch (SQLException ex) {

            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void loadnamapenerbit(MouseEvent event) {
        penerbit();
    }

    @FXML
    private void loadKDPengarang(ActionEvent event) {
        loadkdpengarang();
    }

    private void loadkdpengarang() {
        konek();
        try {
            String sql = "select * from pengarang where idpengarang = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String) idpengarang.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            while (rs.next()) {

                String namaprg = rs.getString("nama");
                namapengarang.setText(namaprg);

            }
        } catch (SQLException ex) {

        }
    }

    private void pengarang() {
        konek();
        optionPengarang.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from pengarang");
            while (rs.next()) {
                optionPengarang.add(rs.getString("idpengarang"));
                idpengarang.setItems(optionPengarang);

            }

        } catch (SQLException ex) {

            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void loadnamapengarang(MouseEvent event) {
        pengarang();
    }

    private void setTable() {
        try {
            MoBuku pr = tabdata.getItems().get(tabdata.getSelectionModel().getSelectedIndex());
            idbuku.setText(pr.getIdbuk());
            judul.setText(pr.getJudulbuk());
            idpenerbit.setValue(pr.getIdpen());
            idpengarang.setValue(pr.getIdpenga());
            thnbuku.getEditor().setText(pr.getTahunbuk());
            jumlah.getEditor().setText(pr.getJumlahbuk());
            tglpengadaan.getEditor().setText(pr.getTglbuk());
        } catch (Exception ex) {

        }
    }

    @FXML
    private void setdariTabel(MouseEvent event) {
        setTable();

    }

    @FXML
    public void cariper(KeyEvent event) throws SQLException {

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
                    data.add(new MoBuku(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

                }
                tabdata.setItems(data);
            } catch (SQLException ex) {
            }
        }

    }

}
