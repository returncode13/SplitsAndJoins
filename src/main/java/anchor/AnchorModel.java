/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anchor;

import java.awt.Color;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class AnchorModel {
    Color color;
    DoubleProperty x=new SimpleDoubleProperty();
    DoubleProperty y=new SimpleDoubleProperty();

    public AnchorModel() {
        x.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("AnchorModel.changed() x: "+newValue);
            }
        });
        
        y.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("AnchorModel.changed() y: "+newValue);
            }
        });
    }
    
    

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
    
    
}
