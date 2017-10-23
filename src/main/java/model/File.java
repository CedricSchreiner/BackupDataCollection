package model;

import javax.persistence.*;

/**
 * Created by Cedric on 22.10.2017.
 * Tile contains all informations about a single File, like parent, description, type, etc..
 */
@Entity
@Table (name = "Files")
public class File {
    @Column(name = "parent_id")
    private int gv_parentId;

    //private ArrayList<Integer> go_child_ids;

    @Column(name = "file_name", columnDefinition = "text")
    private String gv_fileName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private int gv_fileId;

    @Column(name = "file_path", columnDefinition = "text")
    private String gv_filePath;

    @Column(name = "file_description", columnDefinition = "text")
    private String gv_fileDescription;

    @Column(name = "file_type", columnDefinition = "text")
    private String gv_fileType;

    public File() {
    //    go_child_ids = new ArrayList<Integer>();
    }

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

    public String getFileDescription() {
        return gv_fileDescription;
    }

    public void setFileDescription(String iv_fileDescription) {
        this.gv_fileDescription = iv_fileDescription;
    }

    public String getFileName() {
        return gv_fileName;
    }

    public void setFileName(String iv_file_name) {
        this.gv_fileName = iv_file_name;
    }

    public int getFileId() {
        return gv_fileId;
    }

    public void setFileId(int iv_file_id) {
        this.gv_fileId = iv_file_id;
    }

    public String getFilePath() {
        return gv_filePath;
    }

    public void setFilePath(String iv_file_path) {
        this.gv_filePath = iv_file_path;
    }

    public String getFileType() {
        return gv_fileType;
    }

    private boolean checkEnum(String iv_fileType) {
        for (enumContainer.FileTypeEnum fileType: enumContainer.FileTypeEnum.values()) {
            if (fileType.name().equals(iv_fileType)) {
                return true;
            }
        }
        return false;
    }

    public void setFileType(String iv_fileType) {
        if (checkEnum(iv_fileType)) {
            this.gv_fileType = iv_fileType;
        }
    }
}
