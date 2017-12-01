/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dot;

import dot.formulaField.FormulaFieldModel;
import dot.formulaField.FormulaFieldNode;
import edge.dotjobedge.DotJobEdgeModel;
import edge.dotjobedge.DotJobEdgeNode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotController extends Stage{
    private DotModel dotmodel;
    private DotNode dotnode;
    private AnchorPane interactivePane;
    
    final Delta dragDelta=new Delta();
     private final ContextMenu menu=new ContextMenu();
    
    
    @FXML
    private Circle dot;

   
    
    void setModel(DotModel mod){
        dotmodel=mod;
      
        
    }

    void setView(DotNode nd,AnchorPane interactivePane) {
        this.interactivePane=interactivePane;
        dotnode=nd;
        dotnode.setRadius(10);
        dotnode.setCenterX(10.0);
        dotnode.setCenterY(10.0);
        
        dotmodel.getX().bind(dotnode.centerXProperty());
        dotmodel.getY().bind(dotnode.centerYProperty());
        
         MenuItem addAChildJob=new MenuItem("+link to a job");
        MenuItem deleteThisJob=new MenuItem("-delete this dot");
        dotnode.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>(){
            @Override
            public void handle(ContextMenuEvent event) {
                 menu.show(dotnode, event.getScreenX(), event.getScreenY());
            }
        });
        addAChildJob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DotJobEdgeModel djm=new DotJobEdgeModel();
                djm.setDotModel(dotmodel);
                DotJobEdgeNode djn=new DotJobEdgeNode(djm,dotnode,DotController.this.interactivePane);
                DotController.this.interactivePane.getChildren().add(djn);
                
                //dotnode.getChildren().add(0,djn);
            }
        });
        menu.getItems().addAll(addAChildJob,deleteThisJob);
        
        
        dotnode.setOnMousePressed(e->{
            dragDelta.x=dotnode.getCenterX()-e.getX();
            dragDelta.y=dotnode.getCenterY()-e.getY();
        });
        
        dotnode.setOnMouseDragged(e->{
            double newX=e.getX()+dragDelta.x;
            if(newX>0 && newX<DotController.this.interactivePane.getScene().getWidth()){
                dotnode.setCenterX(newX);
            }
        double newY=e.getY()+dragDelta.y;
            if(newY>0 && newY<DotController.this.interactivePane.getScene().getHeight()){
                dotnode.setCenterY(newY);
            }
        });
        
       
    }

    
    
    
    
    
    private class Delta{
        double x,y;
    }
    
}
