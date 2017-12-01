/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dot;

import anchor.AnchorModel;
import basewindow.BaseWindowModel;
import boxes.BoxModel;
import edge.jobdotedge.JobDotEdgeModel;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotModel {
    
    Color color;
    DoubleProperty x=new SimpleDoubleProperty();
    DoubleProperty y=new SimpleDoubleProperty();
    Set<JobDotEdgeModel> jobDotEdges=new HashSet<>();
    Set<BoxModel> parentBoxes=new HashSet<>();
    Set<BoxModel> childBoxes=new HashSet<>();
    

    
    public DotModel(){
        x.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              //  System.out.println("DotModel.changed() x: "+newValue);
            }
        });
        
        y.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
             //   System.out.println("DotModel.changed() y: "+newValue);
            }
        });
    }
    
    
    
    
    
    public DoubleProperty getX() {
        System.out.println("dot.DotModel.getX(): "+x.get());
        
        return x;
    }

    public void setX(DoubleProperty x) {
       
        this.x = x;
    }

    public DoubleProperty getY() {
         System.out.println("dot.DotModel.setY(): "+y.get());
        return y;
    }

    public void setY(DoubleProperty y) {
       
        this.y = y;
    }

    public Set<BoxModel> getParentBoxes() {
        return parentBoxes;
    }

    public void addToParentBoxesSet(BoxModel box) {
        this.parentBoxes.add(box);
    }

    public Set<BoxModel> getChildBoxes() {
        return childBoxes;
    }

    public void addToChildBoxesSet(BoxModel box) {
        this.childBoxes.add(box);
    }

    public Set<JobDotEdgeModel> getJobDotEdges() {
        return jobDotEdges;
    }

    public void addJobDotEdgeToSet(JobDotEdgeModel jobDotEdges) {
        this.jobDotEdges.add(jobDotEdges);
    }

   
    
    
    
    
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
}
