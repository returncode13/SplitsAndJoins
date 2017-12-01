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
    
    BoxModel endBox;
    DotModel dotModel;
    AnchorModel anchorModel;

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

    public BoxModel getEndBox() {
        return endBox;
    }

    public void setEndBox(BoxModel endBox) {
        this.endBox = endBox;
    }
    
    
    

   
    
}
