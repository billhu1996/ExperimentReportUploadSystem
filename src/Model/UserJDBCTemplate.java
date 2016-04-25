package Model;

import External.Error;
import External.MD5;
import View.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import javax.sql.DataSource;

/**
 * Created by bill on 16/4/18.
 */
public class UserJDBCTemplate {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public User login(String id, String password) throws Error {
        try {
            String SQL = "select * from user where id = ?";
            User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UserMapper());
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                throw new Error(1, "用户名或密码错误!!");
            }
        } catch (DataAccessException exception) {
            throw new Error(1, "用户名或密码错误!!");
        }
    }

    public User register(User user) throws Error {
        try {
            String SQL = "select * from user where id = ?";
            User existingUser = jdbcTemplateObject.queryForObject(SQL, new Object[]{user.getId()}, new UserMapper());
            throw new Error(2, "用户名已占用!!");
        } catch (DataAccessException exception) {
            String SQL = "insert into user (id, name, password, user_type) values (?, ?, ?, ?)";
            jdbcTemplateObject.update(SQL, user.getId(), user.getName(), MD5.string2MD5(user.getPassword()), user.getUserType().getIndex());
            SQL = "select * from user where id = ?";
            User userRegistered = jdbcTemplateObject.queryForObject(SQL, new Object[]{user.getId()}, new UserMapper());
            return userRegistered;
        }
    }
}
