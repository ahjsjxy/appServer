package com.insp.dao;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by SteveChan on 2018/7/6.
 */
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
    private String fkje;
    private String dzshyj;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bjxqy")
    public String getBjxqy() {
        return bjxqy;
    }

    public void setBjxqy(String bjxqy) {
        this.bjxqy = bjxqy;
    }

    @Basic
    @Column(name = "qydz")
    public String getQydz() {
        return qydz;
    }

    public void setQydz(String qydz) {
        this.qydz = qydz;
    }

    @Basic
    @Column(name = "qyfzr")
    public String getQyfzr() {
        return qyfzr;
    }

    public void setQyfzr(String qyfzr) {
        this.qyfzr = qyfzr;
    }

    @Basic
    @Column(name = "qyfzrzw")
    public String getQyfzrzw() {
        return qyfzrzw;
    }

    public void setQyfzrzw(String qyfzrzw) {
        this.qyfzrzw = qyfzrzw;
    }

    @Basic
    @Column(name = "qyfzrlxdh")
    public String getQyfzrlxdh() {
        return qyfzrlxdh;
    }

    public void setQyfzrlxdh(String qyfzrlxdh) {
        this.qyfzrlxdh = qyfzrlxdh;
    }

    @Basic
    @Column(name = "jccs")
    public String getJccs() {
        return jccs;
    }

    public void setJccs(String jccs) {
        this.jccs = jccs;
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
    @Column(name = "jgjz")
    public String getJgjz() {
        return jgjz;
    }

    public void setJgjz(String jgjz) {
        this.jgjz = jgjz;
    }

    @Basic
    @Column(name = "zfry1")
    public String getZfry1() {
        return zfry1;
    }

    public void setZfry1(String zfry1) {
        this.zfry1 = zfry1;
    }

    @Basic
    @Column(name = "zfry1zjhm")
    public String getZfry1Zjhm() {
        return zfry1Zjhm;
    }

    public void setZfry1Zjhm(String zfry1Zjhm) {
        this.zfry1Zjhm = zfry1Zjhm;
    }

    @Basic
    @Column(name = "zfry2")
    public String getZfry2() {
        return zfry2;
    }

    public void setZfry2(String zfry2) {
        this.zfry2 = zfry2;
    }

    @Basic
    @Column(name = "zfry2zjhm")
    public String getZfry2Zjhm() {
        return zfry2Zjhm;
    }

    public void setZfry2Zjhm(String zfry2Zjhm) {
        this.zfry2Zjhm = zfry2Zjhm;
    }

    @Basic
    @Column(name = "zfry3")
    public String getZfry3() {
        return zfry3;
    }

    public void setZfry3(String zfry3) {
        this.zfry3 = zfry3;
    }

    @Basic
    @Column(name = "zfry3zjhm")
    public String getZfry3Zjhm() {
        return zfry3Zjhm;
    }

    public void setZfry3Zjhm(String zfry3Zjhm) {
        this.zfry3Zjhm = zfry3Zjhm;
    }

    @Basic
    @Column(name = "jcjhid")
    public String getJcjhid() {
        return jcjhid;
    }

    public void setJcjhid(String jcjhid) {
        this.jcjhid = jcjhid;
    }

    @Basic
    @Column(name = "jcqk")
    public String getJcqk() {
        return jcqk;
    }

    public void setJcqk(String jcqk) {
        this.jcqk = jcqk;
    }

    @Basic
    @Column(name = "jcry1_pic")
    public String getJcry1Pic() {
        return jcry1Pic;
    }

    public void setJcry1Pic(String jcry1Pic) {
        this.jcry1Pic = jcry1Pic;
    }

    @Basic
    @Column(name = "jcry2_pic")
    public String getJcry2Pic() {
        return jcry2Pic;
    }

    public void setJcry2Pic(String jcry2Pic) {
        this.jcry2Pic = jcry2Pic;
    }

    @Basic
    @Column(name = "bjcdw_pic")
    public String getBjcdwPic() {
        return bjcdwPic;
    }

    public void setBjcdwPic(String bjcdwPic) {
        this.bjcdwPic = bjcdwPic;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "result_pic")
    public String getResultPic() {
        return resultPic;
    }

    public void setResultPic(String resultPic) {
        this.resultPic = resultPic;
    }

    @Basic
    @Column(name = "isfc")
    public String getIsfc() {
        return isfc;
    }

    public void setIsfc(String isfc) {
        this.isfc = isfc;
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
    @Column(name = "isyh")
    public String getIsyh() {
        return isyh;
    }

    public void setIsyh(String isyh) {
        this.isyh = isyh;
    }

    @Basic
    @Column(name = "bjcqyid")
    public String getBjcqyid() {
        return bjcqyid;
    }

    public void setBjcqyid(String bjcqyid) {
        this.bjcqyid = bjcqyid;
    }

    @Basic
    @Column(name = "yjzt")
    public String getYjzt() {
        return yjzt;
    }

    public void setYjzt(String yjzt) {
        this.yjzt = yjzt;
    }

    @Basic
    @Column(name = "shr")
    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    @Basic
    @Column(name = "shsj")
    public Timestamp getShsj() {
        return shsj;
    }

    public void setShsj(Timestamp shsj) {
        this.shsj = shsj;
    }

    @Basic
    @Column(name = "shzt")
    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    @Basic
    @Column(name = "iszlzg")
    public String getIszlzg() {
        return iszlzg;
    }

    public void setIszlzg(String iszlzg) {
        this.iszlzg = iszlzg;
    }

    @Basic
    @Column(name = "zlzgsbh")
    public String getZlzgsbh() {
        return zlzgsbh;
    }

    public void setZlzgsbh(String zlzgsbh) {
        this.zlzgsbh = zlzgsbh;
    }

    @Basic
    @Column(name = "zlzgrq")
    public String getZlzgrq() {
        return zlzgrq;
    }

    public void setZlzgrq(String zlzgrq) {
        this.zlzgrq = zlzgrq;
    }

    @Basic
    @Column(name = "zlzgws")
    public String getZlzgws() {
        return zlzgws;
    }

    public void setZlzgws(String zlzgws) {
        this.zlzgws = zlzgws;
    }

    @Basic
    @Column(name = "xcjc_pic")
    public String getXcjcPic() {
        return xcjcPic;
    }

    public void setXcjcPic(String xcjcPic) {
        this.xcjcPic = xcjcPic;
    }

    @Basic
    @Column(name = "fkje")
    public String getFkje() {
        return fkje;
    }

    public void setFkje(String fkje) {
        this.fkje = fkje;
    }

    @Basic
    @Column(name = "dzshyj")
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

        TYdjdShEntity shEntity = (TYdjdShEntity) o;

        if (id != shEntity.id) return false;
        if (bjxqy != null ? !bjxqy.equals(shEntity.bjxqy) : shEntity.bjxqy != null) return false;
        if (qydz != null ? !qydz.equals(shEntity.qydz) : shEntity.qydz != null) return false;
        if (qyfzr != null ? !qyfzr.equals(shEntity.qyfzr) : shEntity.qyfzr != null) return false;
        if (qyfzrzw != null ? !qyfzrzw.equals(shEntity.qyfzrzw) : shEntity.qyfzrzw != null) return false;
        if (qyfzrlxdh != null ? !qyfzrlxdh.equals(shEntity.qyfzrlxdh) : shEntity.qyfzrlxdh != null) return false;
        if (jccs != null ? !jccs.equals(shEntity.jccs) : shEntity.jccs != null) return false;
        if (jckssj != null ? !jckssj.equals(shEntity.jckssj) : shEntity.jckssj != null) return false;
        if (jcjssj != null ? !jcjssj.equals(shEntity.jcjssj) : shEntity.jcjssj != null) return false;
        if (jgjz != null ? !jgjz.equals(shEntity.jgjz) : shEntity.jgjz != null) return false;
        if (zfry1 != null ? !zfry1.equals(shEntity.zfry1) : shEntity.zfry1 != null) return false;
        if (zfry1Zjhm != null ? !zfry1Zjhm.equals(shEntity.zfry1Zjhm) : shEntity.zfry1Zjhm != null) return false;
        if (zfry2 != null ? !zfry2.equals(shEntity.zfry2) : shEntity.zfry2 != null) return false;
        if (zfry2Zjhm != null ? !zfry2Zjhm.equals(shEntity.zfry2Zjhm) : shEntity.zfry2Zjhm != null) return false;
        if (zfry3 != null ? !zfry3.equals(shEntity.zfry3) : shEntity.zfry3 != null) return false;
        if (zfry3Zjhm != null ? !zfry3Zjhm.equals(shEntity.zfry3Zjhm) : shEntity.zfry3Zjhm != null) return false;
        if (jcjhid != null ? !jcjhid.equals(shEntity.jcjhid) : shEntity.jcjhid != null) return false;
        if (jcqk != null ? !jcqk.equals(shEntity.jcqk) : shEntity.jcqk != null) return false;
        if (jcry1Pic != null ? !jcry1Pic.equals(shEntity.jcry1Pic) : shEntity.jcry1Pic != null) return false;
        if (jcry2Pic != null ? !jcry2Pic.equals(shEntity.jcry2Pic) : shEntity.jcry2Pic != null) return false;
        if (bjcdwPic != null ? !bjcdwPic.equals(shEntity.bjcdwPic) : shEntity.bjcdwPic != null) return false;
        if (remarks != null ? !remarks.equals(shEntity.remarks) : shEntity.remarks != null) return false;
        if (createBy != null ? !createBy.equals(shEntity.createBy) : shEntity.createBy != null) return false;
        if (createDate != null ? !createDate.equals(shEntity.createDate) : shEntity.createDate != null) return false;
        if (updateBy != null ? !updateBy.equals(shEntity.updateBy) : shEntity.updateBy != null) return false;
        if (updateDate != null ? !updateDate.equals(shEntity.updateDate) : shEntity.updateDate != null) return false;
        if (delFlag != null ? !delFlag.equals(shEntity.delFlag) : shEntity.delFlag != null) return false;
        if (resultPic != null ? !resultPic.equals(shEntity.resultPic) : shEntity.resultPic != null) return false;
        if (isfc != null ? !isfc.equals(shEntity.isfc) : shEntity.isfc != null) return false;
        if (officeid != null ? !officeid.equals(shEntity.officeid) : shEntity.officeid != null) return false;
        if (isyh != null ? !isyh.equals(shEntity.isyh) : shEntity.isyh != null) return false;
        if (bjcqyid != null ? !bjcqyid.equals(shEntity.bjcqyid) : shEntity.bjcqyid != null) return false;
        if (yjzt != null ? !yjzt.equals(shEntity.yjzt) : shEntity.yjzt != null) return false;
        if (shr != null ? !shr.equals(shEntity.shr) : shEntity.shr != null) return false;
        if (shsj != null ? !shsj.equals(shEntity.shsj) : shEntity.shsj != null) return false;
        if (shzt != null ? !shzt.equals(shEntity.shzt) : shEntity.shzt != null) return false;
        if (iszlzg != null ? !iszlzg.equals(shEntity.iszlzg) : shEntity.iszlzg != null) return false;
        if (zlzgsbh != null ? !zlzgsbh.equals(shEntity.zlzgsbh) : shEntity.zlzgsbh != null) return false;
        if (zlzgrq != null ? !zlzgrq.equals(shEntity.zlzgrq) : shEntity.zlzgrq != null) return false;
        if (zlzgws != null ? !zlzgws.equals(shEntity.zlzgws) : shEntity.zlzgws != null) return false;
        if (xcjcPic != null ? !xcjcPic.equals(shEntity.xcjcPic) : shEntity.xcjcPic != null) return false;
        if (fkje != null ? !fkje.equals(shEntity.fkje) : shEntity.fkje != null) return false;
        if (dzshyj != null ? !dzshyj.equals(shEntity.dzshyj) : shEntity.dzshyj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
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
        result = 31 * result + (yjzt != null ? yjzt.hashCode() : 0);
        result = 31 * result + (shr != null ? shr.hashCode() : 0);
        result = 31 * result + (shsj != null ? shsj.hashCode() : 0);
        result = 31 * result + (shzt != null ? shzt.hashCode() : 0);
        result = 31 * result + (iszlzg != null ? iszlzg.hashCode() : 0);
        result = 31 * result + (zlzgsbh != null ? zlzgsbh.hashCode() : 0);
        result = 31 * result + (zlzgrq != null ? zlzgrq.hashCode() : 0);
        result = 31 * result + (zlzgws != null ? zlzgws.hashCode() : 0);
        result = 31 * result + (xcjcPic != null ? xcjcPic.hashCode() : 0);
        result = 31 * result + (fkje != null ? fkje.hashCode() : 0);
        result = 31 * result + (dzshyj != null ? dzshyj.hashCode() : 0);
        return result;
    }
}
