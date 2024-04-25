package com.test.util;

/**
 * User: Nikitos
 * Date: 08.11.2009
 * Time: 17:52:51
 * Utility class
 */
public class Util {
    public static String getJspLocation() {
        return "/WEB-INF/jsp/";
    }

    public static String getJspPath(String jsp) {
        return getJspLocation() + jsp;
    }

    public static String checkNull(String s) {
        return s == null || s.length() == 0 ? null : s;
    }
}
