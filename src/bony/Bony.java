/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bony;

import Function.Fungsi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.TRANSPARENT;

/**
 *
 * @author HellCat
 */
public class Bony extends Application {

    Fungsi f = new Fungsi();

//    public Bony() {
//
//        DBModel model = new DBModel();
//        model.createDataBase();
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Fungsi.LOGIN));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(TRANSPARENT);
        stage.getIcons().add(new Image(Fungsi.ICON));
        stage.setTitle(Fungsi.APPNAME);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
