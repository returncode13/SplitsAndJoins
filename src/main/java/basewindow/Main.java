/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basewindow;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/**
 *
 * @author sharath nair <sharath.nair@polarcus.com>
 */
public class Main extends Application{
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
           
        BaseWindowModel model=new BaseWindowModel();
        BaseWindowNode node=new BaseWindowNode(model);
        
        /*               primaryStage.setTitle("ComboBoxSample");
        Scene scene = new Scene(new Group(), 450, 250);
        
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo();
        moveTo.setX(0.0f);
        moveTo.setY(0.0f);
        
        CubicCurveTo cubicTo = new CubicCurveTo();
        cubicTo.setControlX1(0.0f);
        cubicTo.setControlY1(0.0f);
        cubicTo.setControlX2(100.0f);
        cubicTo.setControlY2(100.0f);
        cubicTo.setX(100.0f);
        cubicTo.setY(50.0f);
        
        path.getElements().add(moveTo);
        path.getElements().add(cubicTo);
        
        
        Group root = (Group) scene.getRoot();
        root.getChildren().add(path);
        primaryStage.setScene(scene);
        primaryStage.show();*/
  //}
      
//        Scene scene = new Scene(node, 300, 250);
        
/*primaryStage.setTitle("Hello World!");
primaryStage.setScene(node);
primaryStage.show();*/
    }
}
