/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.jobdotedge;

import anchor.AnchorModel;
import anchor.AnchorNode;
import boxes.BoxNode;
import dot.DotModel;
import dot.DotView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class JobDotEdgeController{
    
    private AnchorPane interactivePane;
    private BoxNode boxnode;
    private JobDotEdgeModel model;
    private JobDotEdgeNode node;
    private DotModel dotmodel;
    private DotView dotnode;
    private AnchorModel anchorModel;
    private AnchorNode anchor;
    
   // private CubicCurve curve;
     @FXML
    private CubicCurve curve;
   
    void setModel(JobDotEdgeModel item) {
        model=item;
        dotmodel=model.getDotModel();
        anchorModel=model.getAnchorModel();
        
        
    }

    void setView(JobDotEdgeNode nd,BoxNode boxnode,AnchorPane interactivePane) {
        
        node=nd;
        this.boxnode=boxnode;
        this.interactivePane=interactivePane;
        
        /*anchorModel.getX().bind(this.boxnode.layoutXProperty());                    //bind it to the parent box
        anchorModel.getY().bind(this.boxnode.layoutYProperty());*/
        
        anchor=new AnchorNode(anchorModel,this.interactivePane);
        dotnode=new DotView(dotmodel,this.interactivePane);
        
        
        
        
        curve=createStartingCurve();
       
        anchor.centerXProperty().bind(this.boxnode.layoutXProperty());
        anchor.centerYProperty().bind(this.boxnode.layoutYProperty());
        curve.startXProperty().bind(this.boxnode.layoutXProperty());
        curve.startYProperty().bind(this.boxnode.layoutYProperty());
        /* curve.startXProperty().bind(anchorModel.getX());
        curve.startYProperty().bind(anchorModel.getY());*/
        curve.endXProperty().bind(dotmodel.getX());
        curve.endYProperty().bind(dotmodel.getY());
        curve.setMouseTransparent(true);
        
        /*anchorModel.getX().bind(curve.startXProperty());
        anchorModel.getY().bind(curve.startYProperty());
        dotmodel.getX().bind(curve.endXProperty());
        dotmodel.getY().bind(curve.endYProperty());*/
        
        
        node.getChildren().add(curve);
        node.getChildren().add(anchor);
        
        node.getChildren().add(dotnode);
       
        
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
       // curve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return curve;
    }
    
    
}
