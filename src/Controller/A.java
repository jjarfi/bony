/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Function.Fungsi;
import Function.PFfungsi;
import Function.TFfungsi;
import Implementasi.Aimpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.TRANSPARENT;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class A implements Initializable {
    
    //Kelas A controller merupakan kelas dari formnya A atau form Login
    

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane root;
    TFfungsi TFf = new TFfungsi();
    PFfungsi TPf = new PFfungsi();
    Aimpl a = new Aimpl();
    @FXML
    public void drag(MouseEvent event) {
        a.drag(root, event);
    }
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private Button btnhapuspass;

    @FXML
    private Button btnhapususer;

    @FXML
    public void press(MouseEvent event) {
        a.pres(root, event);
    }

    @FXML
    public void close(MouseEvent event) {
        a.close(root, event);
    }

    @FXML
    public void minimize(MouseEvent event) {
        a.minimize(root, event);
    }

    @FXML
    public void kliklogin(ActionEvent event) {
        //event login untuk login ke sistem 
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent rocot = FXMLLoader.load(getClass().getResource(Fungsi.APLIKASI));
            Stage stage = new Stage();
            Scene scene = new Scene(rocot);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(TRANSPARENT);
            stage.getIcons().add(new Image(Fungsi.ICON));
            stage.setTitle(Fungsi.APPNAME);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        TFf.clearTextFieldByButton(username, btnhapususer);
//        TPf.clearPassFieldByButton(password, btnhapuspass);
    }

}
