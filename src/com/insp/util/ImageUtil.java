package com.insp.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import sun.misc.BASE64Decoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import com.insp.dao.*;

public class ImageUtil {
    public static final String host = "http://47.98.152.18:18080/";
 /* public static final String host = "http://localhost:8088/";*/
    public static final String imgFloder = "pic_file/";

    public String saveSignImg(String dataUrl){
        String fileName = UUID.randomUUID() + ".png";
        String filePath = getImgPath() + fileName;  //输出
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }

        dataUrl = dataUrl.substring(dataUrl.indexOf(",") + 1);
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(dataUrl);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            out = new FileOutputStream(file);
            out.write(b);
            out.flush();
        } catch (Exception e){
            System.out.println("解码出错！");
            e.printStackTrace();
        } finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //暂时对签名文件不作处理,后期需要把其合成到
//        handlerImage(filePath);

        return imgFloder + fileName;
    }

    private static String getImgPath(){
        String picPath = null;

        //获取Tomcat服务器所在的路径
        String tomcat_path = System.getProperty( "user.dir" );
        //获取Tomcat服务器所在路径的最后一个文件目录
        String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());
        //若最后一个文件目录为bin目录，则服务器为手动启动
        if(("bin").equals(bin_path)){
            //获取保存上传图片的文件路径
            picPath = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\webapps"+"\\" + imgFloder;
        }else{//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5
            picPath = tomcat_path+"\\webapps"+"\\" + imgFloder;
        }

        File file = new File(picPath);
        if (!file.exists()){
            file.mkdir();
        }

        return picPath;
    }

    //创建责令整改文书
    public String createZlzgImg(JSONObject jsonObject) throws IOException {
        String fileName = UUID.randomUUID() + ".jpg";
        File result = new File(getImgPath() + fileName);
        Generator generator = new Generator();

        Calendar cal = Calendar.getInstance();
        ParamModel model = new ParamModel();

        //首先构建未通过的检查项
        String[] questions = new String[6];
        String[] resultNum = jsonObject.getString("jcqk").split(",");
        String[] resultValue = jsonObject.getString("jcjhid").split("-");
        if (resultNum.length != resultValue.length){
            return null;
        }

        for (int i=0; i<resultNum.length && i<questions.length; i++){
            if ("0".equals(resultNum[i])){
                questions[i] = resultValue[i];
            }
        }
    try {
        model.setQrCode(QrCodeUtil.createQrCode(host + imgFloder + fileName));
    } catch (WriterException e) {
        e.printStackTrace();
    }
        model.setQuestions(questions);
        model.setTitle1("上海");
        model.setTitle2(jsonObject.getString("wsbh"));
        model.setBranchName(jsonObject.getString("bjxqy"));

        String[] time = jsonObject.getString("time").split("-");
        if (time.length >= 3){
            model.setYear(time[0]);
            model.setMonth(time[1]);
            model.setDay(time[2]);
        }
        model.setPhone(jsonObject.getString("qyfzrlxdh"));
        model.setCurYear(cal.get(cal.YEAR) + "");
        model.setCurMonth((cal.get(cal.MONTH) + 1) + "");
        model.setCurDay(cal.get(cal.DATE) + "");
        model.setCheckerId(new String[] {jsonObject.getString("zfry1Zjhm"), jsonObject.getString("zfry2Zjhm")});

        SignImageHelper helperA = new SignImageHelper(new File(getImgPath() + jsonObject.getString("jcry1Pic").split("/")[1]));
        SignImageHelper helperB = new SignImageHelper(new File(getImgPath() + jsonObject.getString("jcry2Pic").split("/")[1]));
        SignImageHelper helperC = new SignImageHelper(new File(getImgPath() + jsonObject.getString("bjcdwPic").split("/")[1]));
        BufferedImage[] checkerName = new BufferedImage[] { helperA.getSign(), helperB.getSign() };

        model.setCheckerName(checkerName);
        model.setCheckeeSign(helperC.getSign());
        generator.write(model, result);

        return imgFloder + fileName;
    }

    //创建复查意见书
    public String createFcImg(TYdjdXcfxEntity xcfxEntity){
        String fileName = UUID.randomUUID() + ".jpg";
        File result = new File(getImgPath() + fileName);
        ZgGenerator zggenerator = new ZgGenerator();

        Date startDate = new Date(xcfxEntity.getCreateDate().getTime());
        Date endDate = new Date(xcfxEntity.getCreateDate().getTime() + 7*24*60*60*1000);
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        ZgModel model = new ZgModel();
        String jg = "";
        if ("1".equals(xcfxEntity.getIsfc())){
            jg = "复查当日，你单位对上述责令限期整改指令书中提出的问题已全部整改完毕，复查合格。";
        } else {
            jg = "复查当日，你单位对上述责令限期整改指令书中提出的问题未整改完毕，复查不合格。";
        }

        model.setTitle1("奉贤");
        model.setTitle2("001");
        model.setBranchName(xcfxEntity.getBjxqy());
        model.setYear(cal.get(cal.YEAR) + "");
        model.setMonth((cal.get(cal.MONTH) + 1) + "");
        model.setDay(cal.get(cal.DATE) + "");
        cal.setTime(endDate);
        model.setWsDecision("责令你单位于" + cal.get(cal.YEAR) + "年" + (cal.get(cal.MONTH) + 1) + "月" + cal.get(cal.DATE) + "日" + "前整改完毕");
        model.setWsAjh(cal.get(cal.YEAR) + "");
        model.setTitle11(model.getTitle1());
        model.setTitle22(model.getTitle2());
        model.setWsKhq("001");
        model.setFcyj(jg);
        cal.setTime(nowDate);
        model.setCurYear(cal.get(cal.YEAR) + "");
        model.setCurMonth((cal.get(cal.MONTH) + 1) + "");
        model.setCurDay(cal.get(cal.DATE) + "");

        String[] checkerId = new String[] { xcfxEntity.getZfry1Zjhm(), xcfxEntity.getZfry2Zjhm() };
        model.setCheckerId(checkerId);
        SignImageHelper helperA = new SignImageHelper(new File(getImgPath() + xcfxEntity.getJcry1Pic().split("/")[1]));
        SignImageHelper helperB = new SignImageHelper(new File(getImgPath() + xcfxEntity.getJcry2Pic().split("/")[1]));
        SignImageHelper helperC = new SignImageHelper(new File(getImgPath() + xcfxEntity.getBjcdwPic().split("/")[1]));

        try {
            BufferedImage[] checkerName = new BufferedImage[] { helperA.getSign(), helperB.getSign() };
            model.setCheckerName(checkerName);
            model.setCheckeeSign(helperC.getSign());
            zggenerator.write(model, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgFloder + fileName;
    }
    public String getUUIDString() throws Exception{
        UUID uuid = UUID.randomUUID();
        String uuidL = uuid.toString().replace("\\-","");
        return  uuidL;
    }

    public String createResultImg(TYdjdXcjxEntity xcjxEntity, String[] jcxList, int flag,int flagL){
        String fileName = UUID.randomUUID() + ".jpg";
        File result = new File(getImgPath() + fileName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = sdf.parse(xcjxEntity.getJckssj());
            endDate = sdf.parse(xcjxEntity.getJcjssj());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JcModel model = new JcModel();
       /* try {
            model.setQrCode(QrCodeUtil.createQrCode(host + imgFloder + fileName));
        } catch (WriterException e) {
            e.printStackTrace();
        }*/
        model.setFxq(xcjxEntity.getJcdq());
        model.setNyear(xcjxEntity.getJcaj());
        model.setSequence(xcjxEntity.getJch());
        model.setDwmc(xcjxEntity.getBjxqy());
        model.setDwdz(xcjxEntity.getQydz());
        model.setFddbr(xcjxEntity.getQyfzr());
        model.setZw(xcjxEntity.getQyfzrzw());
        model.setDh(xcjxEntity.getQyfzrlxdh());
        model.setJccs(xcjxEntity.getJccs());
        cal.setTime(startDate);
        model.setYear(cal.get(cal.YEAR) + "");
        model.setMonth((cal.get(cal.MONTH)+1) + "");
        model.setsDay(cal.get(cal.DATE) + "");
        model.setsHour(cal.get(cal.HOUR) + "");
        model.setsMinute(cal.get(cal.MINUTE) + "");
        cal.setTime(endDate);
        model.seteMonth(cal.get(cal.MONTH) + "");
        model.seteDay(cal.get(cal.DATE) + "");
        model.seteHour(cal.get(cal.HOUR) + "");
        model.seteMinute(cal.get(cal.MINUTE) + "");
        model.setWname(xcjxEntity.getJgjz());
        model.setZfry1(xcjxEntity.getZfry1());
        model.setZfry2(xcjxEntity.getZfry2());
        model.setZjhm1(xcjxEntity.getZfry1Zjhm());
        model.setZjhm2(xcjxEntity.getZfry2Zjhm());
        String[] jcqkArray = new String[6];
        for (int i=0;i<jcxList.length; i++) {
                jcqkArray[i] = jcxList[i];
        }
        model.setJcqkArray(jcqkArray);

            try {
                if(flagL==0) {
                    model.setQrCode(QrCodeUtil.createQrCode(host + imgFloder + fileName));
                } else{
                    model.setQrCode(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }

       /* StringBuilder sb = new StringBuilder();
        for (Object o : jcxList){
            JSONObject jsonObject = (JSONObject) o;

            sb.append(jsonObject.getString("jcx"));
            sb.append(":");
            if (jsonObject.getBoolean("isActive")){
                sb.append("通过");
            } else {
                sb.append("不通过");
            }

            sb.append("  ");
        }
        model.setJcqk(sb.toString());*/

        try {
            SignImageHelper helperA = new SignImageHelper(new File(getImgPath() + xcjxEntity.getJcry1Pic().split("/")[1]));
            SignImageHelper helperB = new SignImageHelper(new File(getImgPath() + xcjxEntity.getJcry2Pic().split("/")[1]));
            SignImageHelper helperC = new SignImageHelper(new File(getImgPath() + xcjxEntity.getBjcdwPic().split("/")[1]));
            model.setJcrName1(helperA.getSign());
            model.setJcrName2(helperB.getSign());
            model.setFzrName(helperC.getSign());

            cal.setTime(new Date());
            model.setT_year(cal.get(cal.YEAR) + "");
            model.setT_mouth((cal.get(cal.MONTH)+1) + "");
            model.setT_day(cal.get(cal.DATE) + "");
            model.setCount("1");
            model.setNo("1");

            JcGenerator generator = new JcGenerator(flag);
            generator.write(model, result,flagL);
        } catch (Exception e){
            e.printStackTrace();
        }

        return imgFloder + fileName;
    }
}
