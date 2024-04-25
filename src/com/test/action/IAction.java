package com.test.action;

import com.test.util.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 1:35:32
 * Interface for performing some functionality from requests
 */
public interface IAction {
    public String getName();
    public void perform(HttpServletRequest request, HttpServletResponse response) throws BaseException;
    public boolean isRestricted();
}
