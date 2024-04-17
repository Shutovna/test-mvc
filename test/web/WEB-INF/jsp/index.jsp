<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 08.11.2009
  Time: 18:01:36
  Start page. Redirect to user table
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body><%
    RequestDispatcher rd = request.getRequestDispatcher("/user_table.action");
    rd.forward(request, response);
%></body>
</html>