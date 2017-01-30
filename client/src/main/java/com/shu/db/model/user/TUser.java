package com.shu.db.model.user;

import com.shu.db.model.Pojo;

/**
 * Created by admin on 2017/1/30.
 */
public class TUser extends Pojo {
    private String id;

    private String username;

    private String password;

    private Integer level;

    private Integer iszhubo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIszhubo() {
        return iszhubo;
    }

    public void setIszhubo(Integer iszhubo) {
        this.iszhubo = iszhubo;
    }


}
