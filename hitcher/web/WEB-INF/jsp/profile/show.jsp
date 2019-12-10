<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.">
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
        <form method="post">
            <div class="card border-0">
                <input type="hidden" name="id" value="${requestScope.user.id}">
                <div class="card-block">
                    <h4 class="card-title text-center"><fmt:message key="info"/></h4>
                </div>
                <ul class="list-group list-group-flush text-center">
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="data.name"/></div>
                            <div class="col">${requestScope.user.name}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="data.surname"/></div>
                            <div class="col">${requestScope.user.surname}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="data.patronymic"/></div>
                            <div class="col">${requestScope.user.patronymic}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="data.phone"/></div>
                            <div class="col">${requestScope.user.phoneNumber}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="data.address"/></div>
                            <div class="col">${requestScope.user.address}</div>
                        </div>
                    </li>
                    <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                        <li class="list-group-item">
                            <div class="row row-cols-2">
                                <div class="col border-right"><fmt:message key="email"/></div>
                                <div class="col">${requestScope.user.email}</div>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${requestScope.user.driverLicenseNumber != null}">
                        <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                            <li class="list-group-item">
                                <div class="row row-cols-2">
                                    <div class="col border-right"><fmt:message key="driver.license"/></div>
                                    <div class="col">${requestScope.user.driverLicenseNumber}</div>
                                </div>
                            </li>
                        </c:if>
                        <li class="list-group-item">
                            <div class="row row-cols-2">
                                <div class="col border-right"><fmt:message key="driver.model"/></div>
                                <div class="col">${requestScope.user.carModel}</div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row row-cols-2">
                                <div class="col border-right"><fmt:message key="driver.color"/></div>
                                <div class="col">${requestScope.user.carColor}</div>
                            </div>
                        </li>
                    </c:if>
<%--                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="preferences.com"/></div>
                            <div class="col">${requestScope.user.communication}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="preferences.music"/></div>
                            <div class="col">${requestScope.user.music}</div>
                        </div>
                    </li>--%>
                    <li class="list-group-item">
                        <div class="row row-cols-2">
                            <div class="col border-right"><fmt:message key="registration"/></div>
                            <div class="col">${requestScope.user.registrationDate}</div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row text-center">
                            <div class="col">
                                <input class="btn btn-danger" type="submit" value="<fmt:message key="ban"/>"
                                       formaction="${pageContext.request.contextPath}/admin/ban.do"
                                       <c:if test="${sessionScope.authorizedUser.role != 'ADMIN'}">hidden</c:if>>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
    <br/>
    <jsp:include page="/WEB-INF/jsp/template/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>


