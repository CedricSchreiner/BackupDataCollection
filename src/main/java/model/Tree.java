package model;

import dao.DatabaseControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric on 24.10.2017.
 * Contains all Nodes of the Tree
 */
public class Tree {
    private Node go_root;
    private DatabaseControl go_databaseControl;

    public Tree() {
        /*
         * The File "trees" can have different roots so we make artificial node that connects all sub-trees
         */
        go_root = new Node();
        File lo_rootFile = new File();
        lo_rootFile.setFileType(File.DIRECTORY);
        lo_rootFile.setFilePath("!!");
        go_root.setContent(lo_rootFile);

        go_databaseControl = new DatabaseControl();
        go_databaseControl.addObject(go_root);
    }

    public void addNode(File io_new_file) {
        String lv_rootPath;

        Node lo_newNode = new Node();
        lo_newNode.setContent(io_new_file);
        go_databaseControl.addObject(lo_newNode);
        lv_rootPath = io_new_file.getFilePath();
        lv_rootPath = lv_rootPath.substring(0, lv_rootPath.indexOf("\\"));
       // System.out.println("New:" + io_new_file.getFilePath());
        //System.out.println("Root: " + lv_rootPath + "\n");
        go_root.getContent().setFilePath(lv_rootPath);

        addNode(go_root, lo_newNode);

    }

    private void addNode(Node io_current_node, Node io_new_node) {
        List<Node> testList = new ArrayList<Node>();
        boolean test = false;
        String lv_newNodePath = io_new_node.getContent().getFilePath();
        String lv_currentNodePath = io_current_node.getContent().getFilePath();
        //This String contains only the new file name if the current node is the parent
        String lv_parentPath = lv_newNodePath.replace(lv_currentNodePath + "\\", "");

        //Node is the direct child of the current node
        if (lv_parentPath.equals(io_new_node.getContent().getFileName())) {
            //System.out.println("Direct");
            test = true;
            io_current_node.addChild(io_new_node);
            io_new_node.setParentId(io_current_node.getNodeId());

        //the current node is a indirect parent of the new node
        //check if the children are the parents or indirect parents
        } else if (lv_newNodePath.contains(lv_currentNodePath)) {
            //System.out.println("Inirect");
            for (Node lo_child_node : io_current_node.getChildrenList()) {
                if (lv_newNodePath.contains(lo_child_node.getContent().getFilePath())) {
                    test = true;
                    addNode(lo_child_node, io_new_node);
                }
            }
        }

        if (!test)  {
           // System.out.println("else");
            io_new_node.setParentId(io_current_node.getNodeId());
            io_current_node.addChild(io_new_node);
        }
    }

    private void printTreeLevelOrder() {
        boolean found = true;
        int i = 1;
        while(found) {
            System.out.println("-------------------------------");
            System.out.println("Depth: " + i);
            found = printTree(go_root, i);
            i++;
        }
    }

    private boolean printTree(Node io_node, int iv_depth) {
        if (iv_depth == 0) {
            System.out.println("ParentId: " + io_node.getParentId());
            System.out.println("NodeId: " + io_node.getNodeId());
            System.out.println("Path: " + io_node.getContent().getFilePath() + "\n");
            return true;
        } else {
            for (Node lo_child_node: io_node.getChildrenList()) {
                printTree(lo_child_node, iv_depth - 1);
            }
        }
        return false;
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
        print(go_root);
        return "";
    }
}
