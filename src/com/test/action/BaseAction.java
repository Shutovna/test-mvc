package com.test.action;

import com.test.util.BaseException;
import com.test.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 19:57:08
 * Base action class.
 */
public abstract class BaseAction implements IAction {
    public boolean isRestricted() {
        return true;
    }
    protected void forward(HttpServletRequest request, HttpServletResponse response, String jspName) throws BaseException {
        String jspPath = Util.getJspPath(jspName);
        try {
            request.getRequestDispatcher(jspPath).forward(request, response);
        } catch (Exception e) {
            throw new BaseException("Cannot forward to " + jspPath, e);
        }
    }
}
