/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.or5e.plugin.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author JDPS
 */
public class ApplicationMainController extends Application  {
   public static void main(String[] args) {
        Application.launch(ApplicationMainController.class, args);
    }
    @FXML private Text actiontarget;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }    
    @FXML protected void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ApplicationMain.fxml"));
        
        stage.setTitle("Plugin Core Module");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(Boolean.FALSE);
        stage.setAlwaysOnTop(Boolean.TRUE);
        stage.setScene(new Scene(root, 1280, 800));
        stage.show();
    }
}
