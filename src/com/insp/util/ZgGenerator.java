package com.insp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;

public class ZgGenerator {
	private InputStream inFile; // 输入文件
	public static final Map<String, LocationModel> LOC_MAP = new HashMap<String, LocationModel>(); // 位置描述文件
	private static int t_l, t_max, x_l;
	
	static {
		Properties props = new Properties();
		try {
			props.load(ZgGenerator.class.getResourceAsStream("./ZgModelLocDesc.properties"));
			Enumeration<Object> iter = props.keys();
			while (iter.hasMoreElements()) {
				String key = (String) iter.nextElement();
				String val = props.getProperty(key);
				LOC_MAP.put(key, new LocationModel(val));
			}
			props.load(SignImageHelper.class.getResourceAsStream("./Zgfcyj.properties"));
			t_l = Integer.parseInt(props.getProperty("t_l"));
			t_max = Integer.parseInt(props.getProperty("t_max"));
			x_l = Integer.parseInt(props.getProperty("x_l"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ZgGenerator() {
		super();
		this.inFile = ZgGenerator.class.getResourceAsStream("./zgsrc.jpg");
	}

	public void write(ZgModel model, File outFile) throws IOException {
		BufferedImage image = ImageIO.read(inFile);
		Graphics g = image.getGraphics();

		g.setColor(Color.BLACK); // 写入内容
		g.setFont(new Font("宋体", Font.BOLD, 18));

		Field[] fields = ZgModel.class.getDeclaredFields();
		for (Field f : fields) {
			String key = f.getName();
			try {
				Object valObj = f.get(model);
				LocationModel locDesc = LOC_MAP.get(key); // 显示位置定义
				int i = 0;
				switch (key) {
				// 被检查人员签名
				case "checkeeSign":
					BufferedImage img = (BufferedImage) valObj;
					g.drawImage(img, locDesc.getX(), locDesc.getY(), img.getWidth(), img.getHeight(), null);
					break;
				// 检查人员证号
				case "checkerId":
					i = 0;
					String vals[] = (String[]) valObj;
					for (String q : vals) {
						g.drawString(q, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval()); // 绘制位置
						i++;
					}
					break;
				// 检查人员名称
				case "checkerName":
					i = 0;
					BufferedImage[] names = (BufferedImage[]) valObj;
					for (BufferedImage nameImg : names) {
						g.drawImage(nameImg, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval(),
								nameImg.getWidth(), nameImg.getHeight(), null);
						i++;
					}
					break;
				//复查意见fcyj
				case "fcyj":
					String defVals = String.valueOf(valObj); // 待显示的字符串
					double len = defVals.length();
					t_l = 45;// 第一行字符长度
					t_max = 45;// 其他行字符长度
					int d = (int) Math.ceil((len - t_l) / t_max);//分的行数

					// 完成第一行文字绘制
					int lastInd = Math.min((int) len, t_l); // 求出第一行最后一个字符的索引
					String childStr1 = defVals.substring(0, lastInd);
					g.drawString(childStr1, locDesc.getX(), locDesc.getY());
					// 完成后续行绘制
					for (int j = 1; j <= d; j++) {
						if (d > 1) {
							if (j == d) {
								String childStr = defVals.substring((j - 1) * t_max + t_l, (int) len);
								g.drawString(childStr, locDesc.getX(),
										j * locDesc.getInterval() + locDesc.getY());
							} else {
								String childStr = defVals.substring((j - 1) * t_max + t_l, t_l + j * t_max);
								g.drawString(childStr, locDesc.getX(),
										j * locDesc.getInterval() + locDesc.getY());
							}
						} else if (d == 1) {
							String childStr = defVals.substring((j - 1) * t_max + t_l, (int) len);
							g.drawString(childStr, locDesc.getX(), j * locDesc.getInterval() + locDesc.getY());
						}
					}
					break;
				default:
					String defVal = String.valueOf(valObj); // 待显示的字符串
					g.drawString(defVal, locDesc.getX(), locDesc.getY()); // 绘制位置
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		g.dispose();
		ImageIO.write(image, "jpg", outFile);
	}

	public static void main(String[] args) throws IOException, WriterException {
		ZgModel model = new ZgModel();

		// 处理普通字符串
		model.setTitle1("上海");
		model.setTitle2("1205");
		model.setBranchName("上海巴斯夫化工集团股份有限公司");
		model.setYear("2018");
		model.setMonth("12");
		model.setDay("25");
		model.setWsDecision("关于整改计划");
		model.setWsAjh("XXXX");
		model.setTitle11(model.getTitle1());
		model.setTitle22(model.getTitle2());
		model.setWsKhq("22");
		model.setFcyj("东方日哦喷怕熱偶發的的時刻了男方那接受噴水電機富婆時代峻峰時代峻峰 是的批發價噴水電機富婆時代峻峰時代峻均分东方日哦喷怕熱偶發的的時刻了男方那接受噴水電機富婆時代峻峰時代峻峰 是的批發價噴水電機富婆時代峻峰時代峻峰江蘇的披薩店見風使舵披肩發蘇東坡福建省的平均分东方日哦喷怕熱偶發的的時刻了男方那接受噴水電機富婆時代峻峰時代峻峰 是的批發價噴水電機富婆時代峻峰時代峻峰江蘇的披薩店見風使舵披肩發蘇東坡福建省的平均分");
		model.setCurYear("2018");
		model.setCurMonth("3");
		model.setCurDay("22");

		// 证号
		String[] checkerId = new String[] { "41130319891153375", "41130319891153375" };
		model.setCheckerId(checkerId);

		// 检查人员签名（图片）
		SignImageHelper helperA = new SignImageHelper(new File("sign\\a.png"));
		SignImageHelper helperB = new SignImageHelper(new File("sign\\b.png"));

		BufferedImage[] checkerName = new BufferedImage[] { helperA.getSign(), helperB.getSign() };
		model.setCheckerName(checkerName);
		model.setCheckeeSign(helperA.getSign());

		File result = new File("sign\\resultZG.jpg");
		ZgGenerator zggenerator = new ZgGenerator();
		zggenerator.write(model, result);
		System.out.println("执行完毕");
	}
}
