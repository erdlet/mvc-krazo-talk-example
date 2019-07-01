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
        <h1 class="col-md-12">${msg.get("details.title")}</h1>
    </div>
    <hr class="col-md-12">
    <div class="row">
       <div class="col-md-6">
           <label for="bookingNumber">${msg.get("details.bookingNumber")}</label>
           <input id="bookingNumber" disabled class="form-control" value="${booking.bookingNumber}">
       </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-6">
            <label for="name">${msg.get("details.name")}</label>
            <input id="name" disabled class="form-control" value="${booking.name}">
        </div>
        <div class="col-md-6">
            <label for="status">${msg.get("details.status")}</label>
            <input id="status" disabled class="form-control" value="${booking.status.name()}">
        </div>
    </div>
</main>
</body>
</html>