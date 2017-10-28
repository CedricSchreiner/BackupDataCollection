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
        go_databaseControl = new DatabaseControl();
    }

    public void addNode(Node io_new_node) {
        for (Node lo_child_node: go_root.getChildrenList()) {
            //search in the subtree if the new node path contains the complete child node path
            if (io_new_node.getContent().getFilePath().contains(lo_child_node.getContent().getFilePath())) {
                System.out.println("Subtree");
                addNode(lo_child_node, io_new_node);
            }
        }

        //No fitting tree found so add the new node as child to the root
        if (io_new_node.getParentId() == 0) {
            System.out.println("root");
            go_root.addChild(io_new_node);
            io_new_node.setParentId(0);
        }
    }

    private void addNode(Node io_current_node, Node io_new_node) {

    }

    private void printTreeLevelOrder() {
        boolean found = true;
        int i = 1;
        while(found) {
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

    public Node getRoot() {
        return this.go_root;
    }

}
