package repository.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import entity.Person;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import repository.PersonRepository;
import utils.JDBCUtils;

import javax.sql.DataSource;
import java.sql.*;


public class PersonRepositoryImpl implements PersonRepository {

    private static DataSource dataSource = new ComboPooledDataSource("testc3p0");

    /**
     * 读取用户背景图片
     * 使用dbutils简化操作，用简短的语句实现对数据库的增删改查
     * 同时如果没有自主创建的话，dbutils会自动关闭conn等资源，无需手动释放
     *
     * @return 返回数据库图片二进制数组
     */
    @Override
    public byte[] readCover(String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("select usercover from person where tel=?");
        Object[] params = {tel};
        byte[] bytes = null;
        try {
            bytes = queryRunner.query(sql, new ScalarHandler<>(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bytes;
    }

    /**
     * 读取用户头像
     *
     * @return 返回用户头像二进制数组
     */
    @Override
    public byte[] readPor(String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("select userportrait from person where tel=?");
        Object[] params = {tel};
        byte[] bytes = null;
        try {
            bytes = queryRunner.query(sql, new ScalarHandler<>(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bytes;
    }

    /**
     * 上传用户个人信息
     *
     * @param person 一个person类
     * @return The number of rows updated.
     */
    @Override
    public int UploadPersonInfo(Person person) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("UPDATE person set sex = ?,work = ?,location = ?,intro = ? where tel=?");
        //初始化一个num接收返回值
        int num = 0;
        try {
            num = queryRunner.update(sql, person.getSex(), person.getWork(), person.getLocation(), person.getIntro(), person.getTel());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }

    /**
     * 获取用户个人信息
     *
     * @param tel 电话号码
     * @return 返回一个Person对象，使用dbutils的BeanHandler
     */
    @Override
    public Person GetPersonInfo(String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("SELECT * from person where tel = ?");
        Person person = null;
        try {
            person = queryRunner.query(sql, new BeanHandler<Person>(Person.class), tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    /**
     * 更改用户背景
     *
     * @param bytes 背景的byte数组
     * @param tel   用户电话
     * @return The number of rows updated.
     */
    @Override
    public int UploadUserCover(byte[] bytes, String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        //通过num判断是否更改成功
        int num = 0;
        String sql = ("update person set usercover = ? where tel=?");
        try {
            num = queryRunner.update(sql, bytes, tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }


    /**
     * 更改用户头像
     *
     * @param bytes 头像的二进制数组
     * @param tel   电话
     * @return The number of rows updated.
     */
    @Override
    public int UploadUserPor(byte[] bytes, String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        //通过num判断是否更改成功
        int num = 0;
        String sql = ("update person set userportrait = ? where tel = ?");

        try {
            num = queryRunner.update(sql, bytes, tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }

}

