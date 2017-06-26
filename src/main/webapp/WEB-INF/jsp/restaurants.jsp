<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.06.2017
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>
<html>
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3>"Restaurants"</h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>name</th>
            <th>Vote</th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.isakovalexey.lunch.model.Restaurant"/>
            <tr>
                <td><c:out value="${restaurant.name}"/></td>
                <td>${restaurant.vote}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
