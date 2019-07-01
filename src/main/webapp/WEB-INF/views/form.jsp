<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${msg.get("page.title")}</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>

    <script src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${mvc.uri('BookingController#showIndex')}">${msg.get("header.brand")}</a>
        </div>
    </nav>
</header>
<main class="container mt-2">
    <div class="row">
        <h1 class="col-md-12">New booking</h1>
    </div>
    <hr class="col-md-12">
    <form method="post" action="${mvc.uri('BookingController#createNewBooking')}" accept-charset="UTF-8">
        <div class="form-row">
            <div class="col-md-6">
                <label for="name">${msg.get("form.label.name")}</label>
                <input id="name" name="name" class="form-control" placeholder="${msg.get("form.label.name.placeholder")}" required>
            </div>
        </div>

        <c:choose>
            <c:when test="${not errors.isEmpty()}">
                <div class="row mt-2">
                    <c:forEach var="error" items="${errors}">
                        <div class="col-md-12">
                            <div class="alert alert-danger">${error.getParamName()}: ${error.getMessage()}</div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
        </c:choose>

        <div class="form-row mt-2">
            <div class="col-md-3">
                <button type="submit" class="btn btn-primary">${msg.get("form.btn.save")}</button>
            </div>
        </div>
    </form>
</main>
</body>
</html>