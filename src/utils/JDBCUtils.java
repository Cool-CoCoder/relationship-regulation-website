package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static DataSource dataSource = new ComboPooledDataSource("testc3p0");

    /**
     * 创建一个JDBCUtils，和dbutils用法不同，主要用于获取连接，回滚，和释放，本Utils采用ThreadLocal与dbutils
     *
     * @return 返回一个连接对象，之后赋给QueryRunner
     */
    public static Connection getConnection() {
        Connection connection = threadLocal.get();//获取threadlocal中的变量，不够可继续创建
        //每次需要排空，即connection为空时，创建一个connection
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 开启事务
     */
    public static void startTransaction() {
        //连接
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);//开启事务
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 正常提交事务
     */
    public static void commitTransaction() {
        //连接
        Connection connection = getConnection();
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 失败回滚事务
     */
    public static void rollbackTransaction() {
        //连接
        Connection connection = getConnection();
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public static void close() {
        //连接
        Connection connection = getConnection();
        try {
            if (connection != null) {
                connection.close();
            }
            threadLocal.remove();
            connection = null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
