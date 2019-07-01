<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${msg.get("page.title")}</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />

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
        <h1 class="col-md-12">${msg.get("404.title")}</h1>
        <div class="col-md-12">${bookingNumber}</div>
    </div>
</main>
</body>
</html>