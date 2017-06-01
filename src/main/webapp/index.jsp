<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>

<body>

<section>
  <ul>
    <li><a href="users"><fmt:message key="users.title"/></a></li>
    <li><a href="meals"><fmt:message key="meals.title"/></a></li>
  </ul>
</section>

</body>
</html>