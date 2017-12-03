/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.parentchildedge;

import anchor.AnchorModel;
import anchor.AnchorNode;
import boxes.BoxNode;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class ParentChildEdgeController {
    private AnchorPane interactivePane;
    private BoxNode boxnode;
    private ParentChildEdgeModel model;
    private ParentChildEdgeView node;
    
    private AnchorModel childAnchorModel;
    private AnchorNode childAnchor;
    
    
    final Delta dragDelta=new Delta();
      /**
     * 
     * fields used for constraining the cubic curve
     * We do this so that the Dots position lies on the midpoint of the curves start and end x,y coords
     * The alternative is to find the curves points of inflection. and bind the Dots position to it.
     * https://pomax.github.com/bezierinfo/
    */ 
    private final DoubleProperty mControloffX=new SimpleDoubleProperty();
    private final DoubleProperty mControloffY=new SimpleDoubleProperty();
    private final DoubleProperty mControlDirX1=new SimpleDoubleProperty();
    private final DoubleProperty mControlDirY1=new SimpleDoubleProperty();
    private final DoubleProperty mControlDirX2=new SimpleDoubleProperty();
    private final DoubleProperty mControlDirY2=new SimpleDoubleProperty();
    
     @FXML
    private CubicCurve curve;

    

    void setModel(ParentChildEdgeModel item) {
        model=item;
        childAnchorModel=model.getChildAnchorModel();
       
    }

    void setView(ParentChildEdgeView nd, BoxNode parentBoxNode, AnchorPane interactivePane) {
        node=nd;
        this.boxnode=parentBoxNode;
        this.interactivePane=interactivePane;
        
        childAnchor=new AnchorNode(childAnchorModel, this.interactivePane);
        
        curve=createStartingCurve();
        
        curve.startXProperty().bind(this.boxnode.layoutXProperty());
        curve.startYProperty().bind(this.boxnode.layoutYProperty());
        curve.endXProperty().bind(childAnchor.centerXProperty());
        curve.endYProperty().bind(childAnchor.centerYProperty());
        curve.setMouseTransparent(true);
        constraintCurve();
        overrideAnchorBehaviour();
        
        
        
        node.getChildren().add(0,curve);
        node.getChildren().add(0,childAnchor);
        this.interactivePane.getChildren().add(node);
        
    }
    
    
    private CubicCurve createStartingCurve() {
        //CubicCurve curve = new CubicCurve();
        curve.setStartX(50);
        curve.setStartY(200);
        curve.setControlX1(150);
        curve.setControlY1(300);
        curve.setControlX2(250);
        curve.setControlY2(50);
        curve.setEndX(350);
        curve.setEndY(150);
        curve.setStroke(Color.FORESTGREEN);
        curve.setStrokeWidth(4);
        curve.setStrokeLineCap(StrokeLineCap.ROUND);
        curve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return curve;
    }

    public ParentChildEdgeModel getModel() {
        return this.model;
    }
    
    
    /**
     * We do this so that the Dots position lies on the midpoint of the curves start and end x,y coords
     * The alternative is to find the curves points of inflection. and bind the Dots position to it.
     * https://pomax.github.com/bezierinfo/
     */
    private void constraintCurve() {
        /**Curve Constraint Begin
         */
        
        mControloffX.set(100.0);
        mControloffY.set(50.0);
        
        
        mControlDirX1.bind(new When(
        curve.startXProperty().greaterThan(curve.endXProperty()))
        .then(-1.0).otherwise(1.0)
        );
        
        
        mControlDirX2.bind(new When(
        curve.startXProperty().greaterThan(curve.endXProperty()))
        .then(1.0).otherwise(-1.0)
        );
        
        curve.controlX1Property().bind(
                Bindings.add(
                        curve.startXProperty(), 
                        mControloffX.multiply(mControlDirX1)
                        )
        );
        
        curve.controlX2Property().bind(
                Bindings.add(
                        curve.endXProperty(), 
                        mControloffX.multiply(mControlDirX2)
                        )
        );
        
        curve.controlY1Property().bind(
                Bindings.add(
                        curve.startYProperty(), 
                        mControloffY.multiply(mControlDirY1)
                        )
        );
        
        curve.controlY2Property().bind(
                Bindings.add(
                        curve.endYProperty(), 
                        mControloffY.multiply(mControlDirY2)
                        )
        );
        
        curve.toBack();
        /**Curve Constraint End
         */
        
    }

    /**
     * Override the behaviour of the childanchor. 
     * The node needs to go to the top of the parent pane.(the AnchorPane interactivePane).
     * This is inorder to address the z-ordering of the boxes and the anchors.
     * The anchors will always need to be "behind" the boxes for them to be dropped on the boxes.
     * 
     */
    private void overrideAnchorBehaviour() {
        
        childAnchor.setOnMouseDragged(e->{
           
            node.toBack();              ///overriden statement
           // System.out.println("anchor.ParentChildEdgeController.setView() Mouse Dragged");
            double newX=e.getX()+dragDelta.x;
            if(newX>0 && newX<ParentChildEdgeController.this.interactivePane.getScene().getWidth()){
                childAnchor.setCenterX(newX);
            }
        double newY=e.getY()+dragDelta.y;
            if(newY>0 && newY<ParentChildEdgeController.this.interactivePane.getScene().getHeight()){
                childAnchor.setCenterY(newY);
            }
            
           e.consume();
        
        });
        
        
        childAnchor.setOnMouseReleased(e->{
           if(!node.getDropReceived()){
               node.toFront();
               node.requestFocus();
           }
           
        });
        
    }

    public CubicCurve getCurve() {
        return this.curve;
    }
    
      private class Delta{
        double x,y;
    }
}
