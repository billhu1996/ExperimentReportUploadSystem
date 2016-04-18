package Model;

import External.Error;
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
            String SQL = "select * from user where exists (select * from user where id = ? and password = ?)";
            User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{id, password}, new UserMapper());
            return user;
        } catch (DataAccessException exception) {
            throw new Error(1, "用户名或密码错误!!");
        }
    }

}
