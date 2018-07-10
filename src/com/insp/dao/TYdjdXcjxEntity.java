package com.insp.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by SteveChan on 2018/7/6.
 */
@Entity
@javax.persistence.Table(name = "t_ydjd_xcjx", schema = "jeeplus_ani_big", catalog = "")
public class TYdjdXcjxEntity {
    private int id;

    @Id
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String jcdq;

    @Basic
    @javax.persistence.Column(name = "jcdq")
    public String getJcdq() {
        return jcdq;
    }

    public void setJcdq(String jcdq) {
        this.jcdq = jcdq;
    }

    private String jcaj;

    @Basic
    @javax.persistence.Column(name = "jcaj")
    public String getJcaj() {
        return jcaj;
    }

    public void setJcaj(String jcaj) {
        this.jcaj = jcaj;
    }

    private String jch;

    @Basic
    @javax.persistence.Column(name = "jch")
    public String getJch() {
        return jch;
    }

    public void setJch(String jch) {
        this.jch = jch;
    }

    private String bjxqy;

    @Basic
    @javax.persistence.Column(name = "bjxqy")
    public String getBjxqy() {
        return bjxqy;
    }

    public void setBjxqy(String bjxqy) {
        this.bjxqy = bjxqy;
    }

    private String qydz;

    @Basic
    @javax.persistence.Column(name = "qydz")
    public String getQydz() {
        return qydz;
    }

    public void setQydz(String qydz) {
        this.qydz = qydz;
    }

    private String qyfzr;

    @Basic
    @javax.persistence.Column(name = "qyfzr")
    public String getQyfzr() {
        return qyfzr;
    }

    public void setQyfzr(String qyfzr) {
        this.qyfzr = qyfzr;
    }

    private String qyfzrzw;

    @Basic
    @javax.persistence.Column(name = "qyfzrzw")
    public String getQyfzrzw() {
        return qyfzrzw;
    }

    public void setQyfzrzw(String qyfzrzw) {
        this.qyfzrzw = qyfzrzw;
    }

    private String qyfzrlxdh;

    @Basic
    @javax.persistence.Column(name = "qyfzrlxdh")
    public String getQyfzrlxdh() {
        return qyfzrlxdh;
    }

    public void setQyfzrlxdh(String qyfzrlxdh) {
        this.qyfzrlxdh = qyfzrlxdh;
    }

    private String jccs;

    @Basic
    @javax.persistence.Column(name = "jccs")
    public String getJccs() {
        return jccs;
    }

    public void setJccs(String jccs) {
        this.jccs = jccs;
    }

    private String jckssj;

    @Basic
    @javax.persistence.Column(name = "jckssj")
    public String getJckssj() {
        return jckssj;
    }

    public void setJckssj(String jckssj) {
        this.jckssj = jckssj;
    }

    private String jcjssj;

    @Basic
    @javax.persistence.Column(name = "jcjssj")
    public String getJcjssj() {
        return jcjssj;
    }

    public void setJcjssj(String jcjssj) {
        this.jcjssj = jcjssj;
    }

    private String jgjz;

    @Basic
    @javax.persistence.Column(name = "jgjz")
    public String getJgjz() {
        return jgjz;
    }

    public void setJgjz(String jgjz) {
        this.jgjz = jgjz;
    }

    private String zfry1;

    @Basic
    @javax.persistence.Column(name = "zfry1")
    public String getZfry1() {
        return zfry1;
    }

    public void setZfry1(String zfry1) {
        this.zfry1 = zfry1;
    }

    private String zfry1Zjhm;

    @Basic
    @javax.persistence.Column(name = "zfry1zjhm")
    public String getZfry1Zjhm() {
        return zfry1Zjhm;
    }

    public void setZfry1Zjhm(String zfry1Zjhm) {
        this.zfry1Zjhm = zfry1Zjhm;
    }

    private String zfry2;

    @Basic
    @javax.persistence.Column(name = "zfry2")
    public String getZfry2() {
        return zfry2;
    }

