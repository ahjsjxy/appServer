package com.insp.dao;

import javax.persistence.*;

/**
 * Created by SteveChan on 2018/7/2.
 */
@Entity
@Table(name = "t_jcx", schema = "jeeplus_ani_big", catalog = "")
public class TJcxEntity {
    private int id;
    private String jcx;
    private String cjsj;
    private String cjr;
    private String xgsj;
    private String xgr;
    private String type;
    private Integer jcjhid;
    private String jcxid;
    private String kind;
    private String wsdesc;

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
    @Column(name = "xgsj")
    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    @Basic
    @Column(name = "xgr")
    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "jcjhid")
    public Integer getJcjhid() {
        return jcjhid;
    }

    public void setJcjhid(Integer jcjhid) {
        this.jcjhid = jcjhid;
    }

    @Basic
    @Column(name = "jcxid")
    public String getJcxid() {
        return jcxid;
    }

    public void setJcxid(String jcxid) {
        this.jcxid = jcxid;
    }

    @Basic
    @Column(name = "kind")
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "wsdesc")
    public String getWsdesc() {
        return wsdesc;
    }

    public void setWsdesc(String wsdesc) {
        this.wsdesc = wsdesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TJcxEntity that = (TJcxEntity) o;

        if (id != that.id) return false;
        if (jcx != null ? !jcx.equals(that.jcx) : that.jcx != null) return false;
        if (cjsj != null ? !cjsj.equals(that.cjsj) : that.cjsj != null) return false;
        if (cjr != null ? !cjr.equals(that.cjr) : that.cjr != null) return false;
        if (xgsj != null ? !xgsj.equals(that.xgsj) : that.xgsj != null) return false;
        if (xgr != null ? !xgr.equals(that.xgr) : that.xgr != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (jcjhid != null ? !jcjhid.equals(that.jcjhid) : that.jcjhid != null) return false;
        if (jcxid != null ? !jcxid.equals(that.jcxid) : that.jcxid != null) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (wsdesc != null ? !wsdesc.equals(that.wsdesc) : that.wsdesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jcx != null ? jcx.hashCode() : 0);
        result = 31 * result + (cjsj != null ? cjsj.hashCode() : 0);
        result = 31 * result + (cjr != null ? cjr.hashCode() : 0);
        result = 31 * result + (xgsj != null ? xgsj.hashCode() : 0);
        result = 31 * result + (xgr != null ? xgr.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (jcjhid != null ? jcjhid.hashCode() : 0);
        result = 31 * result + (jcxid != null ? jcxid.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (wsdesc != null ? wsdesc.hashCode() : 0);
        return result;
    }
}
