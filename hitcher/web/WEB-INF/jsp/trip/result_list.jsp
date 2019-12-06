<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="rt" uri="http://epam.gartsmanovich.com/isublsv/tag/hitTag" %>
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
    <br/>
    <br/>
    <br/>
    <div class="container">
        <c:if test="${empty requestScope.trips}">
            <div class="list-group">
                <div class="row">
                    <div class="col-sm-12 text-center"><fmt:message key="show.empty"/></div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.trips}">
            <div class="list-group">
                <div class="list-group-item">
                    <div class="row">
                        <div class="col-md-2 text-center"><fmt:message key="show.driver"/></div>
                        <div class="col-md-2 text-center"><fmt:message key="show.from"/></div>
                        <div class="col-md-2 text-center"><fmt:message key="show.to"/></div>
                        <div class="col-md-2 text-center"><fmt:message key="show.departure"/></div>
                        <div class="col-md-2 text-center"><fmt:message key="show.arrival"/></div>
                        <div class="col-md-2 text-center"><fmt:message key="show.price"/>Price</div>
                    </div>
                </div>
                <c:forEach var="trip" items="${requestScope.trips}">
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="row">
                            <div class="col-md-2 text-center">${trip.driver.name}</div>
                            <div class="col-md-2 text-center">${trip.from}</div>
                            <div class="col-md-2 text-center">${trip.to}</div>
                            <div class="col-md-2 text-center">${trip.departureDatetime}</div>
                            <div class="col-md-2 text-center">${trip.arrivalDatetime}</div>
                            <div class="col-md-2 text-center">${trip.price}</div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <br/>
    <jsp:include page="/WEB-INF/jsp/template/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>
