package eu.mihosoft.nativefx.html5;

import eu.mihosoft.nativefx.NativeNode;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SampleBrowser extends Application {

        public void start(Stage primaryStage) {

                StackPane root = new StackPane();

                //HTML5Engine engine = HTML5Engine.open("https://webglsamples.org/");
                HTML5Engine engine = HTML5Engine.open("https://carvisualizer.plus360degrees.com/threejs/");

                NativeNode view = engine.getView();
                root.getChildren().add(view);

                Scene scene = new Scene(root, 1024,768);
                primaryStage.setScene(scene);
                primaryStage.setTitle("NativeFX Browser Demo (including WebGL)");
                primaryStage.setOnCloseRequest((ev)->engine.terminate());
                primaryStage.show();



        }
}
