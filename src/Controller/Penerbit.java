/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Koneksi;
import Model.MoPenerbit;
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
public class Penerbit implements Initializable {

    private ObservableList<MoPenerbit> data;
    private Koneksi koneksi;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    String query = "";
    @FXML
    private TextField idpenerbit;
    @FXML
    private TextField cari;

    @FXML
    private TextField nmpenerbit;

    @FXML
    private TextField alamat;

    @FXML
    private TextField notelp;

    @FXML
    private TextField email;
    @FXML
    private TableView<MoPenerbit> tabdata;

    @FXML
    private TableColumn<String, MoPenerbit> kolid;

    @FXML
    private TableColumn<String, MoPenerbit> kolnama;

    @FXML
    private TableColumn<String, MoPenerbit> kolalamat;

    @FXML
    private TableColumn<String, MoPenerbit> kolnotelp;

    @FXML
    private TableColumn<String, MoPenerbit> kolemail;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = new Koneksi();
        refresh();
        // TODO
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
    private void simpan() {
        konek();

        if (idpenerbit.getText().isEmpty()) {
            TrayNotification tn = new TrayNotification("WANING", "ID Penerbit Tidak Boleh Kosong", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));
        } else if (nmpenerbit.getText().isEmpty()) {
            TrayNotification tn = new TrayNotification("WANING", "Nama Penerbit Tidak Boleh Kosong", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));

        } else if (notelp.getText().isEmpty()) {
            TrayNotification tn = new TrayNotification("WANING", "No Telepon Tidak Boleh Kosong", NotificationType.WARNING);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));

        } else {

            try {
                Statement stt = conn.createStatement();
                String sql = "select * from penerbit where idpenerbit = '" + idpenerbit.getText() + "'";
                rs = stt.executeQuery(sql);
                if (rs.next() == true) {
                    TrayNotification tn = new TrayNotification("WARNING", "ID Penerbit Sudah Terdaftar", NotificationType.NOTICE);
                    tn.setAnimationType(AnimationType.SLIDE);
                    tn.showAndDismiss(Duration.seconds(1));
                } else {
                    konek();
                    String insert = "insert into penerbit(idpenerbit,nama,alamat,nohp,email) values (?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(insert);
                        pst.setString(1, idpenerbit.getText());
                        pst.setString(2, nmpenerbit.getText());
                        pst.setString(3, alamat.getText());
                        pst.setString(4, notelp.getText());
                        pst.setString(5, email.getText());
                        int berhasil = pst.executeUpdate();
                        if (berhasil == 1) {
                            hapus();
                            refresh();
                            TrayNotification tn = new TrayNotification("SUCCESS", "Simpan Data Berhasil", NotificationType.SUCCESS);
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
    @FXML
    private void deleteKlik(ActionEvent event){
        delete();
    }
     @FXML
    private void refreshKlik(ActionEvent event){
        hapus();
        refresh();
    }

    private void update() {

        konek();
        String insert = "update penerbit set nama=?,alamat=?,nohp=?,email=? where idpenerbit=?";
        try {
            pst = conn.prepareStatement(insert);
            pst.setString(5, idpenerbit.getText());
            pst.setString(1, nmpenerbit.getText());
            pst.setString(2, alamat.getText());
            pst.setString(3, notelp.getText());
            pst.setString(4, email.getText());
            int berhasil = pst.executeUpdate();
            if (berhasil == 1) {
                hapus();
                refresh();
                TrayNotification tn = new TrayNotification("SUCCESS", "Data Berhasil Terupdate", NotificationType.SUCCESS);
                tn.setAnimationType(AnimationType.FADE);
                tn.showAndDismiss(Duration.seconds(2));
            } else {
                TrayNotification tn = new TrayNotification("ERROR", "Update Data Gagal", NotificationType.ERROR);
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
        update();
    }

    private void delete() {
        konek();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("HAPUS");
        alert.setHeaderText("ID Penerbit : " + idpenerbit.getText() + "\nNama Penerbit : " + nmpenerbit.getText());
        alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
        String sql = "delete from penerbit where idpenerbit = ? ";
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, idpenerbit.getText());
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

    @FXML
    private void setdariTabel(MouseEvent event) {
        setTable();

    }

    private void setTable() {
        try {
            MoPenerbit pr = tabdata.getItems().get(tabdata.getSelectionModel().getSelectedIndex());
            idpenerbit.setText(pr.getId());
            nmpenerbit.setText(pr.getNama());
            alamat.setText(pr.getAlamat());
            notelp.setText(pr.getNotelp());
            email.setText(pr.getEmail());
        } catch (Exception ex) {

        }
    }

    @FXML
    public void cariper(KeyEvent event) throws SQLException {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from penerbit where idpenerbit LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from penerbit where nama LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from penerbit where alamat LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new MoPenerbit(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

                }
                tabdata.setItems(data);
            } catch (SQLException ex) {
            }
        }

    }

    private void hapus() {
        idpenerbit.setText("");
        nmpenerbit.setText("");
        alamat.setText("");
        notelp.setText("");
        email.setText("");
    }

    private void refresh() {
        konek();
        tabdata.getItems().clear();
        query = "select * from penerbit";
        tabelper();
    }

    private void tabelper() {

        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String id = set.getString(1);
                String nama = set.getString(2);
                String alamat = set.getString(3);
                String notelp = set.getString(4);
                String email = set.getString(5);
                data.add(new MoPenerbit(id, nama, alamat, notelp, email));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolalamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        kolnotelp.setCellValueFactory(new PropertyValueFactory<>("notelp"));
        kolemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabdata.setItems(null);
        tabdata.setItems(data);
    }

}
