package com.insp.dao;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_ydjd_sh", schema = "jeeplus_ani_big", catalog = "")
public class TYdjdShEntity {
    private int id;
    private String bjxqy;
    private String qydz;
    private String qyfzr;
    private String qyfzrzw;
    private String qyfzrlxdh;
    private String jccs;
    private String jckssj;
    private String jcjssj;
    private String jgjz;
    private String zfry1;
    private String zfry1Zjhm;
    private String zfry2;
    private String zfry2Zjhm;
    private String zfry3;
    private String zfry3Zjhm;
    private String jcjhid;
    private String jcqk;
    private String jcry1Pic;
    private String jcry2Pic;
    private String bjcdwPic;
    private String remarks;
    private String createBy;
    private Timestamp createDate;
    private String updateBy;
    private Timestamp updateDate;
    private String delFlag;
    private String resultPic;
    private String isfc;
    private String officeid;
    private String isyh;
    private String bjcqyid;
    private String yjzt;
    private String shr;
    private Timestamp shsj;
    private String shzt;
    private String iszlzg;
    private String zlzgsbh;
    private String zlzgrq;
    private String zlzgws;
    private String xcjcPic;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bjxqy", nullable = true, length = 100)
    public String getBjxqy() {
        return bjxqy;
    }

    public void setBjxqy(String bjxqy) {
        this.bjxqy = bjxqy;
    }

    @Basic
    @Column(name = "qydz", nullable = true, length = 45)
    public String getQydz() {
        return qydz;
    }

    public void setQydz(String qydz) {
        this.qydz = qydz;
    }

    @Basic
    @Column(name = "qyfzr", nullable = true, length = 45)
    public String getQyfzr() {
        return qyfzr;
    }

    public void setQyfzr(String qyfzr) {
        this.qyfzr = qyfzr;
    }

    @Basic
    @Column(name = "qyfzrzw", nullable = true, length = 45)
    public String getQyfzrzw() {
        return qyfzrzw;
    }

    public void setQyfzrzw(String qyfzrzw) {
        this.qyfzrzw = qyfzrzw;
    }

    @Basic
    @Column(name = "qyfzrlxdh", nullable = true, length = 45)
    public String getQyfzrlxdh() {
        return qyfzrlxdh;
    }

    public void setQyfzrlxdh(String qyfzrlxdh) {
        this.qyfzrlxdh = qyfzrlxdh;
    }

    @Basic
    @Column(name = "jccs", nullable = true, length = 45)
    public String getJccs() {
        return jccs;
    }

    public void setJccs(String jccs) {
        this.jccs = jccs;
    }

    @Basic
    @Column(name = "jckssj", nullable = true, length = 45)
    public String getJckssj() {
        return jckssj;
    }

    public void setJckssj(String jckssj) {
        this.jckssj = jckssj;
    }

    @Basic
    @Column(name = "jcjssj", nullable = true, length = 45)
    public String getJcjssj() {
        return jcjssj;
    }

    public void setJcjssj(String jcjssj) {
        this.jcjssj = jcjssj;
    }

    @Basic
    @Column(name = "jgjz", nullable = true, length = 45)
    public String getJgjz() {
        return jgjz;
    }

    public void setJgjz(String jgjz) {
        this.jgjz = jgjz;
    }

    @Basic
    @Column(name = "zfry1", nullable = true, length = 45)
    public String getZfry1() {
        return zfry1;
    }

    public void setZfry1(String zfry1) {
        this.zfry1 = zfry1;
    }

    @Basic
    @Column(name = "zfry1zjhm", nullable = true, length = 45)
    public String getZfry1Zjhm() {
        return zfry1Zjhm;
    }

    public void setZfry1Zjhm(String zfry1Zjhm) {
        this.zfry1Zjhm = zfry1Zjhm;
    }

    @Basic
    @Column(name = "zfry2", nullable = true, length = 45)
    public String getZfry2() {
        return zfry2;
    }

