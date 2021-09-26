package com.qf.jedis.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Cjl
 * @date 2021/7/1 20:42
 */
public class MyBatisUtils {
    /**
     * 获取SqlSession工厂
     */
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 创建ThreadLocal绑定SqlSeesion会话对象
     */
    private static final ThreadLocal<SqlSession> t1 = new ThreadLocal<>();

    /**
     * 加载配置文件，获取SqlSession会话工厂对象
     */
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取SqlSession会话对象（获得连接，从当前threadlocal获得当前线程SqlSession）
     * @return
     */
    public static SqlSession openSession(){
        SqlSession sqlSession = t1.get();
        if (null==sqlSession){
            sqlSession=sqlSessionFactory.openSession();
            t1.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭会话对象，threadlocal移除SqlSession
     */
    public static void closeSession(){
        SqlSession sqlSession = t1.get();
        sqlSession.close();
        t1.remove();
    }

    public static void commit(){
        SqlSession sqlSession =openSession();
        sqlSession.commit();
        closeSession();
    }

    public static void rollback(){
        SqlSession sqlSession = openSession();
        sqlSession.rollback();
        closeSession();
    }

    public static <T extends Object> T getMapper(Class<T> clazz){
        SqlSession sqlSession = openSession();
        return sqlSession.getMapper(clazz);
    }





}

