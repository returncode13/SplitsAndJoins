/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edge.jobdotedge;

import anchor.AnchorModel;
import boxes.BoxModel;
import dot.DotModel;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class JobDotEdgeModel {
    BoxModel parentBox;
    DotModel dotModel;
    AnchorModel anchorModel;
    
   

    
    
    public BoxModel getParentBox() {
        return parentBox;
    }

    public void setParentBox(BoxModel parentBox) {
        this.parentBox = parentBox;
    }
    
    public DotModel getDotModel(){
        if(dotModel==null){
            dotModel=new DotModel();
        }
        return this.dotModel;
    }
    
    public void setDotModel(DotModel dm){
        this.dotModel=dm;
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
    
    
    
    
    
    
    
}

