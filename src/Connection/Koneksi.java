/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author HellCat
 */
public class Koneksi {

    //Kelas Koneksi untuk membuat koneksi ke mysql
    public java.sql.Connection konekDB() {
        try {
            String host = "localhost"; //alamat atau host
            String username = "root"; //username mysql
            String password = "%admin%"; //password user mysql
            String database = "perpus"; //nama database
            String port = "3306"; //default port di mysql

            Class.forName("com.mysql.jdbc.Driver"); //driver koneksi dengan jdbc (java database connection

            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Koneksi Error" + ex);
            alert.setContentText("Pastikan MySQL anda sudah running ..");

        }
        return null;

    }

}
