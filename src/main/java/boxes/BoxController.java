/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;

import anchor.AnchorModel;
import anchor.AnchorNode;
import dot.DotModel;
import dot.DotView;
import edge.dotjobedge.DotJobEdgeModel;
import edge.dotjobedge.DotJobEdgeView;
import edge.jobdotedge.JobDotEdgeModel;
import edge.jobdotedge.JobDotEdgeNode;
import edge.parentchildedge.ParentChildEdgeModel;
import edge.parentchildedge.ParentChildEdgeView;
import java.util.List;
import java.util.Set;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class BoxController {

    
    BoxModel model;
    BoxNode node;
    AnchorPane interactivePane;
    final Delta dragDelta=new Delta();
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
         
        node.setOnMouseDragged(event->{
            node.relocateToPoint(new Point2D(event.getSceneX(),event.getSceneY()));
          //  event.consume();
        });
        
        /**
         * Handle the drop of the anchor
         */
        
        
        
        
        
        
       node.setOnDragEntered(e->{
      //  System.out.println("boxes.BoxController.setView(): DragEntered");
        node.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        e.consume();
        });
     
       node.setOnMouseDragReleased(e->{
            //System.out.println("boxes.BoxController.setView() Mouse Drag  Released on: "+model.getId());e.consume();
                AnchorNode droppedAnchor=(AnchorNode) e.getGestureSource();
                
                
                if(droppedAnchor.getParent() instanceof ParentChildEdgeView){
                    ParentChildEdgeView parentNode=((ParentChildEdgeView)droppedAnchor.getParent());
                    ParentChildEdgeModel parentModel=parentNode.getController().getModel();
                    

                    

                    BoxModel parent=parentModel.getParentBoxModel();
                    /**
                     * add the link to the box only if this box is not an existing child to the parent
                     */
                    if(parent.getChildren().contains(model)){
                        System.out.println("boxes.BoxController.setView(): "+model.getId()%100+" already exists as a child to parent "+parent.getId()%100);
                         parentNode.setDropReceived(false);
                         return;
                    }
                    if(parent.equals(model))
                    {
                        System.out.println("boxes.BoxController.setView(): cyclic");
                        return;
                    }
                    
                    model.addParent(parent);
                    parent.addChild(model);
                    
                    DotModel dotmodel=parentModel.getDotModel();
                    DotView dotnode=new DotView(dotmodel, BoxController.this.interactivePane);
                    parentNode.getChildren().add(dotnode);
                    

                    CubicCurve curve=parentNode.getController().getCurve();  //the curve in the node
                    dotnode.centerXProperty().bind(Bindings.divide((Bindings.add(curve.startXProperty(), curve.endXProperty())),2.0));
                    dotnode.centerYProperty().bind(Bindings.divide((Bindings.add(curve.startYProperty(), curve.endYProperty())),2.0));

                    
                    parentModel.setChildBoxModel(model);
                    parentNode.setDropReceived(true);
                    droppedAnchor.centerXProperty().bind(node.layoutXProperty());
                    droppedAnchor.centerYProperty().bind(node.layoutYProperty());
                
                }
                
                if(droppedAnchor.getParent() instanceof DotJobEdgeView){
                    DotJobEdgeView parentNode=(DotJobEdgeView) droppedAnchor.getParent();
                    DotJobEdgeModel parentModel=parentNode.getController().getModel();
                    
                    Set<BoxModel> parents=parentModel.getDotModel().getParents();          //since the drop happens from a Dot to a Box , the Box is a child of the Dots parents
                    for(BoxModel parent:parents){
                        
                         if(parent.getChildren().contains(model)){
                            System.out.println("boxes.BoxController.setView(): "+model.getId()%100+" already exists as a child to parent "+parent.getId()%100);
                             parentNode.setDropReceived(false);
                             return;
                        }
                         if(parent.equals(model))
                        {
                            System.out.println("boxes.BoxController.setView(): cyclic");
                            return;
                        }
                        
                        model.addParent(parent);
                        parent.addChild(model);
                    }
                    
                    parentNode.setDropReceived(true);
                    parentModel.setChildBox(model);
                    droppedAnchor.centerXProperty().bind(node.layoutXProperty());
                    droppedAnchor.centerYProperty().bind(node.layoutYProperty());
                }
         //   System.out.println("boxes.BoxController.setView() "+e.getGestureSource().getClass().getCanonicalName());
            e.consume();
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
            //    System.out.println(".handle(): Add a jobdotEdge here");
           
                ParentChildEdgeModel pcdm=new ParentChildEdgeModel();
                pcdm.setParentBoxModel(model);
                ParentChildEdgeView pcdn=new ParentChildEdgeView(pcdm, node, BoxController.this.interactivePane);
                
                
                
                
            }
        });
        
        menu.getItems().addAll(addAChildJob,deleteThisJob);
    }
    private class Delta{
        double x,y;
    }
}
