package com.shu.db.model.messageText;

import com.shu.db.model.Pojo;

public class MessageText extends Pojo{
    private String id;

    private String sendid;

    private Integer sendtype;

    private String title;

    private String contents;

    private Integer type;

    private Integer receivetype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid == null ? null : sendid.trim();
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReceivetype() {
        return receivetype;
    }

    public void setReceivetype(Integer receivetype) {
        this.receivetype = receivetype;
    }

    @Override
    public String toString() {
        return "MessageText{" +
                "id='" + id + '\'' +
                ", sendid='" + sendid + '\'' +
                ", sendtype=" + sendtype +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", type=" + type +
                ", receivetype=" + receivetype +
                '}';
    }
}