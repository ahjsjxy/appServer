package com.insp.dao;

/**
 * Created by SteveChan on 2018/6/23.
 */
public class JcjhEntity {
    public String jcjh;
    public boolean isActive;
    public boolean isChecked;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean getisChecked() {
        return this.isChecked;
    }

    public void setisChecked(boolean checked) {
        this.isChecked = checked;
    }

    public boolean getisActive() {
        return this.isActive;
    }

    public void setisActive(boolean active) {
        this.isActive = active;
    }

    public String getJcjh() {
        return jcjh;
    }

    public void setJcjh(String jcjh) {
        this.jcjh = jcjh;
    }
}
