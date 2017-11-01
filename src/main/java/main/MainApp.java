package main;

import controller.OverviewController;
import controller.RootLayoutController;
import dao.DatabaseControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.File;
import model.Node;
import model.Tag;
import model.Tree;

import java.io.IOException;

/**
 * Created by Cedric on 22.10.2017.
 * Hauptklasse für JavaFX
 */
public class MainApp extends Application{
    private Pane go_rootLayout;
    private Stage go_primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage io_primaryStage) throws Exception{
        try {
            go_primaryStage = io_primaryStage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        initRootLayout();
        initOverview();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader lo_loader = new FXMLLoader();
            lo_loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            go_rootLayout = (Pane) lo_loader.load();
            RootLayoutController rootLayoutController = lo_loader.getController();
            rootLayoutController.setMainApp(this);

            // Show the scene containing the root layout.
            Scene lo_scene = new Scene(go_rootLayout);
            go_primaryStage.setScene(lo_scene);
            go_primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
        System.exit(0);
    }


    private void initOverview() {
        try {
            FXMLLoader lo_loader = new FXMLLoader();
            lo_loader.setLocation(MainApp.class.getResource("/view/Overview.fxml"));
            AnchorPane lo_overviiew = (AnchorPane) lo_loader.load();

            go_rootLayout.getChildren().add(lo_overviiew);
            OverviewController lo_overviewController = lo_loader.getController();
            lo_overviewController.setMainApp(this);
            lo_overviewController.setStage(go_primaryStage);
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }
}