    public void setZfry2(String zfry2) {
        this.zfry2 = zfry2;
    }

    @Basic
    @Column(name = "zfry2zjhm", nullable = true, length = 45)
    public String getZfry2Zjhm() {
        return zfry2Zjhm;
    }

    public void setZfry2Zjhm(String zfry2Zjhm) {
        this.zfry2Zjhm = zfry2Zjhm;
    }

    @Basic
    @Column(name = "zfry3", nullable = true, length = 45)
    public String getZfry3() {
        return zfry3;
    }

    public void setZfry3(String zfry3) {
        this.zfry3 = zfry3;
    }

    @Basic
    @Column(name = "zfry3zjhm", nullable = true, length = 45)
    public String getZfry3Zjhm() {
        return zfry3Zjhm;
    }

    public void setZfry3Zjhm(String zfry3Zjhm) {
        this.zfry3Zjhm = zfry3Zjhm;
    }

    @Basic
    @Column(name = "jcjhid", nullable = true, length = 500)
    public String getJcjhid() {
        return jcjhid;
    }

    public void setJcjhid(String jcjhid) {
        this.jcjhid = jcjhid;
    }

    @Basic
    @Column(name = "jcqk", nullable = true, length = 100)
    public String getJcqk() {
        return jcqk;
    }

    public void setJcqk(String jcqk) {
        this.jcqk = jcqk;
    }

    @Basic
    @Column(name = "jcry1_pic", nullable = true, length = 255)
    public String getJcry1Pic() {
        return jcry1Pic;
    }

    public void setJcry1Pic(String jcry1Pic) {
        this.jcry1Pic = jcry1Pic;
    }

    @Basic
    @Column(name = "jcry2_pic", nullable = true, length = 255)
    public String getJcry2Pic() {
        return jcry2Pic;
    }

    public void setJcry2Pic(String jcry2Pic) {
        this.jcry2Pic = jcry2Pic;
    }

    @Basic
    @Column(name = "bjcdw_pic", nullable = true, length = 255)
    public String getBjcdwPic() {
        return bjcdwPic;
    }

    public void setBjcdwPic(String bjcdwPic) {
        this.bjcdwPic = bjcdwPic;
    }

