<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="Error Page"/>
</jsp:include>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <jsp:include page="navbar.jsp"/>
        <div class="jumbotron">
            <h2>You are going to this page, via broken link</h2>
            <a href="productPage">Go to Product Catalog</a>
        </div>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
