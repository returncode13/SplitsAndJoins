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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.css.SimpleStyleableStringProperty;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class DotModel {
    public static final String NJS="N";
    public static final String JOIN="J";
    public static final String SPLIT="S";
    
    Color color;
    DoubleProperty x=new SimpleDoubleProperty();
    DoubleProperty y=new SimpleDoubleProperty();
    
    Set<BoxModel> parentList;
    Set<BoxModel> childList;
    
    ObservableSet<BoxModel> parents;
    ObservableSet<BoxModel> children;
    
    boolean split=false;       //1 Parent --> m Children
    boolean join=false;        //m Parents --> 1 Child
    boolean njs=false;         //1 Parent --> 1 Child
    boolean enableFurtherLinks=true;  
    
    StringProperty status;     //display the status of the Dot on it
    
    
    public DotModel(){
        status=new SimpleStringProperty();
        parentList=new HashSet<>();
        childList=new HashSet<>();
        
        parents=FXCollections.observableSet(parentList);
        children=FXCollections.observableSet(childList);
        
        parents.addListener(new SetChangeListener<BoxModel>(){
            @Override
            public void onChanged(SetChangeListener.Change<? extends BoxModel> change) {
                updateConnectionStatus("Parent");
            }
            
        });
        
        children.addListener(new SetChangeListener<BoxModel>(){
            @Override
            public void onChanged(SetChangeListener.Change<? extends BoxModel> change) {
                updateConnectionStatus("Children");
                
            }
            
        });
        
        
    }   
    
    
    public DoubleProperty getX() {
      return x;
    }

    public void setX(DoubleProperty x) {
       this.x = x;
    }

    public DoubleProperty getY() {
        return y;
    }

    public void setY(DoubleProperty y) {
       this.y = y;
    }

    public StringProperty getStatus() {
        return status;
    }

    public void setStatus(StringProperty status) {
        this.status = status;
    }
    
    
    

    public void addToParents(BoxModel box) {
        if(!this.parents.contains(box)){
            this.parents.add(box);
        }
    }


    public void addToChildren(BoxModel box) {
        if(!this.children.contains(box)){
            this.children.add(box);
        }
    }

    public ObservableSet<BoxModel> getParents() {
        return parents;
    }

    public ObservableSet<BoxModel> getChildren() {
        return children;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isJoin() {
        return join;
    }

    public boolean isNjs() {
        return njs;
    }

    public boolean enableFurtherLinks() {
        return enableFurtherLinks;
    }
    
     private void updateConnectionStatus(String from) {
                 if((parents.size()==1) && (children.size()==1)){
                      System.out.println("dot.DotModel.updateConnectionStatus():  from "+from+" setting njs=true ParentSize: "+parents.size()+" ChildrenSize: "+children.size());
                        njs=true;
                        split=false;
                        join=false;
                        enableFurtherLinks=true;
                        status.set(this.NJS);
                    }
                    else if(parents.size()==1 && children.size()>1){
                        System.out.println("dot.DotModel.updateConnectionStatus():  from "+from+" setting split=true ParentSize: "+parents.size()+" ChildrenSize: "+children.size());
                        njs=false;
                        split=true;
                        join=false;
                        enableFurtherLinks=true;
                        status.set(this.SPLIT);
                    }
                    else if(parents.size()>1&& children.size()==1){
                         System.out.println("dot.DotModel.updateConnectionStatus():  from "+from+" setting join=true ParentSize: "+parents.size()+" ChildrenSize: "+children.size());
                        njs=false;
                        split=false;
                        join=true;
                        enableFurtherLinks=true;
                        status.set(this.JOIN);
                        
                    }else{
                         System.out.println("dot.DotModel.updateConnectionStatus(): from "+from+" Disabling further links ParentSize: "+parents.size()+" ChildrenSize: "+children.size());
                        enableFurtherLinks=false;
                    }
            }
    
}
