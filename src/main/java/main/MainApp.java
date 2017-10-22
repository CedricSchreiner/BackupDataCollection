package main;

import controller.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Cedric on 22.10.2017.
 * Hauptklasse für JavaFX
 */
public class MainApp extends Application{
    private VBox go_rootLayout;
    private Stage go_primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage io_primaryStage) throws Exception{
        go_primaryStage = io_primaryStage;
        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader lo_loader = new FXMLLoader();
            lo_loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            go_rootLayout = (VBox) lo_loader.load();
            RootLayoutController rootLayoutController = lo_loader.getController();
            rootLayoutController.setMainApp(this);

            // Show the scene containing the root layout.
            Scene go_scene = new Scene(go_rootLayout);
            go_primaryStage.setScene(go_scene);
            go_primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
