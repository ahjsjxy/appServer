package com.insp.util;

import java.awt.image.BufferedImage;

/**
 * 安全生产行政执法文书
 */
public class ZgModel {
	/**
     * 标题title1,title2
     */
    public String title1,title2;

    /**
     * 单位名称
     */
    public String branchName;

    /**
     * 于年,月,日之前完成整改
     */
    public String year, month, day;

    /**
     * 决定
     */
    public String wsDecision;

    
    /**
     * 标题11
     */
    public String title11;
    
    /**
     * 安监后
     */
    public String wsAjh;

    /**
     * 标题22
     */
    public String title22;
    
    /**
     * 括号前
     */
    public String wsKhq;

    /**
     * 复查意见
     */
    public String fcyj;

    /**
     * 被复查人员签名
     */
    public BufferedImage checkeeSign;

    /**
     * 检查人员名称, 证件号码 注意两数组中顺序对应
     */
    public BufferedImage checkerName[];
    public String checkerId[];

    /**
     * 当前年月日
     */
    public String curYear, curMonth, curDay;

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWsDecision() {
		return wsDecision;
	}

	public void setWsDecision(String wsDecision) {
		this.wsDecision = wsDecision;
	}

	public String getTitle11() {
		return title11;
	}

	public void setTitle11(String title11) {
		this.title11 = title11;
	}

	public String getWsAjh() {
		return wsAjh;
	}

	public void setWsAjh(String wsAjh) {
		this.wsAjh = wsAjh;
	}

	public String getTitle22() {
		return title22;
	}

	public void setTitle22(String title22) {
		this.title22 = title22;
	}

	public String getWsKhq() {
		return wsKhq;
	}

	public void setWsKhq(String wsKhq) {
		this.wsKhq = wsKhq;
	}

	public String getFcyj() {
		return fcyj;
	}

	public void setFcyj(String fcyj) {
		this.fcyj = fcyj;
	}

	public BufferedImage getCheckeeSign() {
		return checkeeSign;
	}

	public void setCheckeeSign(BufferedImage checkeeSign) {
		this.checkeeSign = checkeeSign;
	}

	public BufferedImage[] getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(BufferedImage[] checkerName) {
		this.checkerName = checkerName;
	}

	public String[] getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String[] checkerId) {
		this.checkerId = checkerId;
	}

	public String getCurYear() {
		return curYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public String getCurMonth() {
		return curMonth;
	}

	public void setCurMonth(String curMonth) {
		this.curMonth = curMonth;
	}

	public String getCurDay() {
		return curDay;
	}

	public void setCurDay(String curDay) {
		this.curDay = curDay;
	}

    
}
