package controller;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainApp;
import model.Tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cedric on 22.10.2017.
 * Controller for the Overview Layout
 */


public class OverviewController {

    private MainApp go_mainApp;
    private Stage go_stage;
    private Tree go_tree;

    @FXML
    private void initialize() {
        go_tree = new Tree();
    }

    public void setMainApp(MainApp io_mainApp) {
        this.go_mainApp = io_mainApp;
    }

    public void setStage(Stage io_stage) {
        this.go_stage = io_stage;
    }

    @FXML
    public void addFiles() {
        List<File> lo_choosenFiles;

        FileChooser lo_fileChooser = new FileChooser();
        lo_fileChooser.setTitle("Add Files");
        lo_choosenFiles = lo_fileChooser.showOpenMultipleDialog(go_stage);
        for (File lo_file : lo_choosenFiles) {
            addFilesToTree(lo_file);
        }
    }

    @FXML
    public void addDirectory() {
        File lo_choosenDirectory;

        DirectoryChooser lo_directoryChooser = new DirectoryChooser();
        lo_directoryChooser.setTitle("Add Directory");
        lo_choosenDirectory = lo_directoryChooser.showDialog(go_stage);
        addFilesToTree(lo_choosenDirectory);
        System.out.println(go_tree);
    }


    private void addFilesToTree(File io_file) {
        if (io_file.isDirectory()) {
            File[] lo_fileList = io_file.listFiles();

            if (lo_fileList != null) {
                for (File lo_directoryFile : lo_fileList) {
                    if (lo_directoryFile.isDirectory()) {
                        System.out.println(lo_directoryFile.getAbsolutePath());

                        model.File lo_file = new model.File();
                        lo_file.setFileName(lo_directoryFile.getName());
                        lo_file.setFilePath(lo_directoryFile.getAbsolutePath());
                        lo_file.setFileType(model.File.DIRECTORY);
                        //go_tree.addNode(lo_file);

                        addFilesToTree(lo_directoryFile);
                    } else {
                        model.File lo_file = new model.File();
                        lo_file.setFileName(lo_directoryFile.getName());
                        lo_file.setFilePath(lo_directoryFile.getAbsolutePath());
                        lo_file.setFileType(model.File.FILE);
                        go_tree.addNode(lo_file);
                    }
                }
                //add the directory itself to the tree
                System.out.println(io_file.getAbsolutePath());
                model.File lo_file = new model.File();
                lo_file.setFileName(io_file.getName());
                lo_file.setFilePath(io_file.getAbsolutePath());
                lo_file.setFileType(model.File.DIRECTORY);
                go_tree.addNode(lo_file);
            }
        } else {
            System.out.println(io_file.getAbsolutePath());
            model.File lo_file = new model.File();
            lo_file.setFileName(io_file.getName());
            lo_file.setFilePath(io_file.getAbsolutePath());
            lo_file.setFileType(model.File.FILE);
            go_tree.addNode(lo_file);
        }
    }
}
