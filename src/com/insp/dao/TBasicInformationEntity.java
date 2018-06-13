package com.insp.dao;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_basic_information", schema = "jeeplus_ani_big", catalog = "")
public class TBasicInformationEntity {
    private int id;
    private String enterpriseName;
    private String areaDm;
    private String area;
    private String postCode;
    private String productionAddress;
    private String areaPropertyDm;
    private String areaProperty;
    private String legalRepresentative;
    private String legalRepresentativePhone;
    private String securityOfficer;
    private String securityOfficerPhone;
    private Timestamp establishDate;
    private String registeredCapital;
    private String economicTypeDm;
    private String economicType;
    private Timestamp operationPeriodBegin;
    private Timestamp operationPeriodEnd;
    private String scopeOfOperation;
    private String industryNatureDm;
    private String industryNature;
    private String enterpriseScaleDm;
    private String enterpriseScale;
    private String coveringArea;
    private String mainMaterial;
    private String mainProduct;
    private String numberOfStaff;
    private String annualRevenue;
    private String safetyManagementDm;
    private String safetyManagement;
    private String productionEquipmentNo;
    private String securityManagerNo;
    private String specialOperatorNo;
    private String safetyProductionPermitDm;
    private String safetyProductionPermit;
    private String safetyProductionPermitNo;
    private Timestamp safetyProductionPermitBegin;
    private Timestamp safetyProductionPermitEnd;
    private String standardizationDm;
    private String standardization;
    private String standardizationCardNo;
    private Timestamp standardizationCardDate;
    private String recordDm;
    private String record;
    private String recordNo;
    private Timestamp recordDate;
    private String declareDm;
    private String declareMc;
    private String declareNo;
    private String declareNumber;
    private String timestamp;
    private String imgs;
    private String locationX;
    private String locationY;
    private String remarks;
    private String createBy;
    private Timestamp createDate;
    private String updateBy;
    private Timestamp updateDate;
    private String delFlag;
    private String officeid;

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
    @Column(name = "enterprise_name", nullable = true, length = 200)
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Basic
    @Column(name = "area_dm", nullable = true, length = 10)
    public String getAreaDm() {
        return areaDm;
    }

