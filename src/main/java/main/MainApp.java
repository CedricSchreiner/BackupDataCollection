package main;

import controller.OverviewController;
import controller.RootLayoutController;
import dao.DatabaseControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.File;
import model.Tag;

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
            createSessionFactory();
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
            OverviewController lo_overviewController = new OverviewController();
        } catch (IOException e) {
            //TODO logger
            e.printStackTrace();
        }
    }

    private void createSessionFactory() {
//        Configuration lo_configuration = new Configuration();
//        lo_configuration.configure("hibernate/hibernate.cfg.xml");
//        SessionFactory lo_sessionFactory = lo_configuration.buildSessionFactory();
//        Session lo_session = lo_sessionFactory.openSession();
//        lo_session.beginTransaction();
//
        dao.DatabaseControl dc = new DatabaseControl();
        File lo_file = new File();
        lo_file.setFileName("test2");
        lo_file.setFilePath("path2");
        lo_file.setFileType("FILE");
        lo_file.setParentId(0);
        Tag tag = new Tag();
        tag.setTagText("Test Tag");
        lo_file.addTag(tag);
        dc.addFile(lo_file);
//
//        lo_session.saveOrUpdate(lo_file);
//
//        lo_session.getTransaction().commit();
//        System.out.println(lo_file.getFileId());
//        lo_session.close();
    }
}
