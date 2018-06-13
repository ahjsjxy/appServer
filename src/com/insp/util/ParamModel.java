package com.insp.util;

import java.awt.image.BufferedImage;

/**
 * 参数对象
 */
public class ParamModel {
	/**
	 * 标题1,2
	 */
	public String title1, title2;

	/**
	 * 单位名称
	 */
	public String branchName;

	/**
	 * 至多6条记录
	 */
	public String questions[] = new String[6];

	/**
	 * 于年,月,日之前完成整改
	 */
	public String year, month, day;

	/**
	 * 检查人员名称, 证件号码 注意两数组中顺序对应
	 */
	public BufferedImage checkerName[];
	public String checkerId[];

	/**
	 * 被检查人员签名
	 */
	public BufferedImage checkeeSign;
	/**
	 * 二维码
	 */
	public BufferedImage qrCode;

	/**
	 * 联系电话
	 */
	public String phone;

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

	public String[] getQuestions() {
		return questions;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
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

	public String[] getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String[] checkerId) {
		this.checkerId = checkerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public BufferedImage[] getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(BufferedImage[] checkerName) {
		this.checkerName = checkerName;
	}

	public BufferedImage getCheckeeSign() {
		return checkeeSign;
	}

	public void setCheckeeSign(BufferedImage checkeeSign) {
		this.checkeeSign = checkeeSign;
	}

	public BufferedImage getQrCode() {
		return qrCode;
	}

	public void setQrCode(BufferedImage qrCode) {
		this.qrCode = qrCode;
	}
	
}