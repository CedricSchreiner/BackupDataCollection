package model;

import com.sun.javafx.scene.web.Debugger;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import dao.DatabaseControl;
import exceptions.NoNewFileAdded;

import java.util.*;

/**
 * Created by Cedric on 24.10.2017.
 * Contains all Nodes of the Tree
 */
public class Tree {

    private Node go_root;
    private DatabaseControl go_databaseControl;
    //contains all new added Files
    private ArrayList<File> go_newFileList;

    public Tree() {
        /*
         * The File "trees" can have different roots so we make artificial node that connects all sub-trees
         */
        go_root = new Node();
        File lo_rootFile = new File();
        lo_rootFile.setFileType(File.DIRECTORY);
        lo_rootFile.setFileName("root");
        go_root.setContent(lo_rootFile);

        go_databaseControl = new DatabaseControl();
        go_databaseControl.addObject(go_root);
        go_newFileList = new ArrayList<File>();
    }

    public void addNode(File io_new_file) {
        go_newFileList.add(io_new_file);
    }

    public void buildTree() {
        if (go_newFileList.isEmpty()) {
            throw new NoNewFileAdded("Keine neue Datei");
        }

        //search the Parents
        List<File> lo_parents = searchParents();
        for (File test : lo_parents) {
            System.out.println(test.getFileName());
        }
    }

    private List<File> searchParents() {
        List<File> lo_parents = new ArrayList<File>();
        File lo_parent;
        boolean lo_parentFound = false;

        for (File lo_newFile : go_newFileList) {
            for (ListIterator<File> iterator = lo_parents.listIterator(); iterator.hasNext();) {
                lo_parent = iterator.next();
                if (lo_parent.getFilePath().contains(lo_newFile.getFilePath())) {
                    iterator.remove();
                    iterator.add(lo_newFile);
                    lo_parentFound = true;
                } else if (lo_newFile.getFilePath().contains(lo_parent.getFilePath())) {
                    lo_parentFound = true;
                }
            }

            if (!lo_parentFound) {
                lo_parents.add(lo_newFile);
            }
            lo_parentFound = false;
        }

        return lo_parents;
    }

    public File getRoot() {
        return this.go_root.getContent();
    }

    private void print(Node io_node) {
        System.out.println("ParentId: " + io_node.getParentId());
        System.out.println("NodeId: " + io_node.getNodeId());
        System.out.println("Path: " + io_node.getContent().getFilePath() + "\n");

        for (Node test : io_node.getChildrenList()) {
            print(test);
        }
    }

    public String toString() {
        System.out.println("-------------------------------------");
        //print(go_root);
        return "";
    }
}
