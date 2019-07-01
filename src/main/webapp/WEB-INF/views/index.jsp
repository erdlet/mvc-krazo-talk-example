<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${msg.get("page.title")}</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>

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
        <h1 class="col-md-12">${msg.get("index.title")}</h1>
    </div>
    <div class="row">
        <div class="col-md-12">
            <a class="btn btn-primary" href="${mvc.uri('BookingController#showNewBookingForm')}">${msg.get("index.btn.new")}</a>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">${msg.get("index.col.bookingNumber")}</th>
                    <th scope="col">${msg.get("index.col.name")}</th>
                    <th scope="col">${msg.get("index.col.status")}</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.bookingNumber}</td>
                        <td>${booking.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${booking.status.name() == 'ACTIVE'}">
                                    <span class="badge badge-success w-100">${msg.get("index.badge.active")}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger w-100">${msg.get("index.badge.cancelled")}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a class="btn btn-link"
                               href="${mvc.uriBuilder('BookingController#showDetailsOfBooking').build(booking.bookingNumber)}">
                                    ${msg.get("index.btn.details")}</a>

                                <%--Alternative with @UriRef --%>
                                <%--                        <a class="btn btn-link" --%>
                                <%--                           th:href="${mvc.uriBuilder('showBookingDetails').build(booking.bookingNumber)}"> --%>
                                <%--                            ${msg.get("index.btn.details")}</a> --%>

                            <form class="d-inline" method="post"
                                  action="${mvc.uriBuilder('BookingController#cancelBooking').build(booking.bookingNumber)}"
                                  onsubmit="return confirm('Do you really want to cancel the booking?');">
                                <input type="hidden" name="_method" value="DELETE">
                                <button type="submit" class="btn btn-link">${msg.get("index.btn.cancel")}</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${bookings.isEmpty()}">
                    <tr>
                        <td>No entries available</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>
</body>
</html>