package com.yc.sqlite;

import java.io.Serializable;

/**
 * 联系人表的实体类
 * @Date 2024/5/30 14:23
 * @Author ZJY
 * @email 714694358@qq.com
 */
public class ContactsInfo implements Serializable {
    int cid;
    String name;
    String phone;
    String sendPhone;
    boolean isChoosed=false;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }
}