    public void setAreaDm(String areaDm) {
        this.areaDm = areaDm;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 200)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "post_code", nullable = true, length = 12)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "production_address", nullable = true, length = 500)
    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    @Basic
    @Column(name = "area_property_dm", nullable = true, length = 10)
    public String getAreaPropertyDm() {
        return areaPropertyDm;
    }

    public void setAreaPropertyDm(String areaPropertyDm) {
        this.areaPropertyDm = areaPropertyDm;
    }

    @Basic
    @Column(name = "area_property", nullable = true, length = 100)
    public String getAreaProperty() {
        return areaProperty;
    }

    public void setAreaProperty(String areaProperty) {
        this.areaProperty = areaProperty;
    }

    @Basic
    @Column(name = "legal_representative", nullable = true, length = 100)
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    @Basic
    @Column(name = "legal_representative_phone", nullable = true, length = 20)
    public String getLegalRepresentativePhone() {
        return legalRepresentativePhone;
    }

    public void setLegalRepresentativePhone(String legalRepresentativePhone) {
        this.legalRepresentativePhone = legalRepresentativePhone;
    }

    @Basic
    @Column(name = "security_officer", nullable = true, length = 100)
    public String getSecurityOfficer() {
        return securityOfficer;
    }

    public void setSecurityOfficer(String securityOfficer) {
        this.securityOfficer = securityOfficer;
    }

    @Basic
    @Column(name = "security_officer_phone", nullable = true, length = 100)
    public String getSecurityOfficerPhone() {
        return securityOfficerPhone;
    }

    public void setSecurityOfficerPhone(String securityOfficerPhone) {
        this.securityOfficerPhone = securityOfficerPhone;
    }

    @Basic
    @Column(name = "establish_date", nullable = true)
    public Timestamp getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Timestamp establishDate) {
        this.establishDate = establishDate;
    }

    @Basic
    @Column(name = "registered_capital", nullable = true, length = 11)
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Basic
    @Column(name = "economic_type_dm", nullable = true, length = 500)
    public String getEconomicTypeDm() {
        return economicTypeDm;
    }

    public void setEconomicTypeDm(String economicTypeDm) {
        this.economicTypeDm = economicTypeDm;
    }

    @Basic
    @Column(name = "economic_type", nullable = true, length = 500)
    public String getEconomicType() {
        return economicType;
    }

    public void setEconomicType(String economicType) {
        this.economicType = economicType;
    }

    @Basic
    @Column(name = "operation_period_begin", nullable = true)
    public Timestamp getOperationPeriodBegin() {
        return operationPeriodBegin;
    }

    public void setOperationPeriodBegin(Timestamp operationPeriodBegin) {
        this.operationPeriodBegin = operationPeriodBegin;
    }

    @Basic
    @Column(name = "operation_period_end", nullable = true)
    public Timestamp getOperationPeriodEnd() {
        return operationPeriodEnd;
    }

    public void setOperationPeriodEnd(Timestamp operationPeriodEnd) {
        this.operationPeriodEnd = operationPeriodEnd;
    }

    @Basic
    @Column(name = "scope_of_operation", nullable = true, length = 500)
    public String getScopeOfOperation() {
        return scopeOfOperation;
    }

    public void setScopeOfOperation(String scopeOfOperation) {
        this.scopeOfOperation = scopeOfOperation;
    }

    @Basic
    @Column(name = "industry_nature_dm", nullable = true, length = 10)
    public String getIndustryNatureDm() {
        return industryNatureDm;
    }

    public void setIndustryNatureDm(String industryNatureDm) {
        this.industryNatureDm = industryNatureDm;
    }

    @Basic
    @Column(name = "industry_nature", nullable = true, length = 300)
    public String getIndustryNature() {
        return industryNature;
    }

    public void setIndustryNature(String industryNature) {
        this.industryNature = industryNature;
    }

    @Basic
    @Column(name = "enterprise_scale_dm", nullable = true, length = 10)
    public String getEnterpriseScaleDm() {
        return enterpriseScaleDm;
    }

    public void setEnterpriseScaleDm(String enterpriseScaleDm) {
        this.enterpriseScaleDm = enterpriseScaleDm;
    }

    @Basic
    @Column(name = "enterprise_scale", nullable = true, length = 300)
    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }

    @Basic
    @Column(name = "covering_area", nullable = true, length = 11)
    public String getCoveringArea() {
        return coveringArea;
    }

    public void setCoveringArea(String coveringArea) {
        this.coveringArea = coveringArea;
    }

    @Basic
    @Column(name = "main_material", nullable = true, length = 255)
    public String getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(String mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    @Basic
    @Column(name = "main_product", nullable = true, length = 255)
    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    @Basic
    @Column(name = "number_of_staff", nullable = true, length = 11)
    public String getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(String numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    @Basic
    @Column(name = "annual_revenue", nullable = true, length = 100)
    public String getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    @Basic
    @Column(name = "safety_management_dm", nullable = true, length = 10)
    public String getSafetyManagementDm() {
        return safetyManagementDm;
    }

    public void setSafetyManagementDm(String safetyManagementDm) {
        this.safetyManagementDm = safetyManagementDm;
    }

    @Basic
    @Column(name = "safety_management", nullable = true, length = 100)
    public String getSafetyManagement() {
        return safetyManagement;
    }

    public void setSafetyManagement(String safetyManagement) {
        this.safetyManagement = safetyManagement;
    }

    @Basic
    @Column(name = "production_equipment_no", nullable = true, length = 10)
    public String getProductionEquipmentNo() {
        return productionEquipmentNo;
    }

    public void setProductionEquipmentNo(String productionEquipmentNo) {
        this.productionEquipmentNo = productionEquipmentNo;
    }

    @Basic
    @Column(name = "security_manager_no", nullable = true, length = 10)
    public String getSecurityManagerNo() {
        return securityManagerNo;
    }

    public void setSecurityManagerNo(String securityManagerNo) {
        this.securityManagerNo = securityManagerNo;
    }

    @Basic
    @Column(name = "special_operator_no", nullable = true, length = 10)
    public String getSpecialOperatorNo() {
        return specialOperatorNo;
    }

    public void setSpecialOperatorNo(String specialOperatorNo) {
        this.specialOperatorNo = specialOperatorNo;
    }

    @Basic
    @Column(name = "safety_production_permit_dm", nullable = true, length = 10)
    public String getSafetyProductionPermitDm() {
        return safetyProductionPermitDm;
    }

    public void setSafetyProductionPermitDm(String safetyProductionPermitDm) {
        this.safetyProductionPermitDm = safetyProductionPermitDm;
    }

    @Basic
    @Column(name = "safety_production_permit", nullable = true, length = 100)
    public String getSafetyProductionPermit() {
        return safetyProductionPermit;
    }

    public void setSafetyProductionPermit(String safetyProductionPermit) {
        this.safetyProductionPermit = safetyProductionPermit;
    }

    @Basic
    @Column(name = "safety_production_permit_no", nullable = true, length = 100)
    public String getSafetyProductionPermitNo() {
        return safetyProductionPermitNo;
    }

    public void setSafetyProductionPermitNo(String safetyProductionPermitNo) {
        this.safetyProductionPermitNo = safetyProductionPermitNo;
    }

    @Basic
    @Column(name = "safety_production_permit_begin", nullable = true)
    public Timestamp getSafetyProductionPermitBegin() {
        return safetyProductionPermitBegin;
    }

    public void setSafetyProductionPermitBegin(Timestamp safetyProductionPermitBegin) {
        this.safetyProductionPermitBegin = safetyProductionPermitBegin;
    }

    @Basic
    @Column(name = "safety_production_permit_end", nullable = true)
    public Timestamp getSafetyProductionPermitEnd() {
        return safetyProductionPermitEnd;
    }

    public void setSafetyProductionPermitEnd(Timestamp safetyProductionPermitEnd) {
        this.safetyProductionPermitEnd = safetyProductionPermitEnd;
    }

    @Basic
    @Column(name = "standardization_dm", nullable = true, length = 10)
    public String getStandardizationDm() {
        return standardizationDm;
    }

    public void setStandardizationDm(String standardizationDm) {
        this.standardizationDm = standardizationDm;
    }

    @Basic
    @Column(name = "standardization", nullable = true, length = 100)
    public String getStandardization() {
        return standardization;
    }

    public void setStandardization(String standardization) {
        this.standardization = standardization;
    }

    @Basic
    @Column(name = "standardization_card_no", nullable = true, length = 100)
    public String getStandardizationCardNo() {
        return standardizationCardNo;
    }

    public void setStandardizationCardNo(String standardizationCardNo) {
        this.standardizationCardNo = standardizationCardNo;
    }

    @Basic
    @Column(name = "standardization_card_date", nullable = true)
    public Timestamp getStandardizationCardDate() {
        return standardizationCardDate;
    }

    public void setStandardizationCardDate(Timestamp standardizationCardDate) {
        this.standardizationCardDate = standardizationCardDate;
    }

    @Basic
    @Column(name = "record_dm", nullable = true, length = 255)
    public String getRecordDm() {
        return recordDm;
    }

    public void setRecordDm(String recordDm) {
        this.recordDm = recordDm;
    }

    @Basic
    @Column(name = "record", nullable = true, length = 255)
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Basic
    @Column(name = "record_no", nullable = true, length = 255)
    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    @Basic
    @Column(name = "record_date", nullable = true)
    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    @Basic
    @Column(name = "declare_dm", nullable = true, length = 255)
    public String getDeclareDm() {
        return declareDm;
    }

    public void setDeclareDm(String declareDm) {
        this.declareDm = declareDm;
    }

    @Basic
    @Column(name = "declare_mc", nullable = true, length = 255)
    public String getDeclareMc() {
        return declareMc;
    }

    public void setDeclareMc(String declareMc) {
        this.declareMc = declareMc;
    }

    @Basic
    @Column(name = "declare_no", nullable = true, length = 255)
    public String getDeclareNo() {
        return declareNo;
    }

    public void setDeclareNo(String declareNo) {
        this.declareNo = declareNo;
    }

    @Basic
    @Column(name = "declare_number", nullable = true, length = 255)
    public String getDeclareNumber() {
        return declareNumber;
    }

    public void setDeclareNumber(String declareNumber) {
        this.declareNumber = declareNumber;
    }

    @Basic
    @Column(name = "timestamp", nullable = true, length = 40)
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "imgs", nullable = true, length = -1)
    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @Basic
    @Column(name = "location_x", nullable = true, length = 255)
    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    @Basic
    @Column(name = "location_y", nullable = true, length = 255)
    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
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
    @Column(name = "officeid", nullable = true, length = 64)
    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TBasicInformationEntity that = (TBasicInformationEntity) o;
        return id == that.id &&
                Objects.equals(enterpriseName, that.enterpriseName) &&
                Objects.equals(areaDm, that.areaDm) &&
                Objects.equals(area, that.area) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(productionAddress, that.productionAddress) &&
                Objects.equals(areaPropertyDm, that.areaPropertyDm) &&
                Objects.equals(areaProperty, that.areaProperty) &&
                Objects.equals(legalRepresentative, that.legalRepresentative) &&
                Objects.equals(legalRepresentativePhone, that.legalRepresentativePhone) &&
                Objects.equals(securityOfficer, that.securityOfficer) &&
                Objects.equals(securityOfficerPhone, that.securityOfficerPhone) &&
                Objects.equals(establishDate, that.establishDate) &&
                Objects.equals(registeredCapital, that.registeredCapital) &&
                Objects.equals(economicTypeDm, that.economicTypeDm) &&
                Objects.equals(economicType, that.economicType) &&
                Objects.equals(operationPeriodBegin, that.operationPeriodBegin) &&
                Objects.equals(operationPeriodEnd, that.operationPeriodEnd) &&
                Objects.equals(scopeOfOperation, that.scopeOfOperation) &&
                Objects.equals(industryNatureDm, that.industryNatureDm) &&
                Objects.equals(industryNature, that.industryNature) &&
                Objects.equals(enterpriseScaleDm, that.enterpriseScaleDm) &&
                Objects.equals(enterpriseScale, that.enterpriseScale) &&
                Objects.equals(coveringArea, that.coveringArea) &&
                Objects.equals(mainMaterial, that.mainMaterial) &&
                Objects.equals(mainProduct, that.mainProduct) &&
                Objects.equals(numberOfStaff, that.numberOfStaff) &&
                Objects.equals(annualRevenue, that.annualRevenue) &&
                Objects.equals(safetyManagementDm, that.safetyManagementDm) &&
                Objects.equals(safetyManagement, that.safetyManagement) &&
                Objects.equals(productionEquipmentNo, that.productionEquipmentNo) &&
                Objects.equals(securityManagerNo, that.securityManagerNo) &&
                Objects.equals(specialOperatorNo, that.specialOperatorNo) &&
                Objects.equals(safetyProductionPermitDm, that.safetyProductionPermitDm) &&
                Objects.equals(safetyProductionPermit, that.safetyProductionPermit) &&
                Objects.equals(safetyProductionPermitNo, that.safetyProductionPermitNo) &&
                Objects.equals(safetyProductionPermitBegin, that.safetyProductionPermitBegin) &&
                Objects.equals(safetyProductionPermitEnd, that.safetyProductionPermitEnd) &&
                Objects.equals(standardizationDm, that.standardizationDm) &&
                Objects.equals(standardization, that.standardization) &&
                Objects.equals(standardizationCardNo, that.standardizationCardNo) &&
                Objects.equals(standardizationCardDate, that.standardizationCardDate) &&
                Objects.equals(recordDm, that.recordDm) &&
                Objects.equals(record, that.record) &&
                Objects.equals(recordNo, that.recordNo) &&
                Objects.equals(recordDate, that.recordDate) &&
                Objects.equals(declareDm, that.declareDm) &&
                Objects.equals(declareMc, that.declareMc) &&
                Objects.equals(declareNo, that.declareNo) &&
                Objects.equals(declareNumber, that.declareNumber) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(imgs, that.imgs) &&
                Objects.equals(locationX, that.locationX) &&
                Objects.equals(locationY, that.locationY) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(officeid, that.officeid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, enterpriseName, areaDm, area, postCode, productionAddress, areaPropertyDm, areaProperty, legalRepresentative, legalRepresentativePhone, securityOfficer, securityOfficerPhone, establishDate, registeredCapital, economicTypeDm, economicType, operationPeriodBegin, operationPeriodEnd, scopeOfOperation, industryNatureDm, industryNature, enterpriseScaleDm, enterpriseScale, coveringArea, mainMaterial, mainProduct, numberOfStaff, annualRevenue, safetyManagementDm, safetyManagement, productionEquipmentNo, securityManagerNo, specialOperatorNo, safetyProductionPermitDm, safetyProductionPermit, safetyProductionPermitNo, safetyProductionPermitBegin, safetyProductionPermitEnd, standardizationDm, standardization, standardizationCardNo, standardizationCardDate, recordDm, record, recordNo, recordDate, declareDm, declareMc, declareNo, declareNumber, timestamp, imgs, locationX, locationY, remarks, createBy, createDate, updateBy, updateDate, delFlag, officeid);
    }
}
