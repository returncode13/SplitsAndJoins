/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.dotjobedge;

import anchor.AnchorModel;
import boxes.BoxModel;
import dot.DotModel;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotJobEdgeModel {
    
   
    DotModel dotModel;      //the dotmodel from which the link originates
    BoxModel childBox;        //the child box on which the anchor will be dropped
    AnchorModel anchorModel;    //the anchor to be dropped

    public DotModel getDotModel() {
        return dotModel;
    }

    public void setDotModel(DotModel dotModel) {
        this.dotModel = dotModel;
    }

    public AnchorModel getAnchorModel() {
        if(anchorModel==null){
            anchorModel=new AnchorModel();
        }
        return anchorModel;
    }

    public void setAnchorModel(AnchorModel anchorModel) {
        this.anchorModel = anchorModel;
    }

    public BoxModel getChildBox() {
        return childBox;
    }

    public void setChildBox(BoxModel childBox) {
        this.childBox = childBox;
        this.dotModel.addToChildren(this.childBox);
    }
    
    
    

   
    
}
