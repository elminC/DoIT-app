package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("START METHOD IS CALLED");
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Do-It");
        Group group = new Group();
        group.getChildren().add(new Button("Click me"));
        primaryStage.setScene(new Scene(group, 300, 300));

        primaryStage.show();
    }


    @Override
    public void init() throws Exception {
        System.out.println("INIT METHOD IS CALLED");
        super.init();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("STOP METHOD IS CALLED");
        super.stop();

    }

    public static void main(String[] args) {
        System.out.println("MAIN method is called");
        launch(args);
    }
}
