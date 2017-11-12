package com.kilogate.chapter2.helper;

import com.kilogate.chapter2.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库操作助手类
 *
 * @author fengquanwei
 * @create 2017/11/11 18:18
 **/
public class DatabaseHelper {
    private static Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    static {
        CONNECTION_HOLDER = new ThreadLocal<>();
        QUERY_RUNNER = new QueryRunner();

        Properties properties = PropertiesUtil.loadProperties("config.properties");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(properties.getProperty("jdbc.driver"));
        DATA_SOURCE.setUrl(properties.getProperty("jdbc.url"));
        DATA_SOURCE.setUsername(properties.getProperty("jdbc.username"));
        DATA_SOURCE.setPassword(properties.getProperty("jdbc.password"));
    }

    /**
     * 插入单个实体
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.size() == 0) {
            logger.info("插入单个实体失败, 参数列表为空");
            return false;
        }

        String sql = "insert into " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        columns.replace(columns.lastIndexOf(", "), columns.length(), ") ");
        values.replace(values.lastIndexOf(", "), values.length(), ") ");
        sql += columns + " values " + values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除单个实体
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "delete from " + getTableName(entityClass) + " where id =?";
        return executeUpdate(sql, id) == 1;
    }

    /**
     * 更新单个实体
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.size() == 0) {
            logger.info("更新实体单个失败, 参数列表为空");
            return false;
        }

        String sql = "update " + getTableName(entityClass) + " set ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("= ?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id = ?";

        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);

        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 查询实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList = null;

        try {
            entityList = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询实体列表异常, sql: {}, params: {}", sql, Arrays.toString(params), e);
        }

        return entityList;
    }

    /**
     * 查询单个实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;

        try {
            entity = QUERY_RUNNER.query(getConnection(), sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            logger.error("查询单个实体异常, sql: {}, params: {}", sql, Arrays.toString(params), e);
        }

        return entity;
    }

    /**
     * 执行更新语句（insert，delete，update）
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;

        try {
            rows = QUERY_RUNNER.update(getConnection(), sql, params);
        } catch (SQLException e) {
            logger.error("执行更新语句异常, sql: {}, params: {}", sql, Arrays.toString(params), e);
        }

        return rows;
    }

    /**
     * 执行查询语句（select）
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result = null;

        try {
            result = QUERY_RUNNER.query(getConnection(), sql, new MapListHandler(), params);
        } catch (SQLException e) {
            logger.error("执行查询语句异常, sql: {}, params: {}", sql, Arrays.toString(params), e);
        }

        return result;
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = CONNECTION_HOLDER.get();
        if (connection == null) {
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("获取数据库连接异常", e);
            } finally {
                CONNECTION_HOLDER.set(connection);
            }
        }

        return connection;
    }

    /**
     * 执行 SQL 文件
     */
    public static void executeSqlFile(String filePath) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String sql;
        try {
            while ((sql = reader.readLine()) != null) {
                DatabaseHelper.executeUpdate(sql);
            }
        } catch (IOException e) {
            logger.error("执行 SQL 文件异常, filePath: {}", filePath, e);
        }
    }

    // 根据实体名获取表名
    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }
}
