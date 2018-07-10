package com.insp.dao;

import javax.persistence.*;

/**
 * Created by SteveChan on 2018/7/9.
 */
@Entity
@Table(name = "t_jcjh", schema = "jeeplus_ani_big", catalog = "")
public class TJcjhEntity {
    private int id;
    private String jcx;
    private String jcqy;
    private String jhlx;
    private String jckssj;
    private String jcjssj;
    private String cjsj;
    private String cjr;
    private String zxdw;
    private String isClose;
    private String officeid;
    private String fId;
    private String zxr;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jcx")
    public String getJcx() {
        return jcx;
    }

    public void setJcx(String jcx) {
        this.jcx = jcx;
    }

    @Basic
    @Column(name = "jcqy")
    public String getJcqy() {
        return jcqy;
    }

    public void setJcqy(String jcqy) {
        this.jcqy = jcqy;
    }

    @Basic
    @Column(name = "jhlx")
    public String getJhlx() {
        return jhlx;
    }

    public void setJhlx(String jhlx) {
        this.jhlx = jhlx;
    }

    @Basic
    @Column(name = "jckssj")
    public String getJckssj() {
        return jckssj;
    }

    public void setJckssj(String jckssj) {
        this.jckssj = jckssj;
    }

    @Basic
    @Column(name = "jcjssj")
    public String getJcjssj() {
        return jcjssj;
    }

    public void setJcjssj(String jcjssj) {
        this.jcjssj = jcjssj;
    }

    @Basic
    @Column(name = "cjsj")
    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    @Basic
    @Column(name = "cjr")
    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    @Basic
    @Column(name = "zxdw")
    public String getZxdw() {
        return zxdw;
    }

    public void setZxdw(String zxdw) {
        this.zxdw = zxdw;
    }

    @Basic
    @Column(name = "isClose")
    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    @Basic
    @Column(name = "officeid")
    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }

    @Basic
    @Column(name = "f_id")
    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    @Basic
    @Column(name = "zxr")
    public String getZxr() {
        return zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TJcjhEntity that = (TJcjhEntity) o;

        if (id != that.id) return false;
        if (jcx != null ? !jcx.equals(that.jcx) : that.jcx != null) return false;
        if (jcqy != null ? !jcqy.equals(that.jcqy) : that.jcqy != null) return false;
        if (jhlx != null ? !jhlx.equals(that.jhlx) : that.jhlx != null) return false;
        if (jckssj != null ? !jckssj.equals(that.jckssj) : that.jckssj != null) return false;
        if (jcjssj != null ? !jcjssj.equals(that.jcjssj) : that.jcjssj != null) return false;
        if (cjsj != null ? !cjsj.equals(that.cjsj) : that.cjsj != null) return false;
        if (cjr != null ? !cjr.equals(that.cjr) : that.cjr != null) return false;
        if (zxdw != null ? !zxdw.equals(that.zxdw) : that.zxdw != null) return false;
        if (isClose != null ? !isClose.equals(that.isClose) : that.isClose != null) return false;
        if (officeid != null ? !officeid.equals(that.officeid) : that.officeid != null) return false;
        if (fId != null ? !fId.equals(that.fId) : that.fId != null) return false;
        if (zxr != null ? !zxr.equals(that.zxr) : that.zxr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jcx != null ? jcx.hashCode() : 0);
        result = 31 * result + (jcqy != null ? jcqy.hashCode() : 0);
        result = 31 * result + (jhlx != null ? jhlx.hashCode() : 0);
        result = 31 * result + (jckssj != null ? jckssj.hashCode() : 0);
        result = 31 * result + (jcjssj != null ? jcjssj.hashCode() : 0);
        result = 31 * result + (cjsj != null ? cjsj.hashCode() : 0);
        result = 31 * result + (cjr != null ? cjr.hashCode() : 0);
        result = 31 * result + (zxdw != null ? zxdw.hashCode() : 0);
        result = 31 * result + (isClose != null ? isClose.hashCode() : 0);
        result = 31 * result + (officeid != null ? officeid.hashCode() : 0);
        result = 31 * result + (fId != null ? fId.hashCode() : 0);
        result = 31 * result + (zxr != null ? zxr.hashCode() : 0);
        return result;
    }
}
