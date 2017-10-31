<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="Product Details Page"/>
</jsp:include>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>

    <h2>Product Details</h2>

    <div>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Product Id:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${product.productId}</p></div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Product Name:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${product.productName}</p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">Price:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${product.productPrice}</p>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>