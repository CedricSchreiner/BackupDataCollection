package model;

import javax.persistence.*;

/**
 * Created by Cedric on 23.10.2017.
 * Tag contains a text which is used to search for
 */

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private int gv_tag_id;

    @Column(name = "tag_text", columnDefinition = "text")
    private String gv_tag_text;

    public int getTagId() {
        return gv_tag_id;
    }

    public void setTagId(int iv_tag_id) {
        this.gv_tag_id = iv_tag_id;
    }

    public String getTagText() {
        return gv_tag_text;
    }

    public void setTagText(String iv_tag_text) {
        this.gv_tag_text = iv_tag_text;
    }
}
