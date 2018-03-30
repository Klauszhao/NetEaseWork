package com.bean;

import java.util.Date;

public class User {
    private Short id;

    private String username;

    private String password;

    private Boolean type;

    private Date createtime;
    public User(){
        
    }
    public User(String username,String password){
        setUsername(username);
        setPassword(password);
    }
    public User(Short id,String password){
        setId(id);
        setPassword(password);
    }
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}