<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="trip.">
    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Where you want to go?</title>

        <jsp:include page="/WEB-INF/jsp/template/links.jsp"/>
    </head>
    <body>
    <jsp:include page="../template/navbar.jsp">
        <jsp:param name="isTripNavbar" value="true"/>
    </jsp:include>
    <br/><br/><br/>
    <div class="container">
        <form method="post" class="needs-validation" novalidate>
            <div class="card border-0">
                <input type="hidden" name="id" value="${requestScope.trip.id}">
                <div class="card-block">
                    <h4 class="card-title text-center"><fmt:message key="info"/></h4>
                </div>
                <ul class="list-group list-group-flush text-center">
                    <li class="list-group-item">
                        <a href="<c:url value="/profile/show.do?id=${requestScope.trip.driver.id}"/>" class="card-link">
                            <div class="row row-cols-2">
                                <div class="col"><fmt:message key="show.driver"/></div>
                                <div class="col">${requestScope.trip.driver.name}</div>
                            </div>
                        </a>
                    </li>
                    <c:if test="${not empty requestScope.trip.passengers}">
                        <li class="list-group-item" id="passengers-parent">
                            <a data-toggle="collapse" href="#passengers" class="card-link">
                                <div class="col-md-12 text-center"><fmt:message key="show.passengers"/></div>
                            </a>
                            <div id="passengers" class="collapse" data-parent="#passengers-parent">
                                <c:forEach var="passenger" items="${requestScope.trip.passengers}">
                                    <a class="card-link" href="<c:url value="/profile/show.do?id=${passenger.id}"/>">
                                        <div class="row">
                                            <div class="col-md-12 text-center">${passenger.name}</div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </li>
                    </c:if>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="from"/></div>
                            <div class="col">${requestScope.trip.from.cityName}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="to"/></div>
                            <div class="col">${requestScope.trip.to.cityName}</div>
                        </div>
                    </li>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd"/>

                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="departure.datetime"/></div>
                            <input type="date" class="form-control col text-center" id="departure" name="departure"
                                   value="${requestScope.trip.departureDatetime}" required
                                   <c:if test="${requestScope.trip.departureDatetime lt currentDate || 
                                   (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled
                            </c:if>>
                            <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="arrival.datetime"/></div>
                            <input type="date" class="form-control col text-center" id="arrival" name="arrival"
                                   value="${requestScope.trip.arrivalDatetime}" required
                                   <c:if test="${requestScope.trip.departureDatetime lt currentDate ||
                                   (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled</c:if>>
                            <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="free.seat"/></div>
                            <input type="number" class="form-control col text-center" id="seats" min="1" name="seats"
                                   value="${requestScope.trip.freeSeats}" required
                                   <c:if test="${requestScope.trip.departureDatetime lt currentDate ||
                                   (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled</c:if>>
                            <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col"><fmt:message key="show.price"/></div>
                            <input type="number" class="form-control col text-center" id="price" min="1" step="0.5"
                                   name="price" value="${requestScope.trip.price}" required
                                   <c:if test="${requestScope.trip.departureDatetime lt currentDate ||
                                   (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled</c:if>>
                            <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-lg-2">
                            <div class="custom-control custom-switch col">
                                <input type="checkbox" class="custom-control-input" id="smoking" value="true"
                                       name="smoking"
                                       <c:if test="${requestScope.trip.smokingAllowed eq 'true'}">checked</c:if>
                                       <c:if test="${requestScope.trip.departureDatetime lt currentDate ||
                                       (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled</c:if>>
                                <label class="custom-control-label" for="smoking"><fmt:message key="smoking"/></label>
                            </div>
                            <div class="custom-control custom-switch col">
                                <input type="checkbox" class="custom-control-input" id="pets" value="true" name="pets"
                                       <c:if test="${requestScope.trip.petsAllowed eq 'true'}">checked</c:if>
                                       <c:if test="${requestScope.trip.departureDatetime lt currentDate ||
                                       (sessionScope.authorizedUser.id != requestScope.trip.driver.id && 
                                   sessionScope.authorizedUser.role != 'ADMIN')}">disabled</c:if>>
                                <label class="custom-control-label" for="pets"><fmt:message key="pets"/></label>
                            </div>
                        </div>
                    </li>
                    <c:if test="${requestScope.trip.departureDatetime ge currentDate}">
                        <li class="list-group-item">
                            <div class="row row-cols-4">
                                <div class="col text-right">
                                    <input class="btn btn-danger" type="submit" value="<fmt:message key="delete"/>"
                                           formaction="${pageContext.request.contextPath}/trip/delete.do"
                                           <c:if test="${sessionScope.authorizedUser.id != 
                                           requestScope.trip.driver.id &&
                                       sessionScope.authorizedUser.role == 'USER'}">hidden</c:if>
                                    >
                                </div>
                                <div class="col text-left">
                                    <input class="btn btn-primary" type="submit" value="<fmt:message key="edit"/>"
                                           formaction="${pageContext.request.contextPath}/trip/edit.do"
                                           <c:if test="${sessionScope.authorizedUser.id != 
                                           requestScope.trip.driver.id &&
                                       sessionScope.authorizedUser.role == 'USER'}">hidden</c:if>
                                    >
                                </div>
                                <c:set var="contains" value="false"/>
                                <c:forEach var="item" items="${requestScope.trip.passengers}">
                                    <c:if test="${item.id eq sessionScope.authorizedUser.id}">
                                        <c:set var="contains" value="true"/>
                                    </c:if>
                                </c:forEach>
                                <div class="col text-right">
                                    <input class="btn btn-primary" type="submit" value="<fmt:message key="register"/>"
                                           formaction="${pageContext.request.contextPath}/trip/register.do"
                                           <c:if test="${sessionScope.authorizedUser.id == requestScope.trip.driver.id 
                                       || contains eq 'true' || sessionScope.authorizedUser.role eq 'ADMIN'}">hidden
                                    </c:if>
                                    >
                                </div>
                                <div class="col text-left">
                                    <input class="btn btn-danger" type="submit" value="<fmt:message key="unregister"/>"
                                           formaction="${pageContext.request.contextPath}/trip/unregister.do"
                                           <c:if test="${sessionScope.authorizedUser.id == requestScope.trip.driver.id 
                                       || contains ne 'true' || sessionScope.authorizedUser.role eq 'ADMIN'}">hidden
                                    </c:if>
                                    >
                                </div>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </div>
        </form>
    </div>
    <br/>
    <jsp:include page="/WEB-INF/jsp/template/footer.jsp"/>
    <script>
        // Disable form submissions if there are invalid fields
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Get the forms we want to add validation styles to
                const forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                const validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
    <script src="<c:url value="/js/trip.js"/>"></script>
    </body>
    </html>
</fmt:bundle>


