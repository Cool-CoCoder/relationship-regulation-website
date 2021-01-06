package repository.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    private static DataSource dataSource = new ComboPooledDataSource("testc3p0");
    /**
     * 注册
     *
     * @param user user对象
     * @return The number of rows updated.
     */
    @Override
    public int Register(User user) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        //使用num判断是否注册成功
        int num = 0;
        String sql = ("INSERT INTO user values (?,?,?,?)");
        try {
            num = queryRunner.update(sql, user.getEmail(), user.getTel(), user.getName(), user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num;
    }

    /**
     * 登录
     *
     * @param user user对象
     * @return 返回一个注册好的user
     */
    @Override
    public User Login(User user) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("select * from user where tel = ? and password = ?");
        User user1 = null;
        try {
            user1 = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getTel(), user.getPassword());
            RegisterPerson(user.getTel());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user1;
    }


    /**
     * 注册一个person,如果用户没有注册person，则创建一个person表
     *
     * @param tel tel
     */
    private void RegisterPerson(String tel) {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = ("INSERT INTO person values (?,null,null,null,null,null,null)");
        try {
            queryRunner.update(sql, tel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
