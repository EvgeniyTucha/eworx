<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Eshop</a>
        </div>
        <ul class="nav navbar-nav navigation">
            <li><a href="/productPage">Products</a></li>
            <li><a href="/basket">Basket</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a><span class="glyphicon glyphicon-user"></span>&nbsp;${pageContext.request.userPrincipal.name}</a></li>
            <li><a href="/login" onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Logout</a></li>
        </ul>
    </div>
</nav>