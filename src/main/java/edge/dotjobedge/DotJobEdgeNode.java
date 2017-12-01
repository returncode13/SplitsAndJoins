/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.dotjobedge;

import boxes.BoxNode;
import dot.DotNode;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotJobEdgeNode extends AnchorPane{
     private  DotJobEdgeModel model;
    private DotJobEdgeController  controller;
    private AnchorPane interactivePane;
    
     private FXMLLoader fXMLLoader;
    private final URL location;
    
    
    public DotJobEdgeNode(DotJobEdgeModel item,DotNode commonDot,AnchorPane interactivePane){
        this.location=getClass().getClassLoader().getResource("fxml/dotjobedge.fxml"); 
       this.interactivePane=interactivePane;
          
           fXMLLoader=new FXMLLoader();
              
            fXMLLoader.setLocation(location);
             
           // fXMLLoader.setRoot(this);
            fXMLLoader.setBuilderFactory(new JavaFXBuilderFactory());
           
            try{
                fXMLLoader.load(location.openStream());
           
                controller=(DotJobEdgeController)fXMLLoader.getController();
             
               // setId(UUID.randomUUID().toString());
                //setId((new UID()).toString());
                
                
                controller.setModel(item);
                controller.setView(this,commonDot,this.interactivePane);
               
                
            }catch(IOException e){
                throw new RuntimeException(e);
            }
    }
    
}
