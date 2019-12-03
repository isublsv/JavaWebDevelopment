<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language" value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
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

        <link href="<c:url value="/css/profile.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/index.css"/>" rel="stylesheet">

        <jsp:include page="/WEB-INF/jsp/template/links.jsp"/>
    </head>
    <body>
    <jsp:include page="../template/navbar.jsp">
        <jsp:param name="isProfileNavbar" value="true"/>
    </jsp:include>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="container" id="profile-panel">
        <div class="row">
            <div class="col-md-4 mb-3">
                <ul class="nav nav-pills flex-column" id="myTab" role="tablist">
                    <li class="nav-item">
                        <h4 class="dropdown-header"><fmt:message key="info"/></h4>
                        <a class="nav-link active" id="personal-data-tab" data-toggle="tab" href="#personal-data"
                           role="tab"
                           aria-controls="home" aria-selected="true"><fmt:message key="personal.data"/></a>
                        <a class="nav-link" id="preferences-tab" data-toggle="tab" href="#preferences" role="tab"
                           aria-controls="profile" aria-selected="false"><fmt:message key="preferences"/></a>
                        <a class="nav-link" id="license-tab" data-toggle="tab" href="#license" role="tab"
                           aria-controls="contact" aria-selected="false"><fmt:message key="driver.license"/></a>
                        <a class="nav-link" id="email-tab" data-toggle="tab" href="#email" role="tab"
                           aria-controls="contact" aria-selected="false"><fmt:message key="email"/></a>
                    </li>
                    <li class="nav-item">
                        <h4 class="dropdown-header"><fmt:message key="review"/></h4>
                        <a class="nav-link" id="review-received-tab" data-toggle="tab" href="#review-received" role="tab"
                           aria-controls="contact" aria-selected="false"><fmt:message key="review.received"/></a>
                        <a class="nav-link" id="review-left-tab" data-toggle="tab" href="#review-left" role="tab"
                           aria-controls="contact" aria-selected="false"><fmt:message key="review.left"/></a>
                    </li>
                    <li class="nav-item">
                        <h4 class="dropdown-header"><fmt:message key="account"/></h4>
                        <a class="nav-link" id="pass-tab" data-toggle="tab" href="#pass" role="tab"
                           aria-controls="contact" aria-selected="false"><fmt:message key="pass"/></a>
                    </li>
                    <li class="nav-item">
                        <h4 class="dropdown-header"><fmt:message key="registration"/></h4>
                        <p class="text-center ">${sessionScope.authorizedUser.registrationDate.month} / 
                                ${sessionScope.authorizedUser.registrationDate.year}</p>
                    </li>
                </ul>
            </div>
            <div class="col-md-8">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="personal-data" role="tabpanel"
                         aria-labelledby="personal-data-tab">
                        <h3><fmt:message key="personal.data.header"/></h3>
                        <jsp:include page="edit/personal_data.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="preferences" role="tabpanel" aria-labelledby="preferences-tab">
                        <h3><fmt:message key="preferences.header"/></h3>
                        <jsp:include page="edit/preferences.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="license" role="tabpanel" aria-labelledby="license-tab">
                        <h3><fmt:message key="driver.license.header"/></h3>
                        <jsp:include page="edit/driver_info.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="email" role="tabpanel" aria-labelledby="email-tab">
                        <h3><fmt:message key="email.header"/></h3>
                        <jsp:include page="edit/email.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="review-received" role="tabpanel" aria-labelledby="review-tab">
                        <h3><fmt:message key="review.header.received"/></h3>
                        <jsp:include page="review/review_about.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="review-left" role="tabpanel" aria-labelledby="review-tab">
                        <h3><fmt:message key="review.header.left"/></h3>
                        <jsp:include page="review/my_reviews.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="pass" role="tabpanel" aria-labelledby="pass-tab">
                        <h3><fmt:message key="pass.header"/></h3>
                        <jsp:include page="edit/password.jsp"/>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->
    <jsp:include page="../template/footer.jsp"/>
</fmt:bundle>
</body>
</html>
