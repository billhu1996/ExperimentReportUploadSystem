package View;
import External.Error;
import External.MD5;
import Model.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bill on 16/4/18.
 */

public class User {
    private String id;
    private String password;
    private String name;
    private UserType userType;

    public User(String id, String password, String name, UserType userType) {
        this.userType = userType;
        this.name = name;
        this.password = password;
        this.id = id;
    }
    public User() {}

    public static User login(String id, String password) throws Error {
        String MD5password = MD5.string2MD5(password);
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("SupportingFiles/beans.xml");
            UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
            return userJDBCTemplate.login(id, MD5password);
        } catch (Error error) {
            throw error;
        }
    }

    public static User register(User temporaryUser) throws Error {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("SupportingFiles/beans.xml");
            UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
            return userJDBCTemplate.register(temporaryUser);
        } catch (Error error) {
            throw error;
        }
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UserType getUserType() {
        return userType;
    }
    public String getPassword() {
        return password;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static void main(String[] args) {
        try {
            User user = User.login("2014011251", "password");
            System.out.println("用户名: " + user.getName() + user.getUserType().getIndex() + user.getId());
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
        try {
            User userWrong = User.login("2014011251", "wrongpassword");
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
        try {
            User registerUser = User.register(new User("2014011121", "password2", "miss", new UserType(1)));
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
        try {
            User registerUser = User.register(new User("2014011326", "password2", "yuantao", new UserType(1)));
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
        try {
            User registerUser = User.register(new User("2014011111", "password2", "miss", new UserType(1)));
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
    }
}
