package com.test.action;

import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.util.BaseException;
import com.test.util.Const;
import com.test.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 2:24:52
 * Create user and redirects to register_successfull.jsp.
 * If cannot create user go to register and display errors.
 */
public class RegisterAction extends BaseAction {
    private UserDao userDao = new UserDao();
    private static final int MAX_LEN = 30;

    public String getName() {
        return "register";
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        User u = new User(
                Util.checkNull(request.getParameter("login")),
                Util.checkNull(request.getParameter("password")),
                Util.checkNull(request.getParameter("name")),
                Util.checkNull(request.getParameter("surname"))
        );
        if (validateInput(request, u)) {
            userDao.create(u.getLogin(), String.valueOf(u.getPassword().hashCode()), u.getName(), u.getSurname());
            forward(request, response, "register_successfull.jsp");
        } else {
            forward(request, response, "register.jsp");
        }
    }

    private boolean validateInput(HttpServletRequest request, User user) {
        List<String> badFields = new ArrayList<String>();
        if (user.getLogin() == null) {
            badFields.add("login");
        }
        if (user.getPassword() == null) {
            badFields.add("password");
        }
        if (user.getName() == null || !isName(user.getName())) {
            badFields.add("name");
        }
        if (user.getSurname() == null || !isName(user.getSurname())) {
            badFields.add("surname");
        }
        if (!badFields.isEmpty()) {
            String msg = "";
            for (String s : badFields) {
                msg += s + ",";
            }
            msg = msg.substring(0, msg.length() - 1);
            request.setAttribute(Const.REQ_ATTR_ERRORS, new String[]{msg + " must be set"});
            return false;
        }
        return true;
    }


    /**
     * Check name name, surname, etc..
     * //todo use regex to check only alphabet symbols    
     */
    private boolean isName(String s) {
        return s.length() < MAX_LEN;
    }

    public boolean isRestricted() {
        return false;
    }
}
