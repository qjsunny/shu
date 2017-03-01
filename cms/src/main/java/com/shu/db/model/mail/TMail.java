package com.shu.db.model.mail;

import com.shu.db.model.Pojo;

/**
 * Created by admin on 2017/2/2.
 */
public class TMail extends Pojo {
    private String id;

    private String uidsend;

    private String uidreceive;

    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUidsend() {
        return uidsend;
    }

    public void setUidsend(String uidsend) {
        this.uidsend = uidsend == null ? null : uidsend.trim();
    }

    public String getUidreceive() {
        return uidreceive;
    }

    public void setUidreceive(String uidreceive) {
        this.uidreceive = uidreceive == null ? null : uidreceive.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}
