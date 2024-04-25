package com.test.action;

import com.test.action.BaseAction;
import com.test.util.BaseException;
import com.test.util.Const;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 20:18:15
 * Remove user object from session
 */
public class LogoutAction extends BaseAction {
    public String getName() {
        return "logout";
    }

    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        request.getSession().removeAttribute(Const.REQ_ATTR_AUTHORIZED);
        forward(request, response, "logout.jsp");
    }
}
