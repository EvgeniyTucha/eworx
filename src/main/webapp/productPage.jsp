<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="Product Catalog"/>
</jsp:include>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <jsp:include page="navbar.jsp"/>

        <div class="main-container">
            <div class="jumbotron">

                <div class="row">
                    <div class="col-md-12">
                        <c:forEach items="${products}" var="product">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <h4 class="text-center"><span class="label label-info">id:${product.productId}</span></h4>
                                    <img src="http://placehold.it/450x450&text=${product.productName}" class="img-responsive">
                                    <div class="caption">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <h3>${product.productName}</h3>
                                            </div>
                                            <div class="col-md-4 price">
                                                <h3>
                                                    <label>&euro;&nbsp;${product.productPrice}</label>
                                                </h3>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <form:form action="/addToCart?id=${product.productId}">
                                                    <input class="btn btn-success btn-product" type="submit" value="Add to Cart"/>
                                                </form:form>
                                            </div>
                                        </div>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
