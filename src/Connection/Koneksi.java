/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HellCat
 */
public class Koneksi {

    public java.sql.Connection konekDB() {
        try {
            String host = "localhost";
            String username = "root";
            String password = "%admin%";
            String database = "bony";
            String port = "3306";

            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
