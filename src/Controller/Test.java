package Controller;

import View.User;

/**
 * Created by bill on 16/4/26.
 */
public class Test {
    public static void main(String[] args) {
        User user = User.login("2014011251", "password");
        System.out.println(user);
    }
}
