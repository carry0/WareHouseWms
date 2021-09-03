package com.yf.common.bean;

/**
 * @Author
 * @cerate 2021/9/3 9:33
 **/
public class AppAuthBean {
    /**
     * auth_name : 入库检验
     * auth_type : 0
     * code :
     * createby : 0
     * createbytype : 0
     * createdatetime : -62135798400000
     * dataareaid : 0
     * description :
     * flag : 1
     * id : 12
     * modifyby : 0
     * modifybytype : 0
     * modifydatetime : -62135798400000
     */

    private String auth_name;
    private int auth_type;
    private String code;
    private int createby;
    private int createbytype;
    private long createdatetime;
    private int dataareaid;
    private String description;
    private int flag;
    private int id;
    private int modifyby;
    private int modifybytype;
    private long modifydatetime;

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    public int getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(int auth_type) {
        this.auth_type = auth_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCreateby() {
        return createby;
    }

    public void setCreateby(int createby) {
        this.createby = createby;
    }

    public int getCreatebytype() {
        return createbytype;
    }

    public void setCreatebytype(int createbytype) {
        this.createbytype = createbytype;
    }

    public long getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(long createdatetime) {
        this.createdatetime = createdatetime;
    }

    public int getDataareaid() {
        return dataareaid;
    }

    public void setDataareaid(int dataareaid) {
        this.dataareaid = dataareaid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModifyby() {
        return modifyby;
    }

    public void setModifyby(int modifyby) {
        this.modifyby = modifyby;
    }

    public int getModifybytype() {
        return modifybytype;
    }

    public void setModifybytype(int modifybytype) {
        this.modifybytype = modifybytype;
    }

    public long getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(long modifydatetime) {
        this.modifydatetime = modifydatetime;
    }
}
