package com.yf.common.bean;

import java.util.List;

/**
 * @Author
 * @cerate 2021/9/3 9:33
 **/
public class LoginBean {

    /**
     * msg : 成功
     * code : 0
     * auth : 1
     * app_auth : [{"auth_name":"入库检验","auth_type":0,"code":"","createby":0,"createbytype":0,"createdatetime":-62135798400000,"dataareaid":0,"description":"","flag":1,"id":12,"modifyby":0,"modifybytype":0,"modifydatetime":-62135798400000}]
     * id : 1
     * username : 周玉偈1
     */

    private String msg;
    private int code;
    private int auth;
    private int id;
    private String username;

    public LoginBean(String msg, int code, int id, String username) {
        this.msg = msg;
        this.code = code;
        this.id = id;
        this.username = username;
    }

    private List<AppAuthBean> app_auth;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AppAuthBean> getApp_auth() {
        return app_auth;
    }

    public void setApp_auth(List<AppAuthBean> app_auth) {
        this.app_auth = app_auth;
    }
}
