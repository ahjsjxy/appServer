package com.insp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

public class SignImageHelper {
	// 原始图片
	private BufferedImage image;
	private static int targetW, targetH;

	static {
		Properties props = new Properties();
		try {
			props.load(SignImageHelper.class.getResourceAsStream("./SignImageHelper.properties"));
			targetW = Integer.parseInt(props.getProperty("w"));
			targetH = Integer.parseInt(props.getProperty("h"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SignImageHelper(File src) {
		try {
			this.image = ImageIO.read(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得有字的区域,相对于整张图片的坐标如下<br>
	 * 
	 * @return 目标矩形
	 */
	public int[] getRectangle() {
		int xmin = 0, ymin = 0, xmax = 0, ymax = 0;
		boolean first = true;
		int w = image.getWidth(), h = image.getHeight();
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				int val = image.getRGB(i, j);
				if ((val & 0xff000000) >> 24 != 0) { // 获得透明度，0为全透明
					if (first) {
						xmin = i;
						xmax = i;

						ymin = j;
						ymax = j;
						first = false;
					} else {
						if (i < xmin) {
							xmin = i;
						} else if (i > xmax) {
							xmax = i;
						}

						if (j < ymin) {
							ymin = j;
						} else if (j > ymax) {
							ymax = j;
						}
					}
				}
			}
		}

		return new int[] { xmin, ymin, xmax, ymax }; // 获得不透明区域的外包裹矩形
	}

	/**
	 * 根据给定的Range切割图片
	 * 
	 * @throws IOException
	 */
	public BufferedImage scale(int[] rect, int width, int height) throws IOException {
		return this.scale(rect, width, height, null, null);
	}

	/**
	 * 根据给定的Range切割图片,调试用
	 * 
	 * @throws IOException
	 */
	public BufferedImage scale(int[] rect, int width, int height, String subPath, String scalePath) throws IOException {
		// 抠图
		int srcW = rect[2] - rect[0];
		int srcH = rect[3] - rect[1];
		BufferedImage subImage = image.getSubimage(rect[0], rect[1], srcW, srcH);
		if (subPath != null) {
			ImageIO.write(subImage, "jpg", new File(subPath));
		}

		// 计算最佳比例
		float wf = (float) width / (float) srcW;
		float hf = (float) height / (float) srcH;
		float f = Math.min(wf, hf);

		// 按照比例生成缩略图
		Builder<BufferedImage> builder = Thumbnails.of(subImage).scale(f);
		BufferedImage scaleImg = builder.asBufferedImage();
		if (scalePath != null) {
			ImageIO.write(scaleImg, "jpg", new File(scalePath));
		}

		return scaleImg;
	}

	public BufferedImage getSign() throws IOException {
		int[] rect = this.getRectangle();
		BufferedImage img = this.scale(rect, targetW, targetH);
		return img;
	}

	public static void main(String[] args) throws IOException {
		File src = new File("sign\\b.png");
		SignImageHelper helper = new SignImageHelper(src);
		int[] rect = helper.getRectangle();
		BufferedImage img = helper.scale(rect, targetW, targetH);
		ImageIO.write(img, "png", new File("sign\\b-sign-scale.png"));
		System.out.println("******************执行完毕*****************");
	}
}
