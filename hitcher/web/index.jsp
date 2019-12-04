<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Where you want to go?</title>

    <!-- Custom styles for this template -->
    <link href="<c:url value="css/scrolling-nav.css"/>" rel="stylesheet">
    <link href="<c:url value="css/index.css"/>" rel="stylesheet">
    <jsp:include page="WEB-INF/jsp/template/links.jsp"/>
</head>

<body id="page-top">

<jsp:include page="WEB-INF/jsp/template/navbar.jsp"/>

<fmt:bundle basename="pagecontent" prefix="index.">
    <header class="bg-primary text-white">
        <div class="container text-center">
            <a class="btn btn-lg btn-primary btn-block btn-find-trip text-uppercase font-weight-bold mb-2"
                    href="<c:url value="/find_trip.do"/>"><fmt:message key="find.trip"/>
            </a>
        </div>
    </header>

    <section id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2><fmt:message key="about"/></h2>
                    <p class="lead">
                        <fmt:message key="about.info"/>
                    </p>
                </div>
            </div>
        </div>
    </section>

    <section id="services" class="bg-light">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2><fmt:message key="services"/></h2>
                    <p class="lead">
                        <fmt:message key="services.info"/>
                    </p>
                </div>
            </div>
        </div>
    </section>

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2><fmt:message key="contact"/></h2>
                    <p class="lead"><fmt:message key="contact.info"/></p>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="WEB-INF/jsp/template/footer.jsp"/>
</fmt:bundle>

</body>
</html>
