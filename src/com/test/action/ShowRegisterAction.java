package com.test.action;

import com.test.util.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 19:03:25
 * Shows registration page
 */
public class ShowRegisterAction extends BaseAction {
    public String getName() {
        return "show_register";
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        forward(request, response, "register.jsp");
    }

    public boolean isRestricted() {
        return false;
    }
}
