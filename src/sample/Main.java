package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.UIController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));

        GridPane gridPane = fxmlLoader.load();

        UIController controller = fxmlLoader.getController();
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
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
