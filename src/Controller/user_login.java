package Controller;

import External.Error;
import External.MD5;
import Model.UserJDBCTemplate;
import View.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bill on 16/4/26.
 */
public class user_login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        try {
            System.out.println(id+"------------"+password);


            String MD5password = MD5.string2MD5(password);
            ApplicationContext context = new ClassPathXmlApplicationContext("SupportingFiles/beans.xml");
            UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
            User user = userJDBCTemplate.login(id, MD5password);


//            User user = User.login(id, password);
            System.out.println("------------");
//            System.out.println(user.getId()+user.getName()+user.getUserType()+user.getPassword());
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