    @Basic
    @Column(name = "remarks", nullable = true, length = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 64)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_by", nullable = true, length = 64)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_date", nullable = true)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "del_flag", nullable = true, length = 64)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "result_pic", nullable = true, length = 255)
    public String getResultPic() {
        return resultPic;
    }

    public void setResultPic(String resultPic) {
        this.resultPic = resultPic;
    }

    @Basic
    @Column(name = "isfc", nullable = true, length = 255)
    public String getIsfc() {
        return isfc;
    }

    public void setIsfc(String isfc) {
        this.isfc = isfc;
    }

    @Basic
    @Column(name = "officeid", nullable = true, length = 64)
    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }

    @Basic
    @Column(name = "isyh", nullable = true, length = 10)
    public String getIsyh() {
        return isyh;
    }

    public void setIsyh(String isyh) {
        this.isyh = isyh;
    }

    @Basic
    @Column(name = "bjcqyid", nullable = true, length = 64)
    public String getBjcqyid() {
        return bjcqyid;
    }

    public void setBjcqyid(String bjcqyid) {
        this.bjcqyid = bjcqyid;
    }

    @Basic
    @Column(name = "yjzt", nullable = true, length = 255)
    public String getYjzt() {
        return yjzt;
    }

    public void setYjzt(String yjzt) {
        this.yjzt = yjzt;
    }

    @Basic
    @Column(name = "shr", nullable = true, length = 255)
    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    @Basic
    @Column(name = "shsj", nullable = true)
    public Timestamp getShsj() {
        return shsj;
    }

    public void setShsj(Timestamp shsj) {
        this.shsj = shsj;
    }

    @Basic
    @Column(name = "shzt", nullable = true, length = 255)
    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    @Basic
    @Column(name = "iszlzg", nullable = true, length = 255)
    public String getIszlzg() {
        return iszlzg;
    }

    public void setIszlzg(String iszlzg) {
        this.iszlzg = iszlzg;
    }

    @Basic
    @Column(name = "zlzgsbh", nullable = true, length = 255)
    public String getZlzgsbh() {
        return zlzgsbh;
    }

    public void setZlzgsbh(String zlzgsbh) {
        this.zlzgsbh = zlzgsbh;
    }

    @Basic
    @Column(name = "zlzgrq", nullable = true, length = 255)
    public String getZlzgrq() {
        return zlzgrq;
    }

    public void setZlzgrq(String zlzgrq) {
        this.zlzgrq = zlzgrq;
    }

    @Basic
    @Column(name = "zlzgws", nullable = true, length = 255)
    public String getZlzgws() {
        return zlzgws;
    }

    public void setZlzgws(String zlzgws) {
        this.zlzgws = zlzgws;
    }

    @Basic
    @Column(name = "xcjc_pic", nullable = true, length = 255)
    public String getXcjcPic() {
        return xcjcPic;
    }

    public void setXcjcPic(String xcjcPic) {
        this.xcjcPic = xcjcPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TYdjdShEntity that = (TYdjdShEntity) o;
        return id == that.id &&
                Objects.equals(bjxqy, that.bjxqy) &&
                Objects.equals(qydz, that.qydz) &&
                Objects.equals(qyfzr, that.qyfzr) &&
                Objects.equals(qyfzrzw, that.qyfzrzw) &&
                Objects.equals(qyfzrlxdh, that.qyfzrlxdh) &&
                Objects.equals(jccs, that.jccs) &&
                Objects.equals(jckssj, that.jckssj) &&
                Objects.equals(jcjssj, that.jcjssj) &&
                Objects.equals(jgjz, that.jgjz) &&
                Objects.equals(zfry1, that.zfry1) &&
                Objects.equals(zfry1Zjhm, that.zfry1Zjhm) &&
                Objects.equals(zfry2, that.zfry2) &&
                Objects.equals(zfry2Zjhm, that.zfry2Zjhm) &&
                Objects.equals(zfry3, that.zfry3) &&
                Objects.equals(zfry3Zjhm, that.zfry3Zjhm) &&
                Objects.equals(jcjhid, that.jcjhid) &&
                Objects.equals(jcqk, that.jcqk) &&
                Objects.equals(jcry1Pic, that.jcry1Pic) &&
                Objects.equals(jcry2Pic, that.jcry2Pic) &&
                Objects.equals(bjcdwPic, that.bjcdwPic) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(resultPic, that.resultPic) &&
                Objects.equals(isfc, that.isfc) &&
                Objects.equals(officeid, that.officeid) &&
                Objects.equals(isyh, that.isyh) &&
                Objects.equals(bjcqyid, that.bjcqyid) &&
                Objects.equals(yjzt, that.yjzt) &&
                Objects.equals(shr, that.shr) &&
                Objects.equals(shsj, that.shsj) &&
                Objects.equals(shzt, that.shzt) &&
                Objects.equals(iszlzg, that.iszlzg) &&
                Objects.equals(zlzgsbh, that.zlzgsbh) &&
                Objects.equals(zlzgrq, that.zlzgrq) &&
                Objects.equals(zlzgws, that.zlzgws) &&
                Objects.equals(xcjcPic, that.xcjcPic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bjxqy, qydz, qyfzr, qyfzrzw, qyfzrlxdh, jccs, jckssj, jcjssj, jgjz, zfry1, zfry1Zjhm, zfry2, zfry2Zjhm, zfry3, zfry3Zjhm, jcjhid, jcqk, jcry1Pic, jcry2Pic, bjcdwPic, remarks, createBy, createDate, updateBy, updateDate, delFlag, resultPic, isfc, officeid, isyh, bjcqyid, yjzt, shr, shsj, shzt, iszlzg, zlzgsbh, zlzgrq, zlzgws, xcjcPic);
    }
}
