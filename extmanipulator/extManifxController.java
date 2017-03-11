/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extmanipulator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Syed Sultan
 */

public class extManifxController implements Initializable {
    
    @FXML
    
    RadioButton radioButton1,radioButton2,radioButton3;
    
    public Label label= new Label();
   
    public TextField textField1 = new TextField();
   
    public TextField radioTextField1 = new TextField();
    public TextField radioTextField2 = new TextField();
    public TextField radioTextField3 = new TextField();
   
    final ToggleGroup group = new ToggleGroup();
    
    public static Path directoryPath  ;
   
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    
    File file1=new File(System.getProperty("user.home")),file2,directoryFile;
    
    static Boolean isDirectorySelected=false;
    
    Stage stage;
    
    Tooltip tooltiptext=new Tooltip("folder is ready");
    
    int conform=0;
    
    @FXML
    public void butaction(){
        directoryFile=directoryChooser.showDialog(stage);
        directoryPath=directoryFile.toPath();
        isDirectorySelected=true;
         try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
             for (Path filePath: stream) {
                 System.out.println(filePath.getFileName());
             }
             
         } catch (IOException | DirectoryIteratorException x) {
             // IOException can never be thrown by the iteration.
             // In this snippet, it can only be thrown by newDirectoryStream.
             System.err.println(x);
         }
         
         directoryChooser.setInitialDirectory(file1);
         textField1.setText(directoryPath.toString());
     
    }
    
    
    
    public void proceedAction(){
        
        
        if(isDirectorySelected){

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {

               if(radioButton1.isSelected()){

                   if(radioTextField1.getText().equals("")||radioTextField1.getText().matches("[^/.]*"))
                   label.setText("EXTENSION IS NOT VALID");

                   else {
                        conform=AlertBox.showalertbox();

                        if(conform==1){   

                            String sname;
                            String fn=radioTextField1.getText();
                            for (Path filePath: stream){

                                file2=filePath.toFile();
                                sname=file2.getAbsolutePath();
                                if(!sname.endsWith(fn)){

                                    file2=new File(sname+fn);
                                    filePath.toFile().renameTo(file2);

                                }
                            }
                        System.out.print("added");
                        label.setText("EXTENSION ADDED");
                        label.setTooltip(tooltiptext );
                       }
                   }
                }else if(radioButton2.isSelected()){
                    
                    if(radioTextField2.getText().equals("")||radioTextField2.getText().matches("[^/.]*")||radioTextField3.getText().equals("")||radioTextField3.getText().matches("[^/.]*"))
                        label.setText("EXTENSION IS NOT VALID");
                    else{
                         conform=AlertBox.showalertbox();

                         if(conform==1){   
                            
                            String  tft1=radioTextField2.getText();
                            String  tft2=radioTextField3.getText();
                            String fn2[],fname,sname;
                            for (Path file: stream){
                                 
                                 file2=file.toFile();
                                 sname=file2.getAbsolutePath();
                                 if(sname.endsWith(tft1)){
                                    
                                    fn2=sname.split("\\.(?=[^\\.]+$)");
                                    System.out.println(fn2[0]+"   \t  "+fn2[1]);
                                    fname=fn2[0].concat(tft2);
                                    System.out.println(fname);
                                    file2=new File(fname);
                                    file.toFile().renameTo(file2);
                               
                                 }

                            }
                            label.setText("EXTENSION CHANGED");
                            System.out.print("changed");           
                            label.setTooltip(tooltiptext);
                         }

                    }
                }else if(radioButton3.isSelected()){

                    conform=AlertBox.showalertbox();
                    if(conform==1){   

                            String fn2[],fname,sname;

                            for (Path file: stream){ 

                                file2=file.toFile();
                                sname=file2.getAbsolutePath();

                                 if(!sname.endsWith(".*")){

                                     fn2=sname.split("\\.(?=[^\\.]+$)");
                                     System.out.println(fn2[0]+"     "+fn2[1]);
                                     System.out.println(fn2[0]);
                                     file2=new File(fn2[0]);
                                     file.toFile().renameTo(file2);
                                 }
                                  System.out.println("removed");
                            }
                            label.setText("EXTENSION REMOVED");
                            label.setTooltip(tooltiptext);
                    }

                }else
                    label.setText("PLEASE SELECT AN OPTION");

            }catch (IOException | DirectoryIteratorException x) {
                // IOException can never be thrown by the iteration.
                // In this snippet, it can only be thrown by newDirectoryStream.
                    System.err.println(x);
            }   

        }else
            label.setText("PLEASE SELECT A DIRECTORY");

    }
    
   public void closeApp(){
   
       System.exit(0);
   
   }
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);
        tooltiptext.setId("tooltiptext1");
        label.setId("labeltext");
        
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
           @Override
            public void changed(ObservableValue<? extends Toggle>ov,Toggle t,Toggle t1){

            RadioButton rck=(RadioButton)t1.getToggleGroup().getSelectedToggle();
             if(rck.equals(radioButton1)){

                   radioButton1.getStyleClass().add("radiobutton");
                   radioTextField1.setDisable(false);
                   radioTextField2.setDisable(true);
                   radioTextField3.setDisable(true);

             }else if(rck.equals(radioButton2)){

                   radioButton2.getStyleClass().add("radiobutton");
                   radioTextField1.setDisable(true);
                   radioTextField2.setDisable(false);
                   radioTextField3.setDisable(false);

             }else{

                   radioButton3.getStyleClass().add("radiobutton");
                   radioTextField1.setDisable(true);
                   radioTextField2.setDisable(true);
                   radioTextField3.setDisable(true);

             }
           }



        });
   
    
    }    
   
}
