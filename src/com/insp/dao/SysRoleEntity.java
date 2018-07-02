package com.insp.dao;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by SteveChan on 2018/6/27.
 */
@Entity
@Table(name = "sys_role", schema = "jeeplus_ani_big", catalog = "")
public class SysRoleEntity {
    private String id;
    private String officeId;
    private String name;
    private String enname;
    private String roleType;
    private String isSys;
    private String useable;
    private String createBy;
    private Timestamp createDate;
    private String updateBy;
    private Timestamp updateDate;
    private String remarks;
    private String delFlag;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "office_id")
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "enname")
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    @Basic
    @Column(name = "role_type")
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Basic
    @Column(name = "is_sys")
    public String getIsSys() {
        return isSys;
    }

    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }

    @Basic
    @Column(name = "useable")
    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRoleEntity entity = (SysRoleEntity) o;

        if (id != null ? !id.equals(entity.id) : entity.id != null) return false;
        if (officeId != null ? !officeId.equals(entity.officeId) : entity.officeId != null) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (enname != null ? !enname.equals(entity.enname) : entity.enname != null) return false;
        if (roleType != null ? !roleType.equals(entity.roleType) : entity.roleType != null) return false;
        if (isSys != null ? !isSys.equals(entity.isSys) : entity.isSys != null) return false;
        if (useable != null ? !useable.equals(entity.useable) : entity.useable != null) return false;
        if (createBy != null ? !createBy.equals(entity.createBy) : entity.createBy != null) return false;
        if (createDate != null ? !createDate.equals(entity.createDate) : entity.createDate != null) return false;
        if (updateBy != null ? !updateBy.equals(entity.updateBy) : entity.updateBy != null) return false;
        if (updateDate != null ? !updateDate.equals(entity.updateDate) : entity.updateDate != null) return false;
        if (remarks != null ? !remarks.equals(entity.remarks) : entity.remarks != null) return false;
        if (delFlag != null ? !delFlag.equals(entity.delFlag) : entity.delFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (officeId != null ? officeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (enname != null ? enname.hashCode() : 0);
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        result = 31 * result + (isSys != null ? isSys.hashCode() : 0);
        result = 31 * result + (useable != null ? useable.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }
}
