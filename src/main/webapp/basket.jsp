<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp">
    <jsp:param name="pageName" value="Basket Page"/>
</jsp:include>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <jsp:include page="navbar.jsp"/>
        <c:choose>
            <c:when test="${not empty orderProducts}">
            <div class="main-container">
                    <div class="shopping-cart">
                        <div class="column-labels">
                            <label class="product-image">Image</label>
                            <label class="product-details">Product</label>
                            <label class="product-price">Price</label>
                            <label class="product-quantity">Quantity</label>
                            <label class="product-removal">Remove</label>
                            <label class="product-line-price">Total</label>
                        </div>
                        <c:forEach items="${orderProducts}" var="orderProduct">
                        <div class="product">
                            <div class="product-image">
                                <img src="http://placehold.it/100x100&text=${orderProduct.product.productName}">
                            </div>
                            <div class="product-details">
                                <div class="product-title">${orderProduct.product.productName}</div>
                            </div>
                            <div class="product-price">${orderProduct.product.productPrice}</div>
                            <div class="product-quantity">
                                <form:form action="/updateOrderProduct?id=${orderProduct.product.productId}">
                                    <input class="change-quantity-input" type="text" value="${orderProduct.purchasedQuantity}" name="quantity"/>
                                    <input class="change-quantity" type="submit" value="Update"/>
                                </form:form>
                            </div>
                            <div class="product-removal">
                                <form:form action="/deleteFromCart?id=${orderProduct.product.productId}">
                                    <input class="remove-product" type="submit" value="Remove"/>
                                </form:form>
                            </div>
                            <div class="product-line-price">${orderProduct.totalPriceForItem}</div>
                        </div>
                        </c:forEach>

                        <div class="totals">
                            <div class="totals-item">
                                <label>Subtotal</label>
                                <div class="totals-value" id="cart-subtotal">${shoppingCart.subtotal}</div>
                            </div>
                            <c:if test="${shoppingCart.discount.unscaledValue() != 0}">
                                <div class="totals-item">
                                    <label>Discount 10%</label>
                                    <div class="totals-value" id="cart-discount">${shoppingCart.discount}</div>
                                </div>
                            </c:if>
                            <div class="totals-item">
                                <label>Shipping</label>
                                <div class="totals-value" id="cart-shipping">${shoppingCart.shippingCost}</div>
                            </div>
                            <div class="totals-item totals-item-total">
                                <label>Grand Total</label>
                                <div class="totals-value" id="cart-total">${shoppingCart.grandTotal}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
        <div class="jumbotron">
            <h2>Empty Basket</h2>
        </div>
        </c:otherwise>
        </c:choose>

    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
