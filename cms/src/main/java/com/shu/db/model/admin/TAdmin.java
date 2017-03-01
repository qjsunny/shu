package com.shu.db.model.admin;

import com.shu.db.model.Pojo;

/**
 * Created by james on 2017/2/9.
 */
public class TAdmin extends Pojo {
    private String id;

    private String name;

    private String password;

    private Integer addAdmin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAddAdmin() {
        return addAdmin;
    }

    public void setAddAdmin(Integer addAdmin) {
        this.addAdmin = addAdmin;
    }
}
