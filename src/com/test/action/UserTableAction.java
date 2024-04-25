package com.test.action;

import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.util.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 1:37:52
 * Shows table of registered users
 */
public class UserTableAction extends BaseAction {
    private UserDao userDao = new UserDao();

    public String getName() {
        return "user_table";
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        List<User> users = userDao.getUsers();
        request.setAttribute("user_table", users);
        forward(request, response, "user_table.jsp");
    }
}
