package model;

import dao.DatabaseControl;

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
    }

    public void addNode(File io_new_file) {
        Node lo_newNode = new Node();
        lo_newNode.setContent(io_new_file);
        go_databaseControl.addObject(lo_newNode);

        addNode(go_root, lo_newNode);
    }

    private void addNode(Node io_current_node, Node io_new_node) {
        String lv_newNodePath = io_new_node.getContent().getFilePath();
        String lv_currentNodePath = io_current_node.getContent().getFilePath();

        //check if the node is a directory
        if (io_current_node.getContent().getFileType().equals(File.DIRECTORY)) {
            //now we need to know if the directory path is included in the path of the new node

            if (lv_newNodePath.contains(lv_currentNodePath)) {
                for (Node lo_child_node : io_current_node.getChildrenList()) {
                    lo_child_node.setParentId(io_new_node.getNodeId());
                    io_new_node.addChild(lo_child_node);
                    io_current_node.deleteChild(lo_child_node);
                }
            }


            String lv_newNodePathShortend = lv_newNodePath.replace(lv_currentNodePath + '\\', "");
            //add the file to the current node if the created path equals the name of the new file
            if (lv_newNodePathShortend.equals(io_new_node.getContent().getFileName())) {
                io_new_node.setParentId(io_current_node.getNodeId());
                io_current_node.addChild(io_new_node);

            } else {
                boolean test = false;
                for (Node lo_child_node : io_current_node.getChildrenList()) {
                    if (lo_child_node.getContent().getFilePath().contains(lv_newNodePath)) {
                        addNode(lo_child_node, io_new_node);
                        test = true;
                    }
                }

                if (!test) {
                    io_current_node.addChild(io_new_node);
                    io_new_node.setParentId(io_current_node.getNodeId());
                }
            }
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

    public String toString() {
        printTreeLevelOrder();
        return "";
    }
}
