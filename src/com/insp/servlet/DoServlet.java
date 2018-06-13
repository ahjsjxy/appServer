package com.insp.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.insp.dao.*;
import com.insp.database.DataBaseManager;
import com.insp.util.ImageUtil;
import com.insp.util.PasswordUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        result.put("result", 0);
        JSONObject jsonObject = JSON.parseObject(getRequestStr(request));
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (username != null && password != null){
            String[] params = {username};
            String hql = "from SysUserEntity t where t.loginName = ?";
            List<SysUserEntity> list = DataBaseManager.query(hql, params);
            if (list.size() > 0){
                SysUserEntity user = list.get(0);
                if (PasswordUtil.validatePassword(password, user.getPassword())){  //比对用户名密码
                    JSONObject userObj = (JSONObject) JSON.toJSON(user);
                    String officeId = user.getOfficeId();

                    if (officeId != null && !"".equals(officeId) && !"10000".equals(officeId)) {
                        //查询该用户的单位具体信息
                        String officeHql = "from SysOfficeEntity t where t.id = '" + officeId + "'";
                        List<SysOfficeEntity> offices = DataBaseManager.query(officeHql, null);
                        if (offices.size() > 0) {
                            userObj.put("officeName", offices.get(0).getName());
                        }

                        //查询该用户同单位成员
                        String allUseHql = "from SysUserEntity t where t.officeId = '" + officeId + "'";
                        List<SysUserEntity> allUsers = DataBaseManager.query(allUseHql, null);
                        if (allUsers.size() > 0) {
                            userObj.put("allUsers", JSON.toJSONString(allUsers));
                        }
                    }
                    result.put("result", userObj.toJSONString());
                }
            }
        }
        response.getWriter().print(result.toString());
    }

    public void getJcx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TJcxEntity> list = new ArrayList<>();

        try {
            String jcjhId = getRequestStr(request);

            String hql = "from TJcxEntity t where (t.type is null or t.type = '') and jcjhid = " + jcjhId;
            list = DataBaseManager.query(hql, null);
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(JSON.toJSONString(list));
    }

    public void saveJcx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = -1;

        try {
            String jsonStr = getRequestStr(request);
            TJcxEntity tJcxEntity = JSON.parseObject(jsonStr, new TypeReference<TJcxEntity>() {});
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            tJcxEntity.setCjsj(sdf.format(new Date()));
            tJcxEntity.setType("1");  //表示是由客户端创建的检查项

            id = (int) DataBaseManager.add(tJcxEntity);
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(id);
    }

    //步骤:1.解析参数 2.保存现场检查 3.修改检查计划 4.生成结果图,将地址返回给客户端
    public void saveXcjc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String resultUrl = "";

        try {
            String jsonStr = getRequestStr(request);
            boolean isNeeFc = false;
            int qyid = -1;
            JSONObject jsonObject = JSON.parseObject(jsonStr);

            TYdjdXcjxEntity xcjxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcjxEntity>() {});
            TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});

            //是否需要插入公司信息表数据
            if (jsonObject.get("lat") != null && jsonObject.get("lon") != null){
                TBasicInformationEntity basicInformationEntity = new TBasicInformationEntity();
                basicInformationEntity.setLocationX(jsonObject.get("lon").toString());
                basicInformationEntity.setLocationY(jsonObject.get("lat").toString());
                basicInformationEntity.setEnterpriseName(xcjxEntity.getBjxqy());
                basicInformationEntity.setProductionAddress(xcjxEntity.getQydz());
                basicInformationEntity.setSecurityOfficer(xcjxEntity.getQyfzr());
                basicInformationEntity.setSecurityOfficerPhone(xcjxEntity.getQyfzrlxdh());
                basicInformationEntity.setArea(xcjxEntity.getJccs());
                basicInformationEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));

                qyid = (int) DataBaseManager.add(basicInformationEntity);
            }

            if (qyid > 0){
                xcjxEntity.setBjcqyid(String.valueOf(qyid));
                xcfxEntity.setBjcqyid(String.valueOf(qyid));
            }
            xcjxEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            xcfxEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
           //图片上传
            JSONArray uploadPics = (JSONArray) jsonObject.get("picArray");
            StringBuilder uploadStr = new StringBuilder();
            for(int i=0;i<uploadPics.size();i++){
                Object uploadPic = uploadPics.get(i);
                String uploadImgPath = imageUtil.saveSignImg(uploadPic.toString());
                uploadStr.append(uploadImgPath);
                if(i<uploadPics.size()-1){
                    uploadStr = uploadStr.append(",");
                }
            }
            xcjxEntity.setUploadPic(uploadStr.toString());
            xcfxEntity.setUploadPic(uploadStr.toString());
            //解析签名图片
            JSONArray signs = (JSONArray) jsonObject.get("signArray");
            for (int i=0; i<signs.size(); i++){
                Object sign = signs.get(i);

                switch (i){
                    case 0:
                        xcjxEntity.setJcry1Pic(imageUtil.saveSignImg(sign.toString()));
                        xcfxEntity.setJcry1Pic(imageUtil.saveSignImg(sign.toString()));
                        break;
                    case 1:
                        xcjxEntity.setJcry2Pic(imageUtil.saveSignImg(sign.toString()));
                        xcfxEntity.setJcry2Pic(imageUtil.saveSignImg(sign.toString()));
                        break;
                    case 2:
                        xcjxEntity.setBjcdwPic(imageUtil.saveSignImg(sign.toString()));
                        xcfxEntity.setBjcdwPic(imageUtil.saveSignImg(sign.toString()));
                        break;
                }
            }

            //解析检查项
            JSONArray jcxList = jsonObject.getJSONArray("jcxlist");
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Object jcx : jcxList){
                String name = ((JSONObject) jcx).getString("jcx");
                int value = ((JSONObject) jcx).getBoolean("isActive") ? 1 : 0;

                names.append(name + "-");
                values.append(value + ",");
            }

            if (names.length() > 0){
                xcjxEntity.setJcjhid(names.toString().substring(0, names.length()-1));
                xcfxEntity.setJcjhid(names.toString().substring(0, names.length()-1));
            }
            if (values.length() > 0){
                xcjxEntity.setJcqk(values.toString().substring(0, values.length()-1));
                xcfxEntity.setJcqk(values.toString().substring(0, values.length()-1));
            }
            if (values.toString().contains("0")){
                //此检查存在问题,需要插入数据进复查表
                xcjxEntity.setIsyh("1");
                isNeeFc = true;
            } else {
                xcjxEntity.setIsyh("0");
            }
            int flag = 0;
            if(xcjxEntity.getOfficeid().equals("100")){
                flag = 1;
            }

            xcjxEntity.setResultPic(imageUtil.createResultImg(xcjxEntity, jcxList,flag));
            xcjxEntity.setDelFlag("0");
            xcfxEntity.setDelFlag("0");
            resultUrl = xcjxEntity.getResultPic();
            if ((int) DataBaseManager.add(xcjxEntity) > 0){
                int id = jsonObject.getInteger("jcid");
                if (id > 0){
                    String undateHql = "update TJcjhEntity t set t.isClose = 1 where t.id = " + id;
                    DataBaseManager.update(undateHql, null);
                }
            }


            //插入复查数据
