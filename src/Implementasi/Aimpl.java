/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementasi;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author HellCat
 */
public class Aimpl {

    public double initX;
    public double initY;

    public void drag(AnchorPane root, MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX() - initX);
        stage.setY(event.getScreenY() - initY);
    }

    public void pres(AnchorPane root, MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        initX = event.getScreenX() - stage.getX();
        initY = event.getScreenY() - stage.getY();
    }

    public void close(AnchorPane root, MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }

    public void minimize(AnchorPane root, MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    public void tutup(AnchorPane root, MouseEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("KELUAR");
            alert.setHeaderText(null);
            alert.setContentText("Yakin Ingin Keluar ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
                System.exit(0);
            } else {

            }
        } catch (Exception e) {

        }
    }
}
