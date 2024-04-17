<%@ page import="com.test.util.Const" %>
<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 09.11.2009
  Time: 1:32:09
  Out errors list
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String[] errors = (String[]) request.getAttribute(Const.REQ_ATTR_ERRORS);%>
<%
    if (errors != null) {
        out.print("<ul>");
        for (String s : errors) {
            out.print("<li>" + s + "</li>");
        }
        out.print("</ul>");
    }
%>

