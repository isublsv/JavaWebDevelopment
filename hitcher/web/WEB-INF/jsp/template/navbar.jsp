<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation Bar -->
<c:set var="language" value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="navbar.">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="<c:url value="/index.do"/>">WhereUWannaGO</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.authorizedUser}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar_profile" data-toggle="dropdown">
                                <fmt:message key="greetings"/>, ${sessionScope.authorizedUser.login}
                            </a>
                            <div class="dropdown-menu">
                                <c:if test="${not param.isProfileNavbar}">
                                <a class="dropdown-item" href="<c:url value="/profile.do"/>"><fmt:message key="link.profile"/></a>
                                </c:if>
                                <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                                    <a class="dropdown-item" href="<c:url value="/user_list.do"/>"><fmt:message key="link.user.list"/></a>
                                </c:if>
                                <a class="dropdown-item" href="<c:url value="/logout.do"/>"><fmt:message key="link.logout"/></a>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" data-target="#sign-in" data-toggle="modal"
                               href="javascript:void(0)"><fmt:message key="link.login"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-target="#register" data-toggle="modal"
                               href="javascript:void(0)"><fmt:message key="link.register"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not param.isProfileNavbar}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#about"><fmt:message key="link.about"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#services"><fmt:message key="link.services"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#contact"><fmt:message key="link.contract"/></a>
                    </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbar_lang" data-toggle="dropdown">
                        <fmt:message key="link.language"/>
                    </a>
                    <div class="dropdown-menu" id="langDrop" onchange="submit()">
                        <a class="dropdown-item" href="<c:url value="?locale=en"/>">English</a>
                        <a class="dropdown-item" href="<c:url value="?locale=ru"/>">Русский</a>
                        <a class="dropdown-item" href="<c:url value="?locale=be"/>">Беларуская</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

    <c:if test="${not param.isProfileNavbar}">
        <!-- The Login Modal -->
        <div class="modal fade" id="sign-in">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="login.welcome"/></h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <div class="login d-flex align-items-center">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12 mx-auto">
                                        <form method="post" action="${pageContext.request.contextPath}/login.do"
                                              id="login-form">
                                            <div id="warning-message">
                                                <p class="text-danger text-center">${requestScope.warningMessage}</p>
                                            </div>
                                            <div id="error-message">
                                                <p class="text-danger text-center">${requestScope.errorMessage}</p>
                                            </div>
                                            <div class="form-label-group">
                                                <input type="text" id="inputLoginUsername" class="form-control"
                                                       placeholder="Username" name="login" required autofocus>
                                                <label for="inputLoginUsername"><fmt:message
                                                        key="form.username"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="password" id="inputLoginPassword" class="form-control"
                                                       placeholder="Password" name="pass" required>
                                                <label for="inputLoginPassword"><fmt:message
                                                        key="form.password"/></label>
                                            </div>

                                            <div class="custom-control custom-checkbox mb-3">
                                                <input type="checkbox" class="custom-control-input" id="customCheck1">
                                                <label class="custom-control-label" for="customCheck1"><fmt:message
                                                        key="remember.pass"/></label>
                                            </div>
                                            <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                                    type="submit"><fmt:message key="link.login"/>
                                            </button>
                                            <div class="text-center">
                                                <a class="small" href="#"><fmt:message
                                                        key="link.forgot.pass"/></a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${not param.isProfileNavbar}">
        <!-- The Register Modal -->
        <div class="modal fade" id="register">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="link.register"/></h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <div class="login d-flex align-items-center">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12 mx-auto">
                                        <form method="post" action="${pageContext.request.contextPath}/register.do">
                                            <div class="form-label-group">
                                                <input type="text" id="inputRegisterUsername" class="form-control" placeholder="Username" name="login"
                                                       required autofocus>
                                                <label for="inputRegisterUsername"><fmt:message key="form.username"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="email" id="inputRegisterEmail" class="form-control" placeholder="Email address" name="email"
                                                       required>
                                                <label for="inputRegisterEmail"><fmt:message key="form.email"/></label>
                                            </div>

                                            <hr>

                                            <div class="form-label-group">
                                                <input type="password" id="inputRegisterPassword" class="form-control" placeholder="Password" name="pass"
                                                       required>
                                                <label for="inputRegisterPassword"><fmt:message key="form.password"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="password" id="inputConfirmPassword" class="form-control" placeholder="Password"
                                                       required>
                                                <label for="inputConfirmPassword"><fmt:message key="form.password.confirm"/></label>
                                            </div>
                                            <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                                    type="submit"><fmt:message key="link.register"/>
                                            </button>
                                            <a class="d-block text-center mt-2 small" data-target="#sign-in" data-toggle="modal"
                                               data-dismiss="modal" href="#"><fmt:message key="link.login"/></a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</fmt:bundle>
<script type="text/javascript" src="<c:url value="/js/login.js"/>"></script>
