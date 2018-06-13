package com.insp.util;

public class LocationModel {
	private int x, y; // 图片中的位置
	private int max; // 最大字符数，目前未使用
	private int interval;// 多行间间隔

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public LocationModel(String line) {
		String[] tokens = line.split(",");
		if (tokens.length >= 3) {
			this.x = Integer.parseInt(tokens[0]);
			this.y = Integer.parseInt(tokens[1]);
			this.max = Integer.parseInt(tokens[2]);
		}

		if (tokens.length > 3) {
			this.interval = Integer.parseInt(tokens[3]);
		}
	}
}