    public void setZfry2(String zfry2) {
        this.zfry2 = zfry2;
    }

    private String zfry2Zjhm;

    @Basic
    @javax.persistence.Column(name = "zfry2zjhm")
    public String getZfry2Zjhm() {
        return zfry2Zjhm;
    }

    public void setZfry2Zjhm(String zfry2Zjhm) {
        this.zfry2Zjhm = zfry2Zjhm;
    }

    private String zfry3;

    @Basic
    @javax.persistence.Column(name = "zfry3")
    public String getZfry3() {
        return zfry3;
    }

    public void setZfry3(String zfry3) {
        this.zfry3 = zfry3;
    }

    private String zfry3Zjhm;

    @Basic
    @javax.persistence.Column(name = "zfry3zjhm")
    public String getZfry3Zjhm() {
        return zfry3Zjhm;
    }

    public void setZfry3Zjhm(String zfry3Zjhm) {
        this.zfry3Zjhm = zfry3Zjhm;
    }

    private String jcxwsdesc;

    @Basic
    @javax.persistence.Column(name = "jcxwsdesc")
    public String getJcxwsdesc() {
        return jcxwsdesc;
    }

    public void setJcxwsdesc(String jcxwsdesc) {
        this.jcxwsdesc = jcxwsdesc;
    }

    private String jcjhlb;

    @Basic
    @javax.persistence.Column(name = "jcjhlb")
    public String getJcjhlb() {
        return jcjhlb;
    }

    public void setJcjhlb(String jcjhlb) {
        this.jcjhlb = jcjhlb;
    }

    private String jcjhid;

    @Basic
    @javax.persistence.Column(name = "jcjhid")
    public String getJcjhid() {
        return jcjhid;
    }

    public void setJcjhid(String jcjhid) {
        this.jcjhid = jcjhid;
    }

    private String jcqk;

    @Basic
    @javax.persistence.Column(name = "jcqk")
    public String getJcqk() {
        return jcqk;
    }

    public void setJcqk(String jcqk) {
        this.jcqk = jcqk;
    }

    private String jcry1Pic;

    @Basic
    @javax.persistence.Column(name = "jcry1_pic")
    public String getJcry1Pic() {
        return jcry1Pic;
    }

    public void setJcry1Pic(String jcry1Pic) {
        this.jcry1Pic = jcry1Pic;
    }

    private String jcry2Pic;

    @Basic
    @javax.persistence.Column(name = "jcry2_pic")
    public String getJcry2Pic() {
        return jcry2Pic;
    }

    public void setJcry2Pic(String jcry2Pic) {
        this.jcry2Pic = jcry2Pic;
    }

    private String bjcdwPic;

    @Basic
    @javax.persistence.Column(name = "bjcdw_pic")
    public String getBjcdwPic() {
        return bjcdwPic;
    }

    public void setBjcdwPic(String bjcdwPic) {
        this.bjcdwPic = bjcdwPic;
    }

    private String remarks;

    @Basic
    @javax.persistence.Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private String createBy;

    @Basic
    @javax.persistence.Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    private Timestamp createDate;

    @Basic
    @javax.persistence.Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    private String updateBy;

    @Basic
    @javax.persistence.Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    private Timestamp updateDate;

    @Basic
    @javax.persistence.Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    private String delFlag;

    @Basic
    @javax.persistence.Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    private String resultPic;

    @Basic
    @javax.persistence.Column(name = "result_pic")
    public String getResultPic() {
        return resultPic;
    }

    public void setResultPic(String resultPic) {
        this.resultPic = resultPic;
    }

    private String isfc;

    @Basic
    @javax.persistence.Column(name = "isfc")
    public String getIsfc() {
        return isfc;
    }

    public void setIsfc(String isfc) {
        this.isfc = isfc;
    }

    private String officeid;

