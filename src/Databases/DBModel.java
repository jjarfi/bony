/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Function.Fungsi;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HellCat
 */
public class DBModel {

    PreparedStatement pst;
    Properties properti = new Properties();
    InputStream iS;
    String db;

    public void loadFileProperti() {
        try {
            iS = new FileInputStream("bony.properties");
            properti.load(iS);
            db = properti.getProperty("db");
        } catch (IOException e) {
            System.out.println("Gagal" + e);
        }
    }

    public void createDataBase() {
        loadFileProperti();
        KoneksiDB con = new KoneksiDB();
        try {
             pst = con.buatDB().prepareStatement("create database if not exists "+db+" DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.buatDB().prepareStatement("CREATE TABLE if not exists "+db+".`admin` (\n"
                    + "  `id` INT NOT NULL,\n"
                    + "  `nama` VARCHAR(45) NULL,\n"
                    + "  `username` VARCHAR(45) NULL,\n"
                    + "  `pass` VARCHAR(45) NULL,\n"
                    + "  `nohp` VARCHAR(45) NULL,\n"
                    + "   PRIMARY KEY (`id`)); ");
            pst.execute();
            pst = con.buatDB().prepareStatement("CREATE TABLE if not exists "+db+".`penerbit` (\n"
                    + "  `idpenerbit` VARCHAR(30) NOT NULL,\n"
                    + "  `nama` VARCHAR(45) NULL,\n"
                    + "  `alamat` VARCHAR(45) NULL,\n"
                    + "  `nohp` TEXT(12) NULL,\n"
                    + "  `email` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idpenerbit`));");
            pst.execute();
            pst = con.buatDB().prepareStatement("CREATE TABLE if not exists "+db+".`pengarang` (\n"
                    + "  `idpengarang` VARCHAR(20) NOT NULL,\n"
                    + "  `nama` VARCHAR(45) NULL,\n"
                    + "  `alamat` VARCHAR(45) NULL,\n"
                    + "  `nohp` TEXT(12) NULL,\n"
                    + "  `email` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idpengarang`));");
            pst.execute();

            pst = con.buatDB().prepareStatement("CREATE TABLE if not exists "+db+".`buku` (\n"
                    + "  `idbuku` VARCHAR(12) NOT NULL,\n"
                    + "  `judul` VARCHAR(45) NULL,\n"
                    + "  `idpenerbit` VARCHAR(30) NOT NULL,\n"
                    + "  `idpengarang` VARCHAR(20) NOT NULL,\n"
                    + "  `tahun` VARCHAR(45) NULL,\n"
                    + "  `jumlah` VARCHAR(45) NULL,\n"
                    + "  `tgl` TEXT(25) NULL,\n"
                    + "  PRIMARY KEY (`idbuku`));");
            pst.execute();
            
            System.out.println("Database berhasil dibuat");


        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(Fungsi.SERVER));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle(Fungsi.APPNAME);
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
