/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extmanipulator;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
     static int conform=0;
    public static int showalertbox(){
        Label label1=new Label("  Compatibility issues may arise if you change to incompatible extension to a file.  ");
        Label label2=new Label("  Are you realy want to change the extension of the files.                                      ");
        
        Button ok=new Button("ok");
        Button cancel=new Button("cancel");
        
        Stage stage=new Stage();
        
       
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Conformation");
        stage.setMaxWidth(570);
        stage.setMinHeight(200);
        
        
        
        cancel.setOnAction(e -> {conform=0;stage.close();});
        
        ok.setOnAction(e -> {conform = 1;stage.close();});
        
        stage.setOnCloseRequest(e ->{conform=0;stage.close();});
        
        FlowPane flowpane=new FlowPane(Orientation.HORIZONTAL, 10, 10);
        flowpane.getChildren().addAll(label1,label2,ok,cancel);
        flowpane.setAlignment(Pos.CENTER);
        
        Scene scene =new Scene(flowpane);
        stage.setScene(scene);
        stage.showAndWait();
        return conform;
    }
    
  
    
}