//            if (isNeeFc){
//                xcfxEntity.setResultPic(xcjxEntity.getResultPic());
//                DataBaseManager.add(xcfxEntity);
//            }
        } catch (Exception e){
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", resultUrl);
        response.getWriter().print(jsonObject.toString());
    }

    //增加移交管理记录
    public void saveYjgl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = getRequestStr(request);
        int result = 0;

        try {
            TYdjdShEntity shEntity = JSON.parseObject(str, new TypeReference<TYdjdShEntity>() {});
            TYdjdXcfxEntity xcfxEntity = JSON.parseObject(str, new TypeReference<TYdjdXcfxEntity>() {});


            shEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            shEntity.setDelFlag("0");
            shEntity.setId(0);
            DataBaseManager.add(shEntity);

            xcfxEntity.setYjzt("1");
            DataBaseManager.update(xcfxEntity);

            result = 1;
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(result);
    }

    //增加复查记录(在现场检查记录中通过责令整改操作)
    public void addXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String resultImagePath = null;
        try {
            String jsonStr = getRequestStr(request);
            JSONObject jsonObject = JSON.parseObject(jsonStr);

//            resultImagePath = imageUtil.createZlzgImg(jsonObject);
            resultImagePath = "1";
            if (resultImagePath != null){
                TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});
                TYdjdXcjxEntity xcjxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcjxEntity>() {});

                xcfxEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
                xcfxEntity.setDelFlag("0");
                xcfxEntity.setZlzgws(resultImagePath);
                xcfxEntity.setZlzgsbh(jsonObject.getString("wsbh"));
                xcfxEntity.setZlzgrq(jsonObject.getString("time"));
                xcfxEntity.setId(0);
                int fcid = (int) DataBaseManager.add(xcfxEntity);

                xcjxEntity.setZlzgws(resultImagePath);
                xcjxEntity.setZlzgsbh(jsonObject.getString("wsbh"));
                xcjxEntity.setZlzgrq(jsonObject.getString("time"));
                xcjxEntity.setIszlzg("1");
                xcjxEntity.setXcfcId(fcid);
                DataBaseManager.update(xcjxEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
            resultImagePath = "0";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", resultImagePath);
        response.getWriter().print(jsonObject.toString());
    }

    public void updateXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String result = "-1";
        try {
            String jsonStr = getRequestStr(request);
            TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});
            result = imageUtil.createFcImg(xcfxEntity);

            xcfxEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            xcfxEntity.setZlzgws(result);
            DataBaseManager.update(xcfxEntity);

            String hql = "from TYdjdXcjxEntity t where t.xcfcId = " + xcfxEntity.getId();
            List<TYdjdXcjxEntity> list = DataBaseManager.query(hql, null);
            if (list != null && list.size() > 0){
                TYdjdXcjxEntity xcjxEntity = list.get(0);
                xcjxEntity.setIsfc(xcfxEntity.getIsfc());
                DataBaseManager.update(xcjxEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().print(jsonObject);
    }

    public void getXcjc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONArray jsonArray = new JSONArray();  //需要返回给客户端的数据(将所有的检查计划都转换成检查记录)
      //  String officeId = request.getParameter("officeId");
        String jsonStr = getRequestStr(request);
        JSONObject jsonObjectL = JSON.parseObject(jsonStr);
        String officeId = jsonObjectL.getString("officeId");
        String jcdq = "";
        String str = "沪奉";
        String companyHql = "";
        String jcjhHql = "from TJcjhEntity t where t.isClose is null or t.isClose = ''";
        if (officeId.equals("100")) {
            jcdq = str;
            companyHql = "from TBasicInformationEntity";
        } else {
            String allAreaHql = "from SysOfficeEntity t where t.delFlag = '0' and t.grade = '2' and t.id = '"+officeId+"'";
            List<SysOfficeEntity> allAreas = DataBaseManager.query(allAreaHql, null);
            if (allAreas.size() > 0) {
                for (int i = 0; i < allAreas.size(); i++) {
                    SysOfficeEntity sysOfficeEntity = allAreas.get(i);
                    jcdq = str + sysOfficeEntity.getName();
                    Pattern p = Pattern.compile("镇$");
                    Matcher m = p.matcher(jcdq);
                    if(m.find()){
                        jcdq = jcdq.substring(0,jcdq.length()-1);
                    }
                }
            }
            companyHql = "from TBasicInformationEntity t where t.officeid = '"+officeId+"'";
        }

        try {
            List<TBasicInformationEntity> companys = DataBaseManager.query(companyHql, null);
            List<TJcjhEntity> jcjhs = DataBaseManager.query(jcjhHql, null);

            for (TJcjhEntity tJcjhEntity : jcjhs){
                for (TBasicInformationEntity company : companys){
                    if (tJcjhEntity.getJcqy().equals(String.valueOf(company.getId()))){
                        //将检查记录与公司信息组合成现场检查
                        TYdjdXcjxEntity xcjxEntity = new TYdjdXcjxEntity();
                        xcjxEntity.setBjxqy(company.getEnterpriseName());
                        xcjxEntity.setQydz(company.getProductionAddress());
                        xcjxEntity.setQyfzr(company.getSecurityOfficer());
                        xcjxEntity.setQyfzrlxdh(company.getSecurityOfficerPhone());
                        xcjxEntity.setQyfzrzw("      ");
                        xcjxEntity.setJccs(company.getArea());
                        xcjxEntity.setJckssj(tJcjhEntity.getJckssj());
                        xcjxEntity.setJcjssj(tJcjhEntity.getJcjssj());
                        xcjxEntity.setBjcqyid(company.getId() + "");

                        JSONObject jsonObject = (JSONObject) JSON.toJSON(xcjxEntity);
                        jsonObject.put("jcid", tJcjhEntity.getId());
                        jsonObject.put("zxdw", "    ");  //执行单位
                        jsonObject.put("zxr", "    ");   //执行人
                        jsonObject.put("jcjhid", tJcjhEntity.getId());
                        jsonObject.remove("id");    //去除无用的id字段
                        jsonObject.put("jcdq",jcdq);
                        jsonArray.add(jsonObject);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(jsonArray.toString());
    }

    //根据id获取公司信息
    public void getCompanyById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getRequestStr(request);
        String hql = "from TBasicInformationEntity t where t.id = '" + id + "'";
        Object o = DataBaseManager.query(hql, null);

        response.getWriter().print(JSON.toJSONString(o));
    }

    //获取消息对象,目前只有一个检查计划
    public void getMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Message> messages = new ArrayList<>();

        String hql = "from TJcjhEntity t where t.isClose is null or t.isClose = ''";
        List<TJcjhEntity> jcjhs;

        try {
            String time = getRequestStr(request);

            jcjhs = DataBaseManager.query(hql, null);
            Message message = new Message();
            message.setType("检查计划");
            if (jcjhs != null && jcjhs.size() > 0){
                message.setNum(jcjhs.size());
                message.setIntro(jcjhs.get(0).getCjr() + "发布新的检查计划");
            }

            messages.add(message);
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(JSON.toJSONString(messages));
    }

    //这里前端需要涉及到多次重组渲染,去除id
    public void getJcjl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hql = "from TYdjdXcjxEntity t where t.delFlag = 0 order by t.createDate desc";
        List<TYdjdXcjxEntity> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            list = DataBaseManager.query(hql, null);
            for (TYdjdXcjxEntity entity : list){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(entity);
//                jsonObject.remove("id");
                jsonArray.add(jsonObject);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(jsonArray.toString());
    }

    //获取现场复查记录
    public void getXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hql = "from TYdjdXcfxEntity t where t.delFlag = 0 and (t.isfc = 0 or t.isfc is null or t.isfc = 2) order by t.createDate desc";
        List<TYdjdXcfxEntity> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        try {
            list = DataBaseManager.query(hql, null);

            for (TYdjdXcfxEntity xcfxEntity : list){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(xcfxEntity);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                jsonObject.put("fctime", sdf.format(xcfxEntity.getCreateDate().getTime()));

                jsonArray.add(jsonObject);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(jsonArray.toString());
    }

    //获取移交管理记录
    public void getYjgl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hql = "from TYdjdShEntity t where t.delFlag = 0 order by t.createDate desc";
        List<TYdjdShEntity> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        try {
            list = DataBaseManager.query(hql, null);

            for (TYdjdShEntity ydjdShEntity : list){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(ydjdShEntity);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                jsonObject.put("yjtime", sdf.format(ydjdShEntity.getCreateDate().getTime()));

                jsonArray.add(jsonObject);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(jsonArray.toString());
    }

    //获取任务资料类别
    public void getYwzlLb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hql = "from TYdjdJcxEntity t where t.delFlag = 0 order by t.createDate desc";
        List<TYdjdJcxEntity> list = new ArrayList<>();

        try {
            list = DataBaseManager.query(hql, null);
        } catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().print(JSON.toJSONString(list));
    }

    //获取任务资料内容
    public void getYwzlXm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = getRequestStr(request);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<TYdjdJcxxxEntity> list = new ArrayList<>();

        if (jsonObject.get("id") != null){
            String kind = jsonObject.get("id").toString();
            String hql = "from TYdjdJcxxxEntity t where t.kindId = '" + kind + "' order by t.createDate asc";

            try {
                list = DataBaseManager.query(hql, null);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        response.getWriter().print(JSON.toJSONString(list));
    }

    //查询function页面各个组件数量
    public void getFunctionNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //检查计划
        String jcjhHql = "select count(*) from TJcjhEntity t where t.isClose is null or t.isClose = ''";
        //责令整改
        String zlzgHql = "select count(*) from TYdjdXcjxEntity t where t.iszlzg = 1";
        //现场复查
        String xcfcHql = "select count(*) from TYdjdXcfxEntity";
        //移交管理
        String yjglHql = "select count(*) from TYdjdShEntity";

        int jcjhNum = DataBaseManager.getCount(jcjhHql, null);
        int zlzgNum = DataBaseManager.getCount(zlzgHql, null);
        int xcfcNum = DataBaseManager.getCount(xcfcHql, null);
        int yjglNum = DataBaseManager.getCount(yjglHql, null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jcjhNum", jcjhNum);
        jsonObject.put("zlzgNum", zlzgNum);
        jsonObject.put("xcfcNum", xcfcNum);
        jsonObject.put("yjglNum", yjglNum);

        response.getWriter().print(jsonObject.toString());
    }

    //签到
    public void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int result = -1;
        String json = getRequestStr(request);

        TQdxxEntity qdxxEntity = JSON.parseObject(json, new TypeReference<TQdxxEntity>() {});
        qdxxEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        qdxxEntity.setIsyx("1");
        result = (int) DataBaseManager.add(qdxxEntity);

        response.getWriter().print(result);
    }

    //根据request对象将请求参数解析出来,默认前端只会传送一组参数过来
    private String getRequestStr(HttpServletRequest request){
        return request.getParameter("param");
    }
}