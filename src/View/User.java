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

//    Error register(User temporaryUser) {
//
//    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UserType getUserType() {
        return userType;
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
        User user = login("2014011251", "password");
        System.out.println("用户名: " + user.getName() + user.getUserType() + user.getId());
        try {
            User userWrong = login("2014011251", "wrongpassword");
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
    }
}
