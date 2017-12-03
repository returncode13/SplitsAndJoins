/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;

import basewindow.BaseWindowModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class BoxModel {
    final Long id;                    //this id allow linkage 
    BaseWindowModel baseWindow;
    Set<BoxModel> parents;
    Set<BoxModel> children;
    public BoxModel(){
        id=UUID.randomUUID().getMostSignificantBits();
        parents=new HashSet<>();
        children=new HashSet<>();
        parents.add(this);
        children.add(this);
    }
    
    
    public BaseWindowModel getBaseWindow() {
        return baseWindow;
    }

    public void setBaseWindow(BaseWindowModel baseWindow) {
        this.baseWindow = baseWindow;
    }

    public Long getId() {
        return id;
    }

    public void addParent(BoxModel parent) {
        System.out.println("boxes.BoxModel.addChild(): "+id%100+" Adding "+parent.getId()%100+" as parent");
        if(parent!=this){
            this.parents.remove(this);
            this.parents.add(parent);
        }
    }

    public void addChild(BoxModel child) {
        System.out.println("boxes.BoxModel.addChild(): "+id%100+" Adding "+child.getId()%100+" as child");
        if(child!=this){
            this.children.remove(this);
            this.children.add(child);
        }
    }

    public Set<BoxModel> getParents() {
        return parents;
    }

    public void setParents(Set<BoxModel> parents) {
        this.parents = parents;
    }

    public Set<BoxModel> getChildren() {
        return children;
    }

    public void setChildren(Set<BoxModel> children) {
        this.children = children;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoxModel other = (BoxModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