    @Basic
    @javax.persistence.Column(name = "officeid")
    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }

    private String isyh;

    @Basic
    @javax.persistence.Column(name = "isyh")
    public String getIsyh() {
        return isyh;
    }

    public void setIsyh(String isyh) {
        this.isyh = isyh;
    }

    private String bjcqyid;

    @Basic
    @javax.persistence.Column(name = "bjcqyid")
    public String getBjcqyid() {
        return bjcqyid;
    }

    public void setBjcqyid(String bjcqyid) {
        this.bjcqyid = bjcqyid;
    }

    private String iszlzg;

    @Basic
    @javax.persistence.Column(name = "iszlzg")
    public String getIszlzg() {
        return iszlzg;
    }

    public void setIszlzg(String iszlzg) {
        this.iszlzg = iszlzg;
    }

    private String zlzgsbh;

    @Basic
    @javax.persistence.Column(name = "zlzgsbh")
    public String getZlzgsbh() {
        return zlzgsbh;
    }

    public void setZlzgsbh(String zlzgsbh) {
        this.zlzgsbh = zlzgsbh;
    }

    private String zlzgrq;

    @Basic
    @javax.persistence.Column(name = "zlzgrq")
    public String getZlzgrq() {
        return zlzgrq;
    }

    public void setZlzgrq(String zlzgrq) {
        this.zlzgrq = zlzgrq;
    }

    private String zlzgws;

    @Basic
    @javax.persistence.Column(name = "zlzgws")
    public String getZlzgws() {
        return zlzgws;
    }

    public void setZlzgws(String zlzgws) {
        this.zlzgws = zlzgws;
    }

    private String xcjcPic;

    @Basic
    @javax.persistence.Column(name = "xcjc_pic")
    public String getXcjcPic() {
        return xcjcPic;
    }

    public void setXcjcPic(String xcjcPic) {
        this.xcjcPic = xcjcPic;
    }

    private String fcjcPic;

    @Basic
    @javax.persistence.Column(name = "fcjc_pic")
    public String getFcjcPic() {
        return fcjcPic;
    }

    public void setFcjcPic(String fcjcPic) {
        this.fcjcPic = fcjcPic;
    }

    private String fckssj;

    @Basic
    @javax.persistence.Column(name = "fckssj")
    public String getFckssj() {
        return fckssj;
    }

    public void setFckssj(String fckssj) {
        this.fckssj = fckssj;
    }

    private String fcjssj;

    @Basic
    @javax.persistence.Column(name = "fcjssj")
    public String getFcjssj() {
        return fcjssj;
    }

    public void setFcjssj(String fcjssj) {
        this.fcjssj = fcjssj;
    }

    private Integer xcfcId;

    @Basic
    @javax.persistence.Column(name = "xcfc_id")
    public Integer getXcfcId() {
        return xcfcId;
    }

    public void setXcfcId(Integer xcfcId) {
        this.xcfcId = xcfcId;
    }

    private String uploadPic;

    @Basic
    @javax.persistence.Column(name = "upload_pic")
    public String getUploadPic() {
        return uploadPic;
    }

    public void setUploadPic(String uploadPic) {
        this.uploadPic = uploadPic;
    }

    private String dzshyj;

    @Basic
    @javax.persistence.Column(name = "dzshyj")
    public String getDzshyj() {
        return dzshyj;
    }

    public void setDzshyj(String dzshyj) {
        this.dzshyj = dzshyj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TYdjdXcjxEntity that = (TYdjdXcjxEntity) o;

        if (id != that.id) return false;
        if (jcdq != null ? !jcdq.equals(that.jcdq) : that.jcdq != null) return false;
        if (jcaj != null ? !jcaj.equals(that.jcaj) : that.jcaj != null) return false;
        if (jch != null ? !jch.equals(that.jch) : that.jch != null) return false;
        if (bjxqy != null ? !bjxqy.equals(that.bjxqy) : that.bjxqy != null) return false;
        if (qydz != null ? !qydz.equals(that.qydz) : that.qydz != null) return false;
        if (qyfzr != null ? !qyfzr.equals(that.qyfzr) : that.qyfzr != null) return false;
        if (qyfzrzw != null ? !qyfzrzw.equals(that.qyfzrzw) : that.qyfzrzw != null) return false;
        if (qyfzrlxdh != null ? !qyfzrlxdh.equals(that.qyfzrlxdh) : that.qyfzrlxdh != null) return false;
        if (jccs != null ? !jccs.equals(that.jccs) : that.jccs != null) return false;
        if (jckssj != null ? !jckssj.equals(that.jckssj) : that.jckssj != null) return false;
        if (jcjssj != null ? !jcjssj.equals(that.jcjssj) : that.jcjssj != null) return false;
        if (jgjz != null ? !jgjz.equals(that.jgjz) : that.jgjz != null) return false;
        if (zfry1 != null ? !zfry1.equals(that.zfry1) : that.zfry1 != null) return false;
        if (zfry1Zjhm != null ? !zfry1Zjhm.equals(that.zfry1Zjhm) : that.zfry1Zjhm != null) return false;
        if (zfry2 != null ? !zfry2.equals(that.zfry2) : that.zfry2 != null) return false;
        if (zfry2Zjhm != null ? !zfry2Zjhm.equals(that.zfry2Zjhm) : that.zfry2Zjhm != null) return false;
        if (zfry3 != null ? !zfry3.equals(that.zfry3) : that.zfry3 != null) return false;
        if (zfry3Zjhm != null ? !zfry3Zjhm.equals(that.zfry3Zjhm) : that.zfry3Zjhm != null) return false;
        if (jcxwsdesc != null ? !jcxwsdesc.equals(that.jcxwsdesc) : that.jcxwsdesc != null) return false;
        if (jcjhlb != null ? !jcjhlb.equals(that.jcjhlb) : that.jcjhlb != null) return false;
        if (jcjhid != null ? !jcjhid.equals(that.jcjhid) : that.jcjhid != null) return false;
        if (jcqk != null ? !jcqk.equals(that.jcqk) : that.jcqk != null) return false;
        if (jcry1Pic != null ? !jcry1Pic.equals(that.jcry1Pic) : that.jcry1Pic != null) return false;
        if (jcry2Pic != null ? !jcry2Pic.equals(that.jcry2Pic) : that.jcry2Pic != null) return false;
        if (bjcdwPic != null ? !bjcdwPic.equals(that.bjcdwPic) : that.bjcdwPic != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (resultPic != null ? !resultPic.equals(that.resultPic) : that.resultPic != null) return false;
        if (isfc != null ? !isfc.equals(that.isfc) : that.isfc != null) return false;
        if (officeid != null ? !officeid.equals(that.officeid) : that.officeid != null) return false;
        if (isyh != null ? !isyh.equals(that.isyh) : that.isyh != null) return false;
        if (bjcqyid != null ? !bjcqyid.equals(that.bjcqyid) : that.bjcqyid != null) return false;
        if (iszlzg != null ? !iszlzg.equals(that.iszlzg) : that.iszlzg != null) return false;
        if (zlzgsbh != null ? !zlzgsbh.equals(that.zlzgsbh) : that.zlzgsbh != null) return false;
        if (zlzgrq != null ? !zlzgrq.equals(that.zlzgrq) : that.zlzgrq != null) return false;
        if (zlzgws != null ? !zlzgws.equals(that.zlzgws) : that.zlzgws != null) return false;
        if (xcjcPic != null ? !xcjcPic.equals(that.xcjcPic) : that.xcjcPic != null) return false;
        if (fcjcPic != null ? !fcjcPic.equals(that.fcjcPic) : that.fcjcPic != null) return false;
        if (fckssj != null ? !fckssj.equals(that.fckssj) : that.fckssj != null) return false;
        if (fcjssj != null ? !fcjssj.equals(that.fcjssj) : that.fcjssj != null) return false;
        if (xcfcId != null ? !xcfcId.equals(that.xcfcId) : that.xcfcId != null) return false;
        if (uploadPic != null ? !uploadPic.equals(that.uploadPic) : that.uploadPic != null) return false;
        if (dzshyj != null ? !dzshyj.equals(that.dzshyj) : that.dzshyj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jcdq != null ? jcdq.hashCode() : 0);
        result = 31 * result + (jcaj != null ? jcaj.hashCode() : 0);
        result = 31 * result + (jch != null ? jch.hashCode() : 0);
        result = 31 * result + (bjxqy != null ? bjxqy.hashCode() : 0);
        result = 31 * result + (qydz != null ? qydz.hashCode() : 0);
        result = 31 * result + (qyfzr != null ? qyfzr.hashCode() : 0);
        result = 31 * result + (qyfzrzw != null ? qyfzrzw.hashCode() : 0);
        result = 31 * result + (qyfzrlxdh != null ? qyfzrlxdh.hashCode() : 0);
        result = 31 * result + (jccs != null ? jccs.hashCode() : 0);
        result = 31 * result + (jckssj != null ? jckssj.hashCode() : 0);
        result = 31 * result + (jcjssj != null ? jcjssj.hashCode() : 0);
        result = 31 * result + (jgjz != null ? jgjz.hashCode() : 0);
        result = 31 * result + (zfry1 != null ? zfry1.hashCode() : 0);
        result = 31 * result + (zfry1Zjhm != null ? zfry1Zjhm.hashCode() : 0);
        result = 31 * result + (zfry2 != null ? zfry2.hashCode() : 0);
        result = 31 * result + (zfry2Zjhm != null ? zfry2Zjhm.hashCode() : 0);
        result = 31 * result + (zfry3 != null ? zfry3.hashCode() : 0);
        result = 31 * result + (zfry3Zjhm != null ? zfry3Zjhm.hashCode() : 0);
        result = 31 * result + (jcxwsdesc != null ? jcxwsdesc.hashCode() : 0);
        result = 31 * result + (jcjhlb != null ? jcjhlb.hashCode() : 0);
        result = 31 * result + (jcjhid != null ? jcjhid.hashCode() : 0);
        result = 31 * result + (jcqk != null ? jcqk.hashCode() : 0);
        result = 31 * result + (jcry1Pic != null ? jcry1Pic.hashCode() : 0);
        result = 31 * result + (jcry2Pic != null ? jcry2Pic.hashCode() : 0);
        result = 31 * result + (bjcdwPic != null ? bjcdwPic.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (resultPic != null ? resultPic.hashCode() : 0);
        result = 31 * result + (isfc != null ? isfc.hashCode() : 0);
        result = 31 * result + (officeid != null ? officeid.hashCode() : 0);
        result = 31 * result + (isyh != null ? isyh.hashCode() : 0);
        result = 31 * result + (bjcqyid != null ? bjcqyid.hashCode() : 0);
        result = 31 * result + (iszlzg != null ? iszlzg.hashCode() : 0);
        result = 31 * result + (zlzgsbh != null ? zlzgsbh.hashCode() : 0);
        result = 31 * result + (zlzgrq != null ? zlzgrq.hashCode() : 0);
        result = 31 * result + (zlzgws != null ? zlzgws.hashCode() : 0);
        result = 31 * result + (xcjcPic != null ? xcjcPic.hashCode() : 0);
        result = 31 * result + (fcjcPic != null ? fcjcPic.hashCode() : 0);
        result = 31 * result + (fckssj != null ? fckssj.hashCode() : 0);
        result = 31 * result + (fcjssj != null ? fcjssj.hashCode() : 0);
        result = 31 * result + (xcfcId != null ? xcfcId.hashCode() : 0);
        result = 31 * result + (uploadPic != null ? uploadPic.hashCode() : 0);
        result = 31 * result + (dzshyj != null ? dzshyj.hashCode() : 0);
        return result;
    }
}
