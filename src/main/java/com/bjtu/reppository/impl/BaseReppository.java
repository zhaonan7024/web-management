package com.bjtu.reppository.impl;

import com.bjtu.config.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.util.List;

public class BaseReppository {
    private QueryRunner queryRunner = new QueryRunner();
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
}
