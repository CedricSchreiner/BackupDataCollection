package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Cedric on 23.10.2017.
 * Node Element for the Tree
 */
@Entity
@Table(name = "Nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "node_id")
    private int gv_node_id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Node> go_children_list;

    @Column(name = "parent_id")
    private int gv_parent_id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private File go_file;

    public Node() {
        go_children_list = new ArrayList<Node>();
    }

    public void setNodeId(int iv_node_id) {
        this.gv_node_id = iv_node_id;
    }

    public int getNodeId() {
        return this.gv_node_id;
    }

    public void setParentId(int iv_parent_id) {
        gv_parent_id = iv_parent_id;
    }

    public int getParentId() {
        return this.gv_parent_id;
    }

    public void addChild(Node lo_node) {
        go_children_list.add(lo_node);
    }

    public void setContent(File io_file) {
        this.go_file = io_file;
    }
}
