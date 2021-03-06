<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.05.2017
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>

<body>


<section>
    <h3><fmt:message key="users.title"/></h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="users.name"/></th>
            <th><fmt:message key="users.email"/></th>
            <th><fmt:message key="users.roles"/></th>
            <th><fmt:message key="users.active"/></th>
            <th><fmt:message key="users.registered"/></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.isakovalexey.lunch.model.User"/>
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.roles}</td>
                <td><%=user.isEnabled()%>
                </td>
                <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
