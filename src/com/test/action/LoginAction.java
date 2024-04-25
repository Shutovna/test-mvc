package com.test.action;

import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.util.BaseException;
import com.test.util.Const;
import com.test.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 19:01:34
 * Add user object to session and redirects to user_table
 */
public class LoginAction extends BaseAction {
    private UserDao userDao = new UserDao();

    public String getName() {
        return "login";
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        String login = Util.checkNull(request.getParameter("login"));
        String password = Util.checkNull(request.getParameter("password"));
        if (login != null && password != null) {
            User user = userDao.getUser(login, String.valueOf(password.hashCode()));
            if (user != null) {
                request.getSession().setAttribute(Const.REQ_ATTR_AUTHORIZED, user);
            } else {
                request.setAttribute(Const.REQ_ATTR_ERRORS, new String[]{"User " + login + " not found"});
            }
        }
        forward(request, response, "user_table.action");
    }

    public boolean isRestricted() {
        return false;
    }
}
