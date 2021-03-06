package com.insp.servlet;

import com.alibaba.fastjson.*;
import com.insp.dao.*;
import com.insp.database.DataBaseManager;
import com.insp.util.ImageUtil;
import com.insp.util.PasswordUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoServlet {
    /**
     * 用户登录，将用户信息及officeId，loginUserId和与该用户相同单位的成员返回给前端
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        result.put("result", 0);
        JSONObject jsonObject = JSON.parseObject(getRequestStr(request));
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String officeName = "";
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
                            officeName = offices.get(0).getName();
                            if(offices.get(0).getName().equals("区局单位")){
                                officeName = "奉贤区";
                            }
                            userObj.put("officeName", officeName);
                        }

                        //查询该用户同单位成员
                        String allUseHql = "from SysUserEntity t where t.officeId = '" + officeId + "'";
                        List<SysUserEntity> allUsers = DataBaseManager.query(allUseHql, null);
                        if (allUsers.size() > 0) {
                            userObj.put("allUsers", JSON.toJSONString(allUsers));
                        }
                    }
                    String loginUserId = user.getId();
                    userObj.put("loginUserId",loginUserId);
                    result.put("result", userObj.toJSONString());
                }
            }
        }
        response.getWriter().print(result.toString());
    }

    /**
     * 获取检查项，从检查项表中获取，分为用户自建和系统添加的
     * @param request
     * @param response
     * @throws IOException
     */
    public void getJcx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TJcxEntity> list = new ArrayList<>();

        try {
            String jcjhId = getRequestStr(request);
            System.out.print(jcjhId);
            //(t.type is null or t.type = '') and
            String hql = "from TJcxEntity t where jcjhid = " + jcjhId;
            list = DataBaseManager.query(hql, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        response.getWriter().print(JSON.toJSONString(list));
    }

    /**
     * 保存客户端创建的检查项
     * @param request
     * @param response
     * @throws IOException
     */

    public void saveJcx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = -1;
        ImageUtil imageUtil = new ImageUtil();
        try {
            String jsonStr = getRequestStr(request);
            TJcxEntity tJcxEntity = JSON.parseObject(jsonStr, new TypeReference<TJcxEntity>() {});
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            tJcxEntity.setCjsj(sdf.format(new Date()));
            tJcxEntity.setType("1");  //表示是由客户端创建的检查项
            tJcxEntity.setKind("H");
            tJcxEntity.setJcxid(imageUtil.getUUIDString());
            tJcxEntity.setWsdesc("未通过"+tJcxEntity.getJcx());
            id = (int) DataBaseManager.add(tJcxEntity);
        } catch (Exception e){
            e.printStackTrace();
        }
        response.getWriter().print(id);
    }

    /**
     * 文书预览与保存现场检查公用方法
     * @param jsonStr,
     * @return

     */
    //todo
    //如果是自定义的检查项，ws描述不通过需再定义
    private Object[] wsLookModel(String jsonStr,int flagL)
    {
        ImageUtil imageUtil = new ImageUtil();
        String resultUrl = "";
        Object[] objects = new Object[3];
        try {
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
            xcjxEntity.setUploadPic("");
            xcfxEntity.setUploadPic("");

            //解析图片上传
            if(flagL==0) {
                JSONArray uploadPics = (JSONArray) jsonObject.get("picArray");
                StringBuilder uploadStr = new StringBuilder();
                for (int i = 0; i < uploadPics.size(); i++) {
                    Object uploadPic = uploadPics.get(i);
                    String uploadImgPath = imageUtil.saveSignImg(uploadPic.toString());
                    uploadStr.append(uploadImgPath);
                    if (i < uploadPics.size() - 1) {
                        uploadStr = uploadStr.append(",");
                    }
                }
                xcjxEntity.setUploadPic(uploadStr.toString());
                xcfxEntity.setUploadPic(uploadStr.toString());
            }
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
            StringBuilder namesfc = new StringBuilder();
            StringBuilder values = new StringBuilder();
            StringBuilder valuesfc = new StringBuilder();
            StringBuilder jcxlb = new StringBuilder();
            StringBuilder jcxlbfc = new StringBuilder();
            StringBuilder wsdesc = new StringBuilder();
            String wsDesc = "";
            int jcjhid = -1;
            if(jcxList.size()>0){
                jcjhid =  ((JSONObject) jcxList.get(0)).getInteger("jcjhid");
            }
            String hqlLp = "from TJcxEntity t where t.jcjhid = "+jcjhid;
            List<TJcxEntity> list = DataBaseManager.query(hqlLp,null);
            for (Object jcx : jcxList){

                String name = ((JSONObject) jcx).getString("jcx");
                int value = ((JSONObject) jcx).getBoolean("isActive") ? 1 : 0;
                wsDesc = ((JSONObject) jcx).getString("wsdesc");
                if(wsDesc == null || wsDesc.equals("")){
                    wsDesc = "未通过"+ ((JSONObject) jcx).getString("jcx");
                }
               // String lb = ((JSONObject) jcx).getString("lb");

            /*    if(lb.equals("") || lb.equals("H")){
                    wsDesc = "未通过"+((JSONObject) jcx).getString("jcx");
            }*/
                names.append(name + "-");
                values.append(value + ",");
              //  jcxlb.append(lb + ",");

                if(value != 1){
                    namesfc.append(name + "-");
                    valuesfc.append(value + ",");
              //      jcxlbfc.append(lb + ",");
                    wsdesc.append(wsDesc + "-");
                }
            }
            if(wsdesc.length()>0){
                xcjxEntity.setJcxwsdesc(wsdesc.toString().substring(0,wsdesc.length()-1));
                xcfxEntity.setJcxwsdesc(wsdesc.toString().substring(0,wsdesc.length()-1));
            }
            if (names.length() > 0){
                xcjxEntity.setJcjhid(names.toString().substring(0, names.length()-1));
                xcjxEntity.setJcqk(values.toString().substring(0, values.length()-1));
              //  xcjxEntity.setJcjhlb(jcxlb.toString());
            }

            if(valuesfc.length()>0){
                xcfxEntity.setJcqk(valuesfc.toString().substring(0, valuesfc.length()-1));
                xcfxEntity.setJcjhid(namesfc.toString().substring(0, namesfc.length()-1));
               // xcfxEntity.setJcjhlb(jcxlbfc.toString());
            }
            if (values.toString().contains("0")){
                //此检查存在问题,需要插入数据进复查表
                xcjxEntity.setIsyh("1");
                xcfxEntity.setIsyh("1");
                xcfxEntity.setDzshyj("");
                isNeeFc = true;
            } else {
                xcjxEntity.setIsyh("0");
            }
            int flag = 0;
            if(xcjxEntity.getOfficeid().equals("100")){
                flag = 1;
            }
            String[] wsdescList = new String[6];
            if(wsdesc.length()>0){
                wsdescList = wsdesc.toString().split("-");
            }
            String imgUrl = imageUtil.createResultImg(xcjxEntity, wsdescList,flag,flagL);

            //区用户进行保存将officeid修改为该企业所在的街镇id

            xcjxEntity.setResultPic(imgUrl);
           /*  List<TJcjhEntity> lists = new ArrayList<>();
            List<TBasicInformationEntity> listes = new ArrayList<>();
            String jcjhId = jsonObject.getString("jcjhid");
           if(xcjxEntity.getOfficeid().equals("100")){
                String hql = "from TJcjhEntity t where t.id ="+jcjhId;
                lists = DataBaseManager.query(hql,null);
                String bjcqyid = "";
                String jzId = "";
                if(lists.size()>0){
                    bjcqyid = lists.get(0).getJcqy();
                    String hqlL = "from TBasicInformationEntity t where t.id ="+bjcqyid;
                    listes = DataBaseManager.query(hqlL,null);
                    if(listes.size()>0){
                        jzId = listes.get(0).getAreaDm();
                    }

                }
                xcjxEntity.setOfficeid(jzId);
                xcfxEntity.setOfficeid(jzId);
            }*/
            objects[0] = xcjxEntity;
            objects[1] = xcfxEntity;
            objects[2] = isNeeFc;
        } catch(Exception e){
            e.printStackTrace();
        }
        return objects;
    }
    /**
     * 文书预览接口
     */
    public void getWsPre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object[] objects = new Object[3];
        String jsonStr = getRequestStr(request);
        int flagL = 1;
        objects = wsLookModel(jsonStr,flagL);
        JSONObject jsonObject = new JSONObject();
        String resultUrl = "";
        try {
            TYdjdXcjxEntity xcjxEntity = (TYdjdXcjxEntity) objects[0];
            resultUrl = xcjxEntity.getResultPic();
            jsonObject.put("result", resultUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.getWriter().print(jsonObject.toString());
    }
    /**
     * 步骤:1.解析参数 2.保存现场检查 3.修改检查计划 4.生成结果图,将地址返回给客户端
     * @param request
     * @param response
     * @throws IOException
     */
    public void saveXcjc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String resultUrl = "-1";
        boolean isNeeFc = false;
        try {
            String jsonStr = getRequestStr(request);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            TYdjdXcjxEntity xcjxEntity = new TYdjdXcjxEntity();
            TYdjdXcfxEntity xcfxEntity = new TYdjdXcfxEntity();
            int flagL = 0;//用于区分是该方法还是getWsPre调用wsLookModel
            Object[] objects = wsLookModel(jsonStr,flagL);
            xcjxEntity = (TYdjdXcjxEntity) objects[0];
            xcfxEntity = (TYdjdXcfxEntity) objects[1];

            isNeeFc =(boolean) objects[2];
            xcjxEntity.setDelFlag("0");
            xcfxEntity.setDelFlag("0");
            xcfxEntity.setLjfccs(0);
            int xcfcId = 0;
            String isfc = "1";
            if (isNeeFc){
                xcfxEntity.setResultPic(xcjxEntity.getResultPic());
                DataBaseManager.add(xcfxEntity);
                String hlq = "from TYdjdXcfxEntity t where t.jcry1Pic = '"+xcfxEntity.getJcry1Pic()+"'";
                List<TYdjdXcfxEntity> list = DataBaseManager.query(hlq,null);
                if(list.size()>0){
                    xcfcId = list.get(0).getId();
                }
                isfc = "0";
            }
            xcjxEntity.setXcfcId(xcfcId);
            xcjxEntity.setIsfc(isfc);
            resultUrl = xcjxEntity.getResultPic();
            DataBaseManager.add(xcjxEntity);
            int id = jsonObject.getInteger("jcid");
            String undateHql = "update TJcjhEntity t set t.isClose = 1 where t.id = " + id;
            DataBaseManager.update(undateHql, null);
            //插入复查数据

        } catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", resultUrl);
        response.getWriter().print(jsonObject.toString());
    }
    /**
     * 文书预览
     */

    /**
     * 增加复查记录(在现场检查记录中通过责令整改操作同时更改现场复查表中关于此条记录的状态)
     * @param request
     * @param response
     * @throws IOException
     */
    public void addXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String resultImagePath = null;
        try {
            String jsonStr = getRequestStr(request);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            resultImagePath = "1";
            if (resultImagePath != null){
                TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});
                TYdjdXcjxEntity xcjxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcjxEntity>() {});
                xcfxEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
                xcfxEntity.setDelFlag("0");
                xcfxEntity.setZlzgws(resultImagePath);
                xcfxEntity.setLjfccs(0);
                System.out.print(xcfxEntity.getLjfccs());
                int xcfcId = xcjxEntity.getXcfcId();
                if(xcjxEntity.getXcfcId() != 0)
                {
                    xcfxEntity.setId(xcfcId);
                }
                xcfxEntity.setZlzgsbh(jsonObject.getString("wsbh"));
                xcfxEntity.setZlzgrq(jsonObject.getString("time"));
                xcfxEntity.setIsyh("1");
                xcfxEntity.setDzshyj("");
                xcfxEntity.setIszlzg("1");
                xcfxEntity.setDzsh("0");
                xcfxEntity.setYjzt("0");
                xcfxEntity.setDelFlag("0");
                xcfxEntity.setLjfccs(0);
                String[] jcjhids  = jsonObject.getString("jcjhid").split("-");
                String[] jcqks = jsonObject.getString("jcqk").split(",");
                StringBuilder strBr = new StringBuilder();
                StringBuilder strJc = new StringBuilder();
                String strBrL = "";
                String strJcL = "";
                for(int i=0;i<jcqks.length;i++){
                    if(jcqks[i].equals("0")){
                        strBr.append(jcjhids[i]+"-");
                        strJc.append(jcqks[i]+",");
                    }
                }
                if(strBr.length()>0){
                    strBrL = strBr.substring(0,strBr.length()-1).toString();
                    strJcL = strJc.substring(0,strJc.length()-1).toString();
                }
                xcfxEntity.setJcjhid(strBrL);
                xcfxEntity.setJcqk(strJcL);
                xcfxEntity.setIsfc("0");

                DataBaseManager.update(xcfxEntity);
                xcjxEntity.setZlzgws(resultImagePath);
                xcjxEntity.setZlzgsbh(jsonObject.getString("wsbh"));
                xcjxEntity.setZlzgrq(jsonObject.getString("time"));
                xcjxEntity.setIszlzg("1");



                String hqlL = "from TYdjdXcjxEntity t where t.xcfcId = "+xcfcId;
                List<TYdjdXcjxEntity> list = DataBaseManager.query(hqlL,null);
                if(list.size()>0){
                    xcjxEntity.setId(list.get(0).getId());
                    DataBaseManager.update(xcjxEntity);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            resultImagePath = "0";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", resultImagePath);
        response.getWriter().print(jsonObject.toString());
    }

    /**
     * 获取队长审核，如果是队长角色则能看到待审核的记录，如果不是则返回空数组
     * @param request
     * @param response
     * @throws IOException
     */
    public void getDzsh(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String jsonStr = getRequestStr(request);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectP = JSONObject.parseObject(jsonStr);
        String userId = jsonObjectP.getString("loginUserId");
        String roleName = "";
        try {
        String hqlL ="from SysUserRoleEntity t where t.userId = '"+userId+"'";
        List<SysUserRoleEntity> roleList = DataBaseManager.query(hqlL,null);
        String roleId = "";
        if(roleList.size()>0){
            roleId = roleList.get(0).getRoleId();
            String hqlP = "from SysRoleEntity t where t.id = '"+roleId+"'";
            List<SysRoleEntity> entityList = new ArrayList<>();
                 entityList =  DataBaseManager.query(hqlP,null);
            if(entityList.size()>0){
                roleName = entityList.get(0).getName();
            }
        }
        if(roleName.equals("安监队长")) {
            String hql = "from TYdjdXcfxEntity t where t.delFlag = 0 and (t.isfc = 0 or t.isfc is null or t.isfc = 2) and t.dzsh = '2' and t.isyh = '1' and t.iszlzg = '1' and (t.yjzt is null or t.yjzt = '0') order by t.createDate desc";
            List<TYdjdXcfxEntity> list = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            list = DataBaseManager.query(hql, null);
            for (TYdjdXcfxEntity entity : list) {
                String updateDate = sdf.format(entity.getUpdateDate());
                JSONObject jsonObject = (JSONObject) JSON.toJSON(entity);
//                jsonObject.remove("id");
                jsonObject.put("fctime", updateDate);
                String[] jcxArray = entity.getJcjhid().split("-");
                jsonObject.put("jcxArray", jcxArray);
                jsonArray.add(jsonObject);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
         // }
        response.getWriter().print(jsonArray.toString());
    }
   /**
    * 更新队长审核状态，提交审核意见，打回队员
    */

    public void updateDzsh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String result = "-1";
        try {
            String jsonStr = getRequestStr(request);
            TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});
            TYdjdShEntity shEntity = JSON.parseObject(jsonStr,new TypeReference<TYdjdShEntity>(){});
            int xcfcId = xcfxEntity.getId();
            String hqlq ="from TYdjdXcjxEntity t where t.xcfcId = "+xcfcId;
            List<TYdjdXcjxEntity> wsList = new ArrayList<>();
            wsList = DataBaseManager.query(hqlq,null);
            if(wsList.size()>0){
                TYdjdXcjxEntity xcjxEntity = wsList.get(0);
                result = xcjxEntity.getResultPic();
            }
            xcfxEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            xcfxEntity.setZlzgws(result);
            shEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
              shEntity.setZlzgws(result);
              String dzsh = xcfxEntity.getDzsh();
            String dzshyj = xcfxEntity.getDzshyj() + "  "+ xcfxEntity.getUpdateDate().toString().substring(0,19);
            if(xcfxEntity.getDzshyj().equals("")||xcfxEntity.getDzshyj()==null){
                dzshyj ="";
            }

            xcfxEntity.setDzshyj(dzshyj);
            // dzsh =1 移交，=3 打回
            if(dzsh.equals("1")){
                xcfxEntity.setDzsh("3");
                xcfxEntity.setYjzt("0");
                xcfxEntity.setIsfc("2");
                xcfxEntity.setDelFlag("0");
                DataBaseManager.update(xcfxEntity);
                shEntity.setDelFlag("0");
                shEntity.setId(0);
                shEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
                DataBaseManager.add(shEntity);
            }else{
                xcfxEntity.setIsyh("1");
                xcfxEntity.setIszlzg("1");
                xcfxEntity.setYjzt("0");
                xcfxEntity.setDzsh("3");
                xcfxEntity.setDelFlag("0");
                xcfxEntity.setIsfc("2");
                DataBaseManager.update(xcfxEntity);
            }
            String hql = "from TYdjdXcjxEntity t where t.xcfcId = " + xcfxEntity.getId();
            List<TYdjdXcjxEntity> list = DataBaseManager.query(hql, null);
            if (list != null && list.size() > 0){
                TYdjdXcjxEntity xcjxEntity = list.get(0);
                xcjxEntity.setIsfc("0");
                xcjxEntity.setDzshyj(dzshyj);
                DataBaseManager.update(xcjxEntity);
            }


        } catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().print(jsonObject);
    }

    /**
     * 获取现场检查，根据officeid，区里用户返回镇名，镇待检查企业数统计,
     * 镇里用户则返回用户所在街镇的企业详细信息列表
     * @param request
     * @param response
     * @throws IOException
     */
    public void getXcjc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONArray jsonArray = new JSONArray();  //需要返回给客户端的数据(将所有的检查计划都转换成检查记录)
        String jsonStr = getRequestStr(request);
        JSONObject jsonObjectL = JSON.parseObject(jsonStr);
        String officeId = jsonObjectL.getString("officeId");
        //String jzId = jsonObjectL.getString("jzs");
        String jcdq = "";
        String companyHql = "";
        String str ="沪奉";
        String jcjhHql = "from TJcjhEntity t where t.isClose is null or t.isClose = '' order by t.cjsj desc";
        List<TJcjhEntity> jcjhs = DataBaseManager.query(jcjhHql, null);
        if (officeId.equals("100")) {
            try {
               // companyHql = "from TBasicInformationEntity t";
               // List<TBasicInformationEntity> companys = DataBaseManager.query(companyHql, null);
                String allAreaHql = "from SysOfficeEntity t where t.delFlag = '0' and type='2'";
                List<SysOfficeEntity> offices = DataBaseManager.query(allAreaHql,null);
                for(int i=0;i<offices.size();i++){  //遍历单位机构
                    JzEntity jzEntity = new JzEntity();
                    jzEntity.setJzId(offices.get(i).getId());
                    jzEntity.setJzName(offices.get(i).getName());
                    int counts = 0 ;
                    //筛选 检查计划
                    for(int k=0;k<jcjhs.size();k++){
                        if(jcjhs.get(k).getOfficeid().equals(offices.get(i).getId())){
                            counts ++;
                        }
                    }
                    jzEntity.setJhjcqys(counts);
                    /*for(int j=0;j<companys.size();j++){
                        if(jzEntity.getJzId().equals(String.valueOf(companys.get(j).getAreaDm()))){
                            for(int k=0;k<jcjhs.size();k++){
                                if(jcjhs.get(k).getJcqy().equals(String.valueOf(companys.get(j).getId()))){
                                    int count = jzEntity.getJhjcqys();
                                    jzEntity.setJhjcqys(count+1);
                                }
                            }
                        }
                    }*/
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(jzEntity);
                    jcdq = str;
                    jsonObject.put("jcdq",jcdq);
                    jsonArray.add(jsonObject);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            jsonArray = getJcjhQy(officeId);
        }
        response.getWriter().print(jsonArray.toString());
    }

    /**
     * 根据街镇id获取当前街镇的企业
     *  参数：企业的areadm 街镇编号jzid
     * @param response
     * @throws IOException
     */
    public void getQyByJzId(HttpServletRequest request,HttpServletResponse response) throws  IOException{
        JSONArray jsonArray = new JSONArray();
        String jcdq = "";
        String str = "沪奉";
        String companyHql = "";
        String jsonStr = getRequestStr(request);
        JSONObject jsonObjectL = JSON.parseObject(jsonStr);
        String jzId = jsonObjectL.getString("jzs");
        jsonArray = getJcjhQy(jzId);
        response.getWriter().print(jsonArray.toString());
    }

    /**
     *
     * @param param
     * @return
     */
    private JSONArray getJcjhQy(String param){
        String companyHql="";
        JSONArray jsonArray = new JSONArray();
        String jcdq = "";
        String str = "沪奉";
        String zxdw = "";
        try {
            String allAreaHql = "from SysOfficeEntity t where t.delFlag = '0' and t.grade = '2' and t.id = '" + param + "'";
            List<SysOfficeEntity> allAreas = DataBaseManager.query(allAreaHql, null);
            if (allAreas.size() > 0) {
                for (int i = 0; i < allAreas.size(); i++) {
                    SysOfficeEntity sysOfficeEntity = allAreas.get(i);
                    jcdq = str + sysOfficeEntity.getName();
                    Pattern p = Pattern.compile("镇$");
                    Matcher m = p.matcher(jcdq);
                    if (m.find()) {
                        jcdq = jcdq.substring(0, jcdq.length() - 1);
                    }
                }
            }
            //查询检查计划，
            String jcjhHql = "from TJcjhEntity t where t.officeid = '"+ param +"' and (t.isClose is null or t.isClose = '')  order by t.cjsj desc";
            List<TJcjhEntity> jcjhs = DataBaseManager.query(jcjhHql, null);
            for (TJcjhEntity tJcjhEntity : jcjhs) {
                companyHql = "from TBasicInformationEntity t where t.id = '"+ tJcjhEntity.getJcqy() +"' ";
                List<TBasicInformationEntity> companys = DataBaseManager.query(companyHql, null);
                if(companys.size() > 0){
                    TYdjdXcjxEntity xcjxEntity = new TYdjdXcjxEntity();
                    xcjxEntity.setBjxqy(companys.get(0).getEnterpriseName());
                    xcjxEntity.setQydz(companys.get(0).getProductionAddress());
                    xcjxEntity.setQyfzr(companys.get(0).getSecurityOfficer());
                    xcjxEntity.setQyfzrlxdh(companys.get(0).getSecurityOfficerPhone());
                    xcjxEntity.setQyfzrzw("");
                    xcjxEntity.setJccs("");
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    String tsL = ts.toString().substring(0,4);
                    xcjxEntity.setJcaj(tsL);
                    xcjxEntity.setJckssj(tJcjhEntity.getJckssj());
                    xcjxEntity.setJcjssj(tJcjhEntity.getJcjssj());
                    xcjxEntity.setBjcqyid(companys.get(0).getId() + "");
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(xcjxEntity);
                    jsonObject.put("jcid", tJcjhEntity.getId());
                    if (allAreas.size() > 0) {
                        for (int i = 0; i < allAreas.size(); i++) {
                            if(tJcjhEntity.getOfficeid().equals(allAreas.get(i).getId())){
                                zxdw = allAreas.get(i).getName();
                            }
                        }
                    }
                    jsonObject.put("zxdw", zxdw);  //执行单位
                    jsonObject.put("zxr", tJcjhEntity.getZxr());   //执行人
                    jsonObject.put("jcjhid", tJcjhEntity.getId());
                    jsonObject.remove("id");    //去除无用的id字段
                    jsonObject.put("jcdq", jcdq);
                    jsonArray.add(jsonObject);
                }
            }

            /*List<TBasicInformationEntity> companys = DataBaseManager.query(companyHql, null);
            String jcjhHql = "from TJcjhEntity t where t.isClose is null or t.isClose = '' order by t.cjsj desc";
            List<TJcjhEntity> jcjhs = DataBaseManager.query(jcjhHql, null);
            for (TJcjhEntity tJcjhEntity : jcjhs) {
                for (TBasicInformationEntity company : companys) {
                    if (tJcjhEntity.getJcqy().equals(String.valueOf(company.getId()))) {
                        //将检查记录与公司信息组合成现场检查
                        TYdjdXcjxEntity xcjxEntity = new TYdjdXcjxEntity();
                        xcjxEntity.setBjxqy(company.getEnterpriseName());
                        xcjxEntity.setQydz(company.getProductionAddress());
                        xcjxEntity.setQyfzr(company.getSecurityOfficer());
                        xcjxEntity.setQyfzrlxdh(company.getSecurityOfficerPhone());
                        xcjxEntity.setQyfzrzw("");
                        xcjxEntity.setJccs("");
                        Timestamp ts = new Timestamp(System.currentTimeMillis());
                        String tsL = ts.toString().substring(0,4);
                        xcjxEntity.setJcaj(tsL);
                        xcjxEntity.setJckssj(tJcjhEntity.getJckssj());
                        xcjxEntity.setJcjssj(tJcjhEntity.getJcjssj());
                        xcjxEntity.setBjcqyid(company.getId() + "");
                        JSONObject jsonObject = (JSONObject) JSON.toJSON(xcjxEntity);
                        jsonObject.put("jcid", tJcjhEntity.getId());
                        if (allAreas.size() > 0) {
                            for (int i = 0; i < allAreas.size(); i++) {
                                if(tJcjhEntity.getOfficeid().equals(allAreas.get(i).getId())){
                                    zxdw = allAreas.get(i).getName();
                                }
                            }
                        }
                        jsonObject.put("zxdw", zxdw);  //执行单位
                        jsonObject.put("zxr", tJcjhEntity.getZxr());   //执行人
                        jsonObject.put("jcjhid", tJcjhEntity.getId());
                        jsonObject.remove("id");    //去除无用的id字段
                        jsonObject.put("jcdq", jcdq);
                        jsonArray.add(jsonObject);
                    }
                }
            }*/

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 根据id获取公司信息
     * @param request
     * @param response
     * @throws IOException
     */
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

    /**
     * 这里前端需要涉及到多次重组渲染,去除id
     * 获取检查记录，对应前端记录管理
     * @param request
     * @param response
     * @throws IOException
     */
    public void getJcjl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hql = "from TYdjdXcjxEntity t where t.delFlag = 0  order by t.createDate desc";
        JSONArray jsonArray = new JSONArray();
        jsonArray = getJcjlEntity(hql);
        response.getWriter().print(jsonArray.toString());
    }
    private JSONArray getJcjlEntity(String hql){
        JSONArray jsonArray = new JSONArray();
        List<TYdjdXcjxEntity> list = new ArrayList<>();
        try {
            list = DataBaseManager.query(hql, null);
            for (TYdjdXcjxEntity entity : list){
                JSONObject jsonObject = (JSONObject) JSON.toJSON(entity);
                jsonObject.remove("id");
                jsonArray.add(jsonObject);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }


    /**
     * 获取现场复查记录，根据officeid来执行不同的hql、调用getXcfcEntity来详细处理
     * @param request
     * @param response
     * @throws IOException
     */

    public void getXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonStr = getRequestStr(request);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String hql = "";
        String officeId = jsonObject.getString("officeId");
        if(officeId.equals("100")) {
            hql = "from TYdjdXcfxEntity t where t.delFlag = 0 and (t.isfc = '0' or t.isfc is null or t.isfc = '2') and (t.dzsh is null or t.dzsh = '3' or t.dzsh = '0') and t.isyh = '1' and t.iszlzg = '1' and (t.yjzt is null or t.yjzt = '0') order by t.createDate desc";
        } else{
            hql = "from TYdjdXcfxEntity t where t.delFlag = 0 and (t.isfc = '0' or t.isfc is null or t.isfc = '2') and (t.dzsh is null or t.dzsh = '3' or t.dzsh = '0') and t.isyh = '1' and t.iszlzg = '1' and (t.yjzt is null or t.yjzt = '0') and t.officeid = "+officeId+"order by t.createDate desc";
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray = getXcfcEntity(hql,officeId);
        response.getWriter().print(jsonArray.toString());
    }

    /**
     * 获取现场复查，根据officeId辨别是区用户还是镇用户，执行不同的hql，并且区返回各个镇的计划检查企业数，镇名
     * 镇的话直接返回需要复查的企业详细信息列表
     * @param hql
     * @param officeId
     * @return
     */
    private JSONArray getXcfcEntity(String hql,String officeId) {
        List<TYdjdXcfxEntity> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        String allAreaHql = "from SysOfficeEntity t where t.delFlag = '0' and type='2'";
        try {
            list = DataBaseManager.query(hql, null);
            if(officeId.equals("100")){
                List<SysOfficeEntity> offices = DataBaseManager.query(allAreaHql,null);
                for(int i=0; i<offices.size();i++) {
                    JzEntity jzEntity = new JzEntity();
                    jzEntity.setJzId(offices.get(i).getId());
                    jzEntity.setJzName(offices.get(i).getName());
                    jzEntity.setJhjcqys(0);
                    for (TYdjdXcfxEntity xcfxEntity : list) {
                        if(jzEntity.getJzId().equals(xcfxEntity.getOfficeid())){
                            jzEntity.setJhjcqys(jzEntity.getJhjcqys()+1);
                        }
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject = (JSONObject) JSON.toJSON(jzEntity);
                    jsonArray.add(jsonObject);
                }
            }else {
                for (TYdjdXcfxEntity xcfxEntity : list) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(xcfxEntity);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String jcjhid = xcfxEntity.getJcjhid();
               //     String lb = xcfxEntity.getJcjhlb();
                    String[] jcjhArray = jcjhid.split("-");
               //     String[] lbArray = lb.split(",");
                    Object[] objects = new Object[jcjhArray.length];
                    for(int j= 0;j<jcjhArray.length;j++){
                        JcjhEntity jcjhEntity = new JcjhEntity();
                        jcjhEntity.setJcjh(jcjhArray[j]);
                        jcjhEntity.setisActive(true);
                        jcjhEntity.setisChecked(true);
                 //       jcjhEntity.setJcjhlb(lbArray[j]);
                        objects[j] = jcjhEntity;
                    }

                    jsonObject.put("fctime", sdf.format(xcfxEntity.getCreateDate().getTime()));
                    jsonObject.put("jcjhArray", objects);
                    jsonArray.add(jsonObject);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 区用户登录获取现场复查显示的计划检查企业及企业数目点击该div后前台传入jzid获取该街镇需要检查的企业列表
     * 根据街镇id显示当前街镇需要复查的记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void getXcfcByJz(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String jsonStr = getRequestStr(request);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(jsonStr);
        String jzId = jsonObject.getString("jzs");
        String hql = "from TYdjdXcfxEntity t where t.delFlag = 0 and (t.isfc = 0 or t.isfc is null or t.isfc = 2) and (t.dzsh = '0' or t.dzsh = '3') and t.isyh = '1' and t.iszlzg = '1' and t.officeid = "+jzId+" order by t.createDate desc";
        try{
            List<TYdjdXcfxEntity> list = DataBaseManager.query(hql,null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if(list.size()>0) {
                for (int i = 0; i < list.size(); i++) {
                    TYdjdXcfxEntity xcfxEntity = list.get(i);
                    JSONObject jsonObjectL = new JSONObject();
                    jsonObjectL = (JSONObject) JSON.toJSON(xcfxEntity);
                    String jcjhid = xcfxEntity.getJcjhid();
                    String[] jcjhArray = jcjhid.split("-");
                    Object[] objects = new Object[jcjhArray.length];
                    for(int j= 0;j<jcjhArray.length;j++){
                        JcjhEntity jcjhEntity = new JcjhEntity();
                        jcjhEntity.setJcjh(jcjhArray[j]);
                        jcjhEntity.setisActive(true);
                        jcjhEntity.setisChecked(true);
                        objects[j] = jcjhEntity;
                    }
                    jsonObjectL.put("fctime", sdf.format(xcfxEntity.getCreateDate().getTime()));
                    jsonObjectL.put("jcjhArray", objects);
                    jsonArray.add(jsonObjectL);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response.getWriter().print(jsonArray.toString());
    }

    /**
     * 更新现场复查结果，如果通过则isfc置1，直接结束，不通过isfc置0，队长可看dzsh置2
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateXcfc(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ImageUtil imageUtil = new ImageUtil();
        Boolean isNeedFc = false;
        int result = -1;
        try{
            String jsonStr = getRequestStr(request);
            int qyid = -1;
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            int fcid = (int) jsonObject.get("fcid");
            String hql = "from TYdjdXcfxEntity t where t.id = "+fcid;
            String hqlL= "from TYdjdXcjxEntity t where t.xcfcId = "+fcid;
            List<TYdjdXcfxEntity> list = DataBaseManager.query(hql,null);
            TYdjdXcfxEntity xcfxEntity = list.get(0);
          /*  TYdjdXcfxEntity xcfxEntity = JSON.parseObject(jsonStr, new TypeReference<TYdjdXcfxEntity>() {});*/
            xcfxEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            JSONArray xcfxPics = (JSONArray) jsonObject.get("picArray");
            StringBuilder uploadStr = new StringBuilder();
            if (xcfxPics.size() > 0) {
                for (int i = 0; i < xcfxPics.size(); i++) {
                    Object uploadPic = xcfxPics.get(i);
                    String uploadImgPath = imageUtil.saveSignImg(uploadPic.toString());
                    uploadStr.append(uploadImgPath+",");
                }
            }
            String uploadStrL = "";
            if(uploadStr.length()>0){
                uploadStrL = uploadStr.toString();
                uploadStrL = uploadStrL.substring(0,uploadStrL.length()-1);
            }
            xcfxEntity.setId(fcid);
            xcfxEntity.setUploadPic(uploadStrL);
            JSONArray jcxList = jsonObject.getJSONArray("fcjcxlist");
            StringBuilder namesfc = new StringBuilder();
            StringBuilder valuesfc = new StringBuilder();
            StringBuilder jcjhlb = new StringBuilder();
            StringBuilder jcjhlbfc = new StringBuilder();
            if(jcxList.size()>0) {
                for (Object jcx : jcxList) {
                    String name = ((JSONObject) jcx).getString("jcjh");
                    int value = ((JSONObject) jcx).getBoolean("isActive") ? 1 : 0;
                      String jcjhlbL = ((JSONObject) jcx).getString("jcjhlb");
                      jcjhlb.append(jcjhlbL + ",");
                    if (value == 0) {
                        namesfc.append(name + "-");
                        valuesfc.append(value + ",");
                        jcjhlbfc.append(jcjhlbL + ",");
                        isNeedFc = true;
                    }
                }
            }
            String jcjhid = "";
            String jcqk = "";
            if(valuesfc.toString().contains("0"))
            {

                jcjhid = namesfc.toString().substring(0,namesfc.length()-1);
                jcqk = valuesfc.toString().substring(0,valuesfc.length()-1);
            }
            xcfxEntity.setJcjhid(jcjhid);
            xcfxEntity.setJcqk(jcqk);
            if(xcfxEntity.getLjfccs() == null){
                xcfxEntity.setLjfccs(0);
            }
            xcfxEntity.setLjfccs(xcfxEntity.getLjfccs() + 1);
            xcfxEntity.setDzshyj("");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            xcfxEntity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            if (isNeedFc) {
                //此检查存在问题,需要插入数据进复查表
                xcfxEntity.setIsyh("1");
                xcfxEntity.setDelFlag("0");
                xcfxEntity.setDzsh("2");
                xcfxEntity.setIsfc("2");
            } else {
                xcfxEntity.setIsyh("0");
                xcfxEntity.setIsfc("1");
                xcfxEntity.setDzsh("0");
                xcfxEntity.setDelFlag("0");
                List<TYdjdXcjxEntity> lists = DataBaseManager.query(hqlL, null);
                if (lists.size() > 0) {
                    TYdjdXcjxEntity xcjxEntity = lists.get(0);
                    xcjxEntity.setIsfc("1");
                    DataBaseManager.update(xcjxEntity);
                }
            }
            DataBaseManager.update(xcfxEntity);
            result = 1;
        } catch (Exception e){
            e.printStackTrace();
        }
        response.getWriter().print(result);
    }

    /**
     * 获取移交管理记录
     * @param request
     * @param response
     * @throws IOException
     */
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
