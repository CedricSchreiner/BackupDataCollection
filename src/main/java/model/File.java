package model;

import javax.persistence.*;

/**
 * Created by Cedric on 22.10.2017.
 * File Objekt
 */
@Entity
public class File {
    private int gv_parentId;
    //private ArrayList<Integer> go_child_ids;
    private String gv_fileName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gv_fileId;
    private String gv_filePath;


    public File() {
    //    go_child_ids = new ArrayList<Integer>();
    }

    @Column(name = "parent_id")
    public int getParentId() {
        return gv_parentId;
    }

    public void setParentId(int iv_parent_id) {
        this.gv_parentId = iv_parent_id;
    }

    /*
    public ArrayList<Integer> get_child_ids() {
        return this.go_child_ids;
    }

    public void set_child_ids(ArrayList<Integer> io_child_ids) {
        this.go_child_ids = io_child_ids;
    }
    */

    @Column(name = "file_name")
    public String getFileName() {
        return gv_fileName;
    }

    public void setFileName(String iv_file_name) {
        this.gv_fileName = iv_file_name;
    }

    @Column(name = "file_id")
    public int getFileId() {
        return gv_fileId;
    }

    public void setFileId(int iv_file_id) {
        this.gv_fileId = iv_file_id;
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return gv_filePath;
    }

    public void setFilePath(String iv_file_path) {
        this.gv_filePath = iv_file_path;
    }
}
