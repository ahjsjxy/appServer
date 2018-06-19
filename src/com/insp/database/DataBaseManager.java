package com.insp.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static SessionFactory sessionFactory;

    /**
     * @return 获取会话工厂
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * @return 获取会话对象
     */
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    /**
     * @param obj 添加数据
     * @return
     */
    public static Object add(Object obj) {
        Session session = null;
        Transaction tran = null;
        Object result = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            result = session.save(obj);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (tran != null) {
                //事物回滚
                tran.rollback();
            }
        } finally {
            if (session != null) {
                //关闭session
                session.close();
            }
        }
        return result;
    }

    /**
     * 同时保存多条数据
     * @param list
     * @return
     */
    public static boolean add(List list) {
        Session session = null;
        Transaction tran = null;
        boolean result = false;
        try {
            session = getSession();
            tran = session.beginTransaction();

            for(Object obj : list){
                session.save(obj);
            }

            tran.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();

            if (tran != null) {
                //事物回滚
                tran.rollback();
            }
        } finally {
            if (session != null) {
                //关闭session
                session.close();
            }
        }
        return result;
    }

    /**
     * @return 更新数据
     * 参数为修改的主键id对象
     */
    public static boolean update(Object object) {
        Session session = null;
        Transaction tran = null;
        boolean result = false;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.update(object);
            tran.commit();
            result = true;
        } catch (Exception e) {
            if (tran != null) {
                //事物回滚
                tran.rollback();
            }
        } finally {
            if (session != null) {
                //关闭session
                session.close();
            }
        }
        return result;
    }

    /**
     * 使用语句更新
     * @param hql
     * @param params
     */
    public static void update(String hql, String... params) {

        Session session = null;
        Transaction tx = null;

        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                    // System.out.println("query influenced: "+params[i]);
                }
            }
            int n = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * @param c
     * @param obj 查询一条数据根据主键的id号
     * @return
     */
    public static Object get(Class c, int obj) {
        Session session = null;
        Object object = null;
        try {
            session = getSession();
            object = session.get(c, obj);
        } catch (Exception e) {
        } finally {
            if (session != null) {
                //关闭session
                session.close();
            }
        }
        return object;
    }

    /**
     * @param obj
     * @return 删除数据
     */
    public static boolean delete(Object obj) {
        Session session = null;
        Transaction tran = null;
        boolean result = false;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.delete(obj);
            tran.commit();
            result = true;
        } catch (Exception e) {
            if (tran != null) {
                //事物回滚
                tran.rollback();
            }
        } finally {
            if (session != null) {
                //关闭session
                session.close();
            }
        }
        return result;
    }

    /**
     * @param <T>   查询多条记录
     * @param hql   sql语句
     * @param param 参数数组
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> query(String hql, String[] param) {
        List<T> list = new ArrayList<T>();
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    query.setString(i, param[i]);
                }
            }
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }
    public static int queryBySql(String sql)
    {
        Session session = null;
        int maxId =0;
        try{
            Transaction tx = null;
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addScalar("id", IntegerType.INSTANCE);
            List list = query.list();
             if(list.size()>0){
                 maxId = (int)list.get(0);
             }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  maxId;
    }

    /**
     * @param hql
     * @param param 查询单条记录
     * @return
     */
    public static Object queryOne(String hql, String[] param) {
        Object object = null;
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    query.setString(0, param[i]);
                }
                object = query.uniqueResult();
            }
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return object;
    }

    /**
     * @param <T>
     * @param sql
     * @param param
     * @param page
     * @param size
     * @return 实现分页查询
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> queryByPage(String sql, String[] param, int page, int size) {
        List<T> list = new ArrayList<T>();
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    query.setString(i, param[i]);
                }
            }
            //筛选条数
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            list = query.list();
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

    /**
     * @param hql
     * @param pras
     * @return返回数据个数
     */
    public static int getCount(String hql, String[] pras) {
        int resu = 0;
        Session s = null;
        try {
            s = getSession();
            Query q = s.createQuery(hql);
            if (pras != null) {
                for (int i = 0; i < pras.length; i++) {
                    q.setString(i, pras[i]);
                }
            }
            resu = Integer.valueOf(q.iterate().next().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                s.close();
        }
        return resu;
    }
}
