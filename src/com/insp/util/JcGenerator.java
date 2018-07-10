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
import com.google.zxing.common.StringUtils;

public class JcGenerator {
    private InputStream inFile; // 输入文件
    public static final Map<String, LocationModel> LOC_MAP = new HashMap<String, LocationModel>(); // 位置描述文件


/*	static {
        Properties props = new Properties();
		try {
			props.load(JcGenerator.class.getResourceAsStream("./JcModelLocDesc.properties"));
			Enumeration<Object> iter = props.keys();
			while (iter.hasMoreElements()) {
				String key = (String) iter.nextElement();
				String val = props.getProperty(key);
				LOC_MAP.put(key, new LocationModel(val));
			}
			props.load(SignImageHelper.class.getResourceAsStream("./Jcjcqk.properties"));
			t_l = Integer.parseInt(props.getProperty("t_l"));
			t_max = Integer.parseInt(props.getProperty("t_max"));
			x_l = Integer.parseInt(props.getProperty("x_l"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

    public JcGenerator() {
        super();
        this.inFile = JcGenerator.class.getResourceAsStream("./jcsrc.jpg");
    }

    public JcGenerator(int i) {

        Properties props = new Properties();
        if (i == 0) {
            this.inFile = JcGenerator.class.getResourceAsStream("./jcsrc.jpg");
            try {
                props.load(JcGenerator.class.getResourceAsStream("./JcModelLocDesc.properties"));
                Enumeration<Object> iter = props.keys();
                while (iter.hasMoreElements()) {
                    String key = (String) iter.nextElement();
                    String val = props.getProperty(key);
                    LOC_MAP.put(key, new LocationModel(val));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.inFile = JcGenerator.class.getResourceAsStream("./wssrc.jpg");
            try {
                props.load(JcGenerator.class.getResourceAsStream("./WsModelLocDesc.properties"));
                Enumeration<Object> iter = props.keys();
                while (iter.hasMoreElements()) {
                    String key = (String) iter.nextElement();
                    String val = props.getProperty(key);
                    LOC_MAP.put(key, new LocationModel(val));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int t_l, t_max, x_l;
        try {
            props.load(SignImageHelper.class.getResourceAsStream("./Jcjcqk.properties"));
            t_l = Integer.parseInt(props.getProperty("t_l"));
            t_max = Integer.parseInt(props.getProperty("t_max"));
            x_l = Integer.parseInt(props.getProperty("x_l"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(JcModel model, File outFile, int flagL) throws IOException {
        BufferedImage image = ImageIO.read(inFile);
        Graphics g = image.getGraphics();

        g.setColor(Color.BLACK); // 写入内容
        g.setFont(new Font("宋体", Font.BOLD, 18));
        Field[] fields = JcModel.class.getDeclaredFields();
            for (Field f : fields) {
                String key = f.getName();
                try {
                    Object valObj = f.get(model);
                    LocationModel locDesc = LOC_MAP.get(key); // 显示位置定义
                    switch (key) {
                        case "jcrName1":
                            BufferedImage img = (BufferedImage) valObj;
                            g.drawImage(img, locDesc.getX(), locDesc.getY(), img.getWidth(), img.getHeight(), null);
                            break;
                        case "jcrName2":
                            BufferedImage imgs = (BufferedImage) valObj;
                            g.drawImage(imgs, locDesc.getX(), locDesc.getY(), imgs.getWidth(), imgs.getHeight(), null);
                            break;
                        case "fzrName":
                            BufferedImage _imgs = (BufferedImage) valObj;
                            g.drawImage(_imgs, locDesc.getX(), locDesc.getY(), _imgs.getWidth(), _imgs.getHeight(), null);
                            break;
                        case "qrCode":
                            BufferedImage _img = (BufferedImage) valObj;
                            if (_img == null) {
                                _img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
                            }
                            g.drawImage(_img, locDesc.getX(), locDesc.getY(), _img.getWidth(), _img.getHeight(), null);
                            break;
					/*case "jcqkArray":
						String vals[] = (String[]) valObj;
						int i=0;
						for (String q : vals) {
							g.drawString(q, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval()); // 绘制位置
							i++;
						}*/
                        case "jcqkArray":
                            int j = 1;
                            int i = 0;
                            String vals[] = (String[]) valObj;
                            for (String str : vals) {
                                if (!"".equals(str) && str != null) {
                                    String strL = str.substring(str.indexOf(".") + 1, str.length());
                                    if (j == 1) {
                                        g.drawString(j + "." + strL, locDesc.getX() + 155, locDesc.getY() - 54);
                                        j++;
                                    } else {
                                        g.drawString(j + "." + strL, locDesc.getX(), locDesc.getY() + i * locDesc.getInterval()); // 绘制位置
                                        j++;
                                        i++;
                                    }
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
}