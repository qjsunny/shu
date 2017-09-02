package com.shu.db.model.message;

import com.shu.db.model.Pojo;

public class Message extends Pojo{
    private String id;

    private String receiveid;

    private Integer receivetype;

    private String messageid;

    private Integer statue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(String receiveid) {
        this.receiveid = receiveid == null ? null : receiveid.trim();
    }

    public Integer getReceivetype() {
        return receivetype;
    }

    public void setReceivetype(Integer receivetype) {
        this.receivetype = receivetype;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

}