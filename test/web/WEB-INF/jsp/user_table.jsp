<%@ page import="com.test.bean.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 07.11.2009
  Time: 23:20:03
  Registered users table
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registered users table</title>
</head>
<body>
<jsp:include page="page_errors.jsp"/>
<jsp:include page="dashboard.jsp"/>
<% List<User> users = (List<User>) request.getAttribute("user_table");%>
<table>
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
    </tr>

    <%
        for (User u : users) {
            out.print(
                    "<tr>" +
                            "<td>" + u.getLogin() + "</td>" +
                            "<td>" + u.getName() + "</td>" +
                            "<td>" + u.getSurname() + "</td>" +
                            "</tr>"
            );
        }
    %>

</table>
</body>
</html>