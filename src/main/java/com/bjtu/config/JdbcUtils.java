package com.bjtu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource druidDataSource;
    static {
        try {
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
       properties.load(inputStream);
        druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static  void close(Connection connection) throws Exception
    {
        if(connection!=null) {
           try {
               connection.close();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }
}
