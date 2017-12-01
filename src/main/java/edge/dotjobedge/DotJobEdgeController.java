/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.dotjobedge;

import anchor.AnchorModel;
import anchor.AnchorNode;
import boxes.BoxNode;
import dot.DotModel;
import dot.DotNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotJobEdgeController {
    private BoxNode endnode;
    private DotJobEdgeModel model;
    private DotJobEdgeNode node;
    private DotModel dotmodel;
    private DotNode dotnode;
    private AnchorModel anchorModel;
    private AnchorNode anchor;
     AnchorPane interactivePane;
    @FXML
    private CubicCurve curve;
   
    void setModel(DotJobEdgeModel item) {
        model=item;
        dotmodel=model.getDotModel();
        anchorModel=model.getAnchorModel();
        
        
    }
    
    void setView(DotJobEdgeNode nd,DotNode commonDot,AnchorPane interactivePane) {
        
        node=nd;
        this.interactivePane=interactivePane;
                
     
        
        anchor=new AnchorNode(anchorModel,this.interactivePane);
//        dotnode=new DotNode(dotmodel);
        this.interactivePane=interactivePane;
        dotnode=commonDot;
        
        
        
        
        curve=createStartingCurve();
       
        
        curve.startXProperty().bind(dotnode.centerXProperty());
        curve.startYProperty().bind(dotnode.centerYProperty());
        /* curve.startXProperty().bind(anchorModel.getX());
        curve.startYProperty().bind(anchorModel.getY());*/
        curve.endXProperty().bind(anchorModel.getX());
        curve.endYProperty().bind(anchorModel.getY());
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
        curve.setStroke(Color.RED);
        curve.setStrokeWidth(4);
        curve.setStrokeLineCap(StrokeLineCap.ROUND);
       // curve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return curve;
    }
    
    
}
