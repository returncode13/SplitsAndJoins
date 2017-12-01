/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basewindow;

import boxes.BoxModel;
import boxes.BoxNode;
import dot.DotModel;
import dot.DotNode;
import edge.jobdotedge.JobDotEdgeModel;
import edge.jobdotedge.JobDotEdgeNode;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class BaseWindowController extends Stage {
    
    
    
    private BaseWindowModel model;
    private BaseWindowNode node;
    
    @FXML
    private AnchorPane baseWindow;              //depth =0 
    
    
    @FXML
    private SplitPane splitpane;                  //depth =1 

    
    @FXML   
    private ScrollPane scrollpane;              //depth =2 
    @FXML
    private AnchorPane interactivePane;         //depth =3 
    
    @FXML
    private Button add;

    @FXML
    void addBox(ActionEvent event) {
        BoxModel box=new BoxModel();
        BoxNode boxnode=new BoxNode(box,interactivePane);
        box.setBaseWindow(model);
        model.getObservableBoxes().add(box);
        interactivePane.getChildren().add(boxnode);
        
        /*JobDotEdgeModel jbm=new JobDotEdgeModel();
        //jbm.setParentBox(model);
        JobDotEdgeNode jbn=new JobDotEdgeNode(jbm);
        interactivePane.getChildren().add(jbn);*/
        
        /* DotModel dm=new DotModel();
        DotNode dn=new DotNode(dm);
        interactivePane.getChildren().add(dn);*/
    }
    
    
    
    
    void setModel(BaseWindowModel item) {
        splitpane.prefWidthProperty().bind(baseWindow.widthProperty());
        splitpane.prefHeightProperty().bind(baseWindow.heightProperty());
        scrollpane.prefWidthProperty().bind(splitpane.widthProperty());
        scrollpane.prefHeightProperty().bind(splitpane.heightProperty());
        interactivePane.prefWidthProperty().bind(scrollpane.widthProperty());
        interactivePane.prefHeightProperty().bind(scrollpane.heightProperty());
        
        model=item;
        model.getObservableBoxes().addListener(new ListChangeListener<BoxModel>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends BoxModel> c) {
                System.out.println("BaseWindowController.setModel().ListChanger.onChanged()");
            }
            
        });
        
        
        
    }

    void setView(BaseWindowNode aThis) {
        /*node=aThis;
        baseWindow.getChildren().add(node);*/
        this.setTitle("MCVE for Dots");
         this.setScene(new Scene(baseWindow));
         this.showAndWait();
    }
    
}
