package com.yf.common.bean;

/**
 * @Author
 * @cerate 2021/9/2 18:03
 **/
public class VersionBean {
    /**
     * id : 99
     * code :
     * version_no : 21.8.26.1
     * version_name : 21.8.26.1
     * update_url : http://47.106.157.255/android/21.8.26.1.apk
     * update_time : 1629942153000
     * update_describe : 修复倒仓上架问题
     * operator : 刘武锐
     * createbytype : 0
     * createby : 0
     * createdatetime : 1629942153000
     * modifybytype : 0
     * modifyby : 0
     * modifydatetime : 1629942153000
     * dataareaid : 0
     */

    private int id;
    private String code;
    private String version_no;
    private String version_name;
    private String update_url;
    private long update_time;
    private String update_describe;
    private String operator;
    private int createbytype;
    private int createby;
    private long createdatetime;
    private int modifybytype;
    private int modifyby;
    private long modifydatetime;
    private int dataareaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_describe() {
        return update_describe;
    }

    public void setUpdate_describe(String update_describe) {
        this.update_describe = update_describe;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCreatebytype() {
        return createbytype;
    }

    public void setCreatebytype(int createbytype) {
        this.createbytype = createbytype;
    }

    public int getCreateby() {
        return createby;
    }

    public void setCreateby(int createby) {
        this.createby = createby;
    }

    public long getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(long createdatetime) {
        this.createdatetime = createdatetime;
    }

    public int getModifybytype() {
        return modifybytype;
    }

    public void setModifybytype(int modifybytype) {
        this.modifybytype = modifybytype;
    }

    public int getModifyby() {
        return modifyby;
    }

    public void setModifyby(int modifyby) {
        this.modifyby = modifyby;
    }

    public long getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(long modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public int getDataareaid() {
        return dataareaid;
    }

    public void setDataareaid(int dataareaid) {
        this.dataareaid = dataareaid;
    }
}
