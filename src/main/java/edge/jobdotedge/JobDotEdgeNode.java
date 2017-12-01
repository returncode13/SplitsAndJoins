/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.jobdotedge;

import anchor.AnchorNode;
import boxes.BoxNode;
import dot.DotNode;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class JobDotEdgeNode extends AnchorPane{
   private  JobDotEdgeModel model;
    private JobDotEdgeController  controller;
    private BoxNode boxnode;
    private AnchorPane interactivePane;
     private FXMLLoader fXMLLoader;
    private final URL location;
    
    
    public JobDotEdgeNode(JobDotEdgeModel item,BoxNode node, AnchorPane interactivePane){
        this.location=getClass().getClassLoader().getResource("fxml/jobdotedge.fxml"); 
       this.boxnode=node;
       this.interactivePane=interactivePane;
          
           fXMLLoader=new FXMLLoader();
              
            fXMLLoader.setLocation(location);
             
           // fXMLLoader.setRoot(this);
            fXMLLoader.setBuilderFactory(new JavaFXBuilderFactory());
           
            try{
                fXMLLoader.load(location.openStream());
           
                controller=(JobDotEdgeController)fXMLLoader.getController();
             
               // setId(UUID.randomUUID().toString());
                //setId((new UID()).toString());
                
                
                controller.setModel(item);
                controller.setView(this,boxnode,this.interactivePane);
               
                
            }catch(IOException e){
                throw new RuntimeException(e);
            }
    }
    
    
    /*/*
    for drag purposes of the node
    */
    /*
    public void relocateToPoint(Point2D xx){
        Point2D coords=getParent().sceneToLocal(xx);
        
        relocate(
                (int)(coords.getX() -(getBoundsInLocal().getWidth()/2)),
                (int)(coords.getY() -(getBoundsInLocal().getHeight()/2))
        );
        
    }*/
}
