package com.insp.dao;

import javax.persistence.*;
import java.util.Objects;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jcx", nullable = true, length = 500)
    public String getJcx() {
        return jcx;
    }

    public void setJcx(String jcx) {
        this.jcx = jcx;
    }

    @Basic
    @Column(name = "cjsj", nullable = true, length = 45)
    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    @Basic
    @Column(name = "cjr", nullable = true, length = 45)
    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    @Basic
    @Column(name = "xgsj", nullable = true, length = 45)
    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    @Basic
    @Column(name = "xgr", nullable = true, length = 45)
    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "jcjhid", nullable = true)
    public Integer getJcjhid() {
        return jcjhid;
    }

    public void setJcjhid(Integer jcjhid) {
        this.jcjhid = jcjhid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TJcxEntity that = (TJcxEntity) o;
        return id == that.id &&
                Objects.equals(jcx, that.jcx) &&
                Objects.equals(cjsj, that.cjsj) &&
                Objects.equals(cjr, that.cjr) &&
                Objects.equals(xgsj, that.xgsj) &&
                Objects.equals(xgr, that.xgr) &&
                Objects.equals(type, that.type) &&
                Objects.equals(jcjhid, that.jcjhid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jcx, cjsj, cjr, xgsj, xgr, type, jcjhid);
    }
}
