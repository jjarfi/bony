/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Function.Fungsi;

import Implementasi.Aimpl;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.TRANSPARENT;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HellCat
 */
public class A implements Initializable {

    //Kelas A controller merupakan kelas dari formnya A atau form Login
    Fungsi funsi = new Fungsi();
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane root;
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
    private JFXCheckBox ckpenerbit;
    
    @FXML
    private JFXCheckBox sp;
    @FXML
    private Label lblpass;

    @FXML
    private JFXCheckBox ckpengarang;

    @FXML
    private JFXCheckBox ckbuku;

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
        if (username.getText().equals("admin") && password.getText().equals("perpus123")) {
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
        } else {
            TrayNotification tn = new TrayNotification("PERINGATAN", "Periksa Kembali Username & Password Anda !", NotificationType.NOTICE);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndDismiss(Duration.seconds(1));
        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }

    @FXML
    private void showPass(ActionEvent event) {
        if(sp.isSelected()==true){
            lblpass.setText(password.getText());
        }else{
            lblpass.setText("");
        }
    }

}
