/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;

import dot.DotModel;
import dot.DotNode;
import edge.jobdotedge.JobDotEdgeModel;
import edge.jobdotedge.JobDotEdgeNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class BoxController {

    
    BoxModel model;
    BoxNode node;
    AnchorPane interactivePane;
    
     @FXML
    private AnchorPane baseBox;
    
    
    @FXML
    private AnchorPane box;
    
    private final ContextMenu menu=new ContextMenu();
    
    void setModel(BoxModel item) {
        model=item;
    }

    void setView(BoxNode aThis,AnchorPane interactivePane) {
        this.interactivePane=interactivePane;
        node=aThis;
        
        /**
         * Required to execute Drag on boxes
         */
        node.setOnDragOver(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent event) {
                node.relocateToPoint(new Point2D(event.getSceneX(),event.getSceneY()));
                event.acceptTransferModes(TransferMode.LINK);
                event.consume();
            }
            
        });
        
        node.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                  Dragboard dragboard=node.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content=new ClipboardContent();
          
            content.putString(node.getId());
            dragboard.setContent(content);
            event.consume();
            }
            
        });
        
        
        /**
         * Setup linking
         */
        MenuItem addAChildJob=new MenuItem("+start a link");
        MenuItem deleteThisJob=new MenuItem("-delete this job node");
        node.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>(){
            @Override
            public void handle(ContextMenuEvent event) {
                menu.show(node, event.getScreenX(), event.getScreenY());
            }
            
        });
        addAChildJob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(".handle(): Add a jobdotEdge here");
                JobDotEdgeModel jbm=new JobDotEdgeModel();
                jbm.setParentBox(model);
                JobDotEdgeNode jbn=new JobDotEdgeNode(jbm,node,BoxController.this.interactivePane);
                BoxController.this.interactivePane.getChildren().add(jbn);
                
                
                
            }
        });
        
        menu.getItems().addAll(addAChildJob,deleteThisJob);
    }
    
}
