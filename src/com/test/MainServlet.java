package com.test;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.test.util.Const;
import com.test.util.Util;
import com.test.action.*;

/**
 * User: Nikitos
 * Date: 07.11.2009
 * Time: 3:02:48
 * Application controller
 */
public class MainServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(MainServlet.class);

    private Map<String, IAction> actions;

    public void init() throws ServletException {
        super.init();
        DbManager.init();
        actions = new HashMap<String, IAction>();
        addAction(new UserTableAction());
        addAction(new ShowRegisterAction());
        addAction(new RegisterAction());
        addAction(new LoginAction());
        addAction(new LogoutAction());
    }

    public void destroy() {
        super.destroy();
        DbManager.destroy();
    }

    private void addAction(IAction action) {
        actions.put(action.getName(), action);
    }

    private IAction getAction(String userAction) {
        return actions.get(userAction);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            log.debug("Process request");
            String userAction = getUserAction(request);
            log.debug("requestURI: " + request.getRequestURI());
            log.debug("userAction: " + userAction);

            IAction action = getAction(userAction);

            HttpSession session = request.getSession();
            boolean needLogin = action == null || action.isRestricted();

            if (session.getAttribute(Const.REQ_ATTR_AUTHORIZED) == null && needLogin) {
                log.debug("not authorized");
                RequestDispatcher rd = request.getRequestDispatcher(Util.getJspPath("login.jsp"));
                rd.forward(request, response);
            } else {
                if (action != null) {
                    action.perform(request, response);
                } else {
                    throw new RuntimeException("Unknown action " + userAction);
                }
            }

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            request.setAttribute(Const.REQ_ATTR_ERRORS, new String[]{e.toString()});
            request.getRequestDispatcher(Util.getJspPath("error.jsp")).forward(request, response);
        }
    }

    private String getUserAction(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int beg = uri.lastIndexOf("/") + 1;
        int end = uri.lastIndexOf(".");
        if (beg >= 0 && end >= 0) {
            return uri.substring(beg, end);
        }
        return null;
    }
}
