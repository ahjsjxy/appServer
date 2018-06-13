package com.insp.util;

import java.awt.image.BufferedImage;

public class JcModel {
	/**
	 * 地区，当前年，号
	 */

	public String fxq;
	public String nyear;
	public String sequence;

	public String getFxq() {
		return fxq;
	}

	public void setFxq(String fxq) {
		this.fxq = fxq;
	}

	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * 单位名称、单位地址
	 */
	public String dwmc,dwdz;
	
	/**
	 * 法定代表人、职务、电话
	 */
	public String fddbr,zw,dh;
	
	/**
	 * 检查场所
	 */
	public String jccs;
	
	/**
	 * 检查时间、年、月、日、时、分
	 */
	public String Year, Month, sDay,sHour,sMinute,eMonth,eDay,eHour,eMinute;
	/**
	 * 我们是
	 */
	public String wname;



	/**
	 * 执法人员
	 */
	public String zfry1;
	public String zfry2;
	/**
	 * 证件号码
	 */
	public String zjhm1;
	public String zjhm2;
	/**
	 * 检查情况
	 */
	public String[] jcqkArray = new String[6];
	
	/**
	 * 检查人员签名
	 */
	public BufferedImage jcrName1;
	public BufferedImage jcrName2;
	
	/**
	 * 负责人签名
	 */
	
	public BufferedImage fzrName;
	/**
	 * 年月日
	 * @return
	 */
	public String t_year,t_mouth,t_day;
	
	/**
	 * 共多少页，第几页
	 * @return
	 */
	public String count,no;
	/**
	 * 二维码
	 */
	public BufferedImage qrCode;
	public BufferedImage getQrCode() {
		return qrCode;
	}

	public void setQrCode(BufferedImage qrCode) {
		this.qrCode = qrCode;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getFddbr() {
		return fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getJccs() {
		return jccs;
	}

	public void setJccs(String jccs) {
		this.jccs = jccs;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getsDay() {
		return sDay;
	}

	public void setsDay(String sDay) {
		this.sDay = sDay;
	}

	public String getsHour() {
		return sHour;
	}

	public void setsHour(String sHour) {
		this.sHour = sHour;
	}

	public String getsMinute() {
		return sMinute;
	}

	public void setsMinute(String sMinute) {
		this.sMinute = sMinute;
	}
	public String geteMonth() {
		return eMonth;
	}

	public void seteMonth(String eMonth) {
		this.eMonth = eMonth;
	}
	public String geteDay() {
		return eDay;
	}

	public void seteDay(String eDay) {
		this.eDay = eDay;
	}

	public String geteHour() {
		return eHour;
	}

	public void seteHour(String eHour) {
		this.eHour = eHour;
	}

	public String geteMinute() {
		return eMinute;
	}

	public void seteMinute(String eMinute) {
		this.eMinute = eMinute;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}


	public String getZfry1() {
		return zfry1;
	}

	public void setZfry1(String zfry1) {
		this.zfry1 = zfry1;
	}

	public String getZfry2() {
		return zfry2;
	}

	public void setZfry2(String zfry2) {
		this.zfry2 = zfry2;
	}

	public String getZjhm1() {
		return zjhm1;
	}

	public void setZjhm1(String zjhm1) {
		this.zjhm1 = zjhm1;
	}

	public String getZjhm2() {
		return zjhm2;
	}

	public void setZjhm2(String zjhm2) {
		this.zjhm2 = zjhm2;
	}

	public BufferedImage getJcrName1() {
		return jcrName1;
	}

	public void setJcrName1(BufferedImage jcrName1) {
		this.jcrName1 = jcrName1;
	}

	public BufferedImage getJcrName2() {
		return jcrName2;
	}

	public void setJcrName2(BufferedImage jcrName2) {
		this.jcrName2 = jcrName2;
	}

	public BufferedImage getFzrName() {
		return fzrName;
	}

	public void setFzrName(BufferedImage fzrName) {
		this.fzrName = fzrName;
	}

	public String getT_year() {
		return t_year;
	}

	public void setT_year(String t_year) {
		this.t_year = t_year;
	}

	public String getT_mouth() {
		return t_mouth;
	}

	public void setT_mouth(String t_mouth) {
		this.t_mouth = t_mouth;
	}

	public String getT_day() {
		return t_day;
	}

	public void setT_day(String t_day) {
		this.t_day = t_day;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String[] getJcqkArray() {
		return jcqkArray;
	}

	public void setJcqkArray(String[] jcqkArray) {
		this.jcqkArray = jcqkArray;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}
