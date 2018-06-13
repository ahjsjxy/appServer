package com.insp.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_jcjh", schema = "company_inspection", catalog = "")
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

    @Id
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
    @Column(name = "jcqy", nullable = true, length = 500)
    public String getJcqy() {
        return jcqy;
    }

    public void setJcqy(String jcqy) {
        this.jcqy = jcqy;
    }

    @Basic
    @Column(name = "jhlx", nullable = true, length = 45)
    public String getJhlx() {
        return jhlx;
    }

    public void setJhlx(String jhlx) {
        this.jhlx = jhlx;
    }

    @Basic
    @Column(name = "jckssj", nullable = true, length = 100)
    public String getJckssj() {
        return jckssj;
    }

    public void setJckssj(String jckssj) {
        this.jckssj = jckssj;
    }

    @Basic
    @Column(name = "jcjssj", nullable = true, length = 100)
    public String getJcjssj() {
        return jcjssj;
    }

    public void setJcjssj(String jcjssj) {
        this.jcjssj = jcjssj;
    }

    @Basic
    @Column(name = "cjsj", nullable = true, length = 100)
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
    @Column(name = "zxdw", nullable = true, length = 45)
    public String getZxdw() {
        return zxdw;
    }

    public void setZxdw(String zxdw) {
        this.zxdw = zxdw;
    }

    @Basic
    @Column(name = "isClose", nullable = true, length = 45)
    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TJcjhEntity that = (TJcjhEntity) o;
        return id == that.id &&
                Objects.equals(jcx, that.jcx) &&
                Objects.equals(jcqy, that.jcqy) &&
                Objects.equals(jhlx, that.jhlx) &&
                Objects.equals(jckssj, that.jckssj) &&
                Objects.equals(jcjssj, that.jcjssj) &&
                Objects.equals(cjsj, that.cjsj) &&
                Objects.equals(cjr, that.cjr) &&
                Objects.equals(zxdw, that.zxdw) &&
                Objects.equals(isClose, that.isClose);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jcx, jcqy, jhlx, jckssj, jcjssj, cjsj, cjr, zxdw, isClose);
    }
}
