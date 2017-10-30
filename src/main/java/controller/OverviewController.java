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
    }


    private void addFilesToTree(File io_file) {
        //we have to add the child nodes of the file if the file is a direcotry
        if (io_file.isDirectory()) {
            //add the directory itself
            addFile(io_file, model.File.DIRECTORY);
            File[] lo_fileList = io_file.listFiles();
            if (lo_fileList != null) {
                //add all files in the directory
                for (File lo_directoryChildFile : lo_fileList) {
                    //if the directory contains another directory, do the same for it
                    if (lo_directoryChildFile.isDirectory()) {
                        addFilesToTree(lo_directoryChildFile);
                    } else {
                        //add normal file
                        addFile(lo_directoryChildFile, model.File.FILE);
                    }
                }
            }
        } else {
            addFile(io_file, model.File.FILE);
        }
    }


    private void addFile(File io_file, String iv_type) {
        model.File lo_file = new model.File();
        lo_file.setFileName(io_file.getName());
        lo_file.setFilePath(io_file.getAbsolutePath());
        lo_file.setFileType(iv_type);
        go_tree.addNode(lo_file);
        System.out.println(go_tree);
    }
}
