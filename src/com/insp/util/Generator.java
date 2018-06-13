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

public class Generator {
	private InputStream inFile; // 输入文件
	public static final Map<String, LocationModel> LOC_MAP = new HashMap<String, LocationModel>(); // 位置描述文件

	static {
		Properties props = new Properties();
		try {
			props.load(Generator.class.getResourceAsStream("./ParamModelLocDesc.properties"));
			Enumeration<Object> iter = props.keys();
			while (iter.hasMoreElements()) {
				String key = (String) iter.nextElement();
				String val = props.getProperty(key);
				LOC_MAP.put(key, new LocationModel(val));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Generator() {
		super();
		this.inFile = Generator.class.getResourceAsStream("./src.jpg");
	}

	public void write(ParamModel model, File outFile) throws IOException {
		BufferedImage image = ImageIO.read(inFile);
		Graphics g = image.getGraphics();

		g.setColor(Color.BLACK); // 写入内容
		g.setFont(new Font("宋体", Font.BOLD, 18));

		Field[] fields = ParamModel.class.getDeclaredFields();
		for (Field f : fields) {
			String key = f.getName();
			try {
				Object valObj = f.get(model);
				LocationModel locDesc = LOC_MAP.get(key); // 显示位置定义
				int i = 0;
				switch (key) {
				case "checkeeSign":
					BufferedImage img = (BufferedImage) valObj;
					g.drawImage(img, locDesc.getX(), locDesc.getY(), img.getWidth(), img.getHeight(), null);
					break;
				case "questions":
				case "checkerId":
					i = 0;
					String vals[] = (String[]) valObj;
					for (String q : vals) {
						if (q == null){
							continue;
						}
						g.drawString(q, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval()); // 绘制位置
						i++;
					}
					break;
				case "checkerName":
					i = 0;
					BufferedImage[] names = (BufferedImage[]) valObj;
					for (BufferedImage nameImg : names) {
						g.drawImage(nameImg, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval(),
								nameImg.getWidth(), nameImg.getHeight(), null);
						i++;
					}
					break;
				case "qrCode":
					BufferedImage _img = (BufferedImage) valObj;
					g.drawImage(_img, locDesc.getX(), locDesc.getY(), _img.getWidth(), _img.getHeight(), null);
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
		ParamModel model = new ParamModel();
		model.setQrCode(QrCodeUtil.createQrCode("www.baidu.com"));
		String[] questions = new String[] { "未按照要求建立应急灭火制度", "未按照要求建立应急灭火制度", "未按照要求建立应急灭火制度" };
		model.setQuestions(questions);
		model.setTitle1("上海");
		model.setTitle2("1205");
		model.setBranchName("上海巴斯夫化工集团股份有限公司");
		model.setYear("2018");
		model.setMonth("12");
		model.setDay("25");
		// model.setCheckeeSign("王宏达");
		model.setPhone("021-52833508");

		model.setCurYear("2018");
		model.setCurMonth("3");
		model.setCurDay("22");
		String[] checkerId = new String[] { "41130319891153375", "41130319891153375" };
		model.setCheckerId(checkerId);
		SignImageHelper helperA = new SignImageHelper(new File("sign\\a.png"));
		SignImageHelper helperB = new SignImageHelper(new File("sign\\b.png"));

		BufferedImage[] checkerName = new BufferedImage[] { helperA.getSign(), helperB.getSign() };
		model.setCheckerName(checkerName);
		model.setCheckeeSign(helperA.getSign());

		File result = new File("sign\\result.jpg");
		Generator generator = new Generator();
		generator.write(model, result);
		System.out.println("执行完毕");
	}
}