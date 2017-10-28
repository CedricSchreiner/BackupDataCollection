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
            lo_overviewController.set
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
//        File lo_file = new File();
//        lo_file.setFileName("test2");
//        lo_file.setFilePath("path2");
//        lo_file.setFileType("FILE");
//        Tag tag = new Tag();
//        tag.setTagText("Test Tag");
//        lo_file.addTag(tag);
//        dc.addFile(lo_file);

        File file1 = new File();
        file1.setFileName("File1");
        file1.setFileType("DIR");
        file1.setFileDescription("Knoten der ersten File");
        file1.setFilePath("C:\\Destop\\Test\\Files");

        File file2 = new File();
        file2.setFileName("File2");
        file2.setFileType("FILE");
        file2.setFileDescription("Knoten der zweiten File");
        file2.setFilePath("C:\\Destop\\Test\\Files\\Neu");

        File file3 = new File();
        file3.setFileName("File3");
        file3.setFileType("FILE");
        file3.setFilePath("C:\\Downloads");

        File file4 = new File();
        file4.setFilePath("C:\\Downloads\\neuerOrdner");

        Tag tag1 = new Tag();
        tag1.setTagText("erster");

        Tag tag2 = new Tag();
        tag2.setTagText("zweiter");

        Tag tag3 = new Tag();
        tag3.setTagText("dritter");

        Tag tag4 = new Tag();
        tag4.setTagText("vierter");

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();

        file1.addTag(tag1);
        file1.addTag(tag2);

        file2.addTag(tag3);
        file2.addTag(tag4);

        node1.setContent(file1);
        node2.setContent(file2);
        node3.setContent(file3);
        node4.setContent(file4);

        Tree lo_tree = new Tree();
        lo_tree.addNode(node1);
        lo_tree.addNode(node2);
        lo_tree.addNode(node3);
        lo_tree.addNode(node4);
        System.out.println(lo_tree);
//
//        lo_session.saveOrUpdate(lo_file);
//
//        lo_session.getTransaction().commit();
//        System.out.println(lo_file.getFileId());
//        lo_session.close();
    }
}
