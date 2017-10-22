package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by Cedric on 22.10.2017.
 * File Objekt
 */
@Entity
public class File {
    private int gv_parent_id;
    //private ArrayList<Integer> go_child_ids;
    private String gv_file_name;
    @Id
    private int gv_file_id;
    private String gv_file_path;


    public File() {
    //    go_child_ids = new ArrayList<Integer>();
    }

    public int get_parent_id() {
        return gv_parent_id;
    }

    public void set_parent_id(int iv_parent_id) {
        this.gv_parent_id = iv_parent_id;
    }

    /*
    public ArrayList<Integer> get_child_ids() {
        return this.go_child_ids;
    }

    public void set_child_ids(ArrayList<Integer> io_child_ids) {
        this.go_child_ids = io_child_ids;
    }
    */

    public String get_file_name() {
        return gv_file_name;
    }

    public void set_file_name(String iv_file_name) {
        this.gv_file_name = iv_file_name;
    }

    public int get_file_id() {
        return gv_file_id;
    }

    public void set_file_id(int iv_file_id) {
        this.gv_file_id = iv_file_id;
    }

    public String get_file_path() {
        return gv_file_path;
    }

    public void set_file_path(String iv_file_path) {
        this.gv_file_path = iv_file_path;
    }
}
