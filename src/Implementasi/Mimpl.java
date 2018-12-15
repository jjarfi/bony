/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementasi;

import Function.Fungsi;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author HellCat
 */
public class Mimpl {

    Fungsi f = new Fungsi();
    AnchorPane dash;
    AnchorPane pane;



    public void setNode(Node node) {
        pane.getChildren().setAll(dash);
        pane.getChildren().add(node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public void pindahdashboard(ActionEvent event) {
        setNode(dash);
    }
}
