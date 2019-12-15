<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation Bar -->
<c:set var="language" value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="navbar.">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="<c:url value="/index.do?isIndexNavbar=true"/>">WhereUWannaGO
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <c:if test="${param.isIndexNavbar eq 'true'}"> <%--index page--%>
                        <c:choose>
                            <c:when test="${not empty sessionScope.authorizedUser}"> <%--user exist--%>
                                <%--hello, user dropdown--%>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbar_profile_index"
                                       data-toggle="dropdown">
                                        <fmt:message key="greetings"/>, ${sessionScope.authorizedUser.login}
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="<c:url value="/trip/load.do?action=offer"/>">
                                            <fmt:message key="trip.offer"/></a>
                                        <a class="dropdown-item" href="<c:url value="/trip/my_trips.do"/>">
                                            <fmt:message key="trip.mine"/></a>
                                        <a class="dropdown-item" href="<c:url value="/profile.do"/>">
                                            <fmt:message key="link.profile"/></a>
                                            <%--if admin, show user list--%>
                                        <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                                            <a class="dropdown-item" href="<c:url value="/admin/user_list.do"/>"><fmt:message
                                                    key="link.user.list"/></a>
                                        </c:if>
                                        <a class="dropdown-item" href="<c:url value="/logout.do"/>"><fmt:message
                                                key="link.logout"/></a>
                                    </div>
                                </li>
                                <%--about--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#about"><fmt:message
                                            key="link.about"/></a>
                                </li>
                                <%--services--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#services"><fmt:message
                                            key="link.services"/></a>
                                </li>
                                <%--contacts--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#contact"><fmt:message
                                            key="link.contract"/></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <%--login--%>
                                <li class="nav-item">
                                    <a class="nav-link" data-target="#sign-in" data-toggle="modal"
                                       href="javascript:void(0)"><fmt:message key="link.login"/></a>
                                </li>
                                <%--register--%>
                                <li class="nav-item">
                                    <a class="nav-link" data-target="#register" data-toggle="modal"
                                       href="javascript:void(0)"><fmt:message key="link.register"/></a>
                                </li>
                                <%--about--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#about"><fmt:message
                                            key="link.about"/></a>
                                </li>
                                <%--services--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#services"><fmt:message
                                            key="link.services"/></a>
                                </li>
                                <%--contacts--%>
                                <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="#contact"><fmt:message
                                            key="link.contract"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${param.isTripNavbar eq 'true'}"><%--trip page--%>
                        <c:choose>
                            <c:when test="${not empty sessionScope.authorizedUser}">
                                <%--find trip--%>
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="<c:url value="/trip/load.do?action=find"/>">
                                        <fmt:message key="trip.find"/></a>
                                </li>
                                <%--create trip--%>
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="<c:url value="/trip/load.do?action=offer"/>">
                                        <fmt:message key="trip.offer"/></a>
                                </li>
                                <%--hello, user dropdown--%>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbar_profile_trip"
                                       data-toggle="dropdown">
                                        <fmt:message key="greetings"/>, ${sessionScope.authorizedUser.login}
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="<c:url  value="/profile.do"/>">
                                            <fmt:message key="link.profile"/></a>
                                        <a class="dropdown-item" href="<c:url value="/trip/my_trips.do"/>">
                                            <fmt:message key="trip.mine"/></a>
                                            <%--if admin, show user list--%>
                                        <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                                            <a class="dropdown-item" href="<c:url value="/admin/user_list.do"/>"><fmt:message
                                                    key="link.user.list"/></a>
                                        </c:if>
                                        <a class="dropdown-item" href="<c:url value="/logout.do"/>"><fmt:message
                                                key="link.logout"/></a>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <%--find trip--%>
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="<c:url value="/trip/load.do?action=find"/>">
                                        <fmt:message key="trip.find"/></a>
                                </li>
                                <%--login--%>
                                <li class="nav-item">
                                    <a class="nav-link" data-target="#sign-in" data-toggle="modal"
                                       href="javascript:void(0)"><fmt:message key="link.login"/></a>
                                </li>
                                <%--register--%>
                                <li class="nav-item">
                                    <a class="nav-link" data-target="#register" data-toggle="modal"
                                       href="javascript:void(0)"><fmt:message key="link.register"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${param.isProfileNavbar eq 'true'}"> <%--profile page--%>
                        <%--find trip--%>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="<c:url value="/trip/load.do?action=find"/>">
                                <fmt:message key="trip.find"/></a>
                        </li>
                        <%--create trip--%>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="<c:url value="/trip/load.do?action=offer"/>">
                                <fmt:message key="trip.offer"/></a>
                        </li>
                        <%--hello, user dropdown--%>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar_profile"
                               data-toggle="dropdown">
                                <fmt:message key="greetings"/>, ${sessionScope.authorizedUser.login}
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="<c:url value="/profile.do"/>"><fmt:message key="link.profile"/></a>
                                <a class="dropdown-item" href="<c:url value="/trip/my_trips.do"/>">
                                    <fmt:message key="trip.mine"/></a>
                                    <%--if admin, show user list--%>
                                <c:if test="${sessionScope.authorizedUser.role eq 'ADMIN'}">
                                    <a class="dropdown-item" href="<c:url value="/admin/user_list.do"/>"><fmt:message
                                            key="link.user.list"/></a>
                                </c:if>
                                <a class="dropdown-item" href="<c:url value="/logout.do"/>"><fmt:message
                                        key="link.logout"/></a>
                            </div>
                        </li>
                    </c:if>
                        <%--choose language--%>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbar_lang" data-toggle="dropdown">
                            <fmt:message key="link.language"/>
                        </a>
                        <div class="dropdown-menu" id="langDrop" onchange="submit()">
                            <a class="dropdown-item" href="?locale=en">English</a>
                            <a class="dropdown-item" href="?locale=ru">Русский</a>
                            <a class="dropdown-item" href="?locale=be">Беларуская</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <c:if test="${param.isProfileNavbar != 'true'}">
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
                                        <form class="needs-validation-login" novalidate method="post"
                                              action="${pageContext.request.contextPath}/login.do"
                                              id="login-form">
                                            <div class="form-label-group" id="error-login" hidden>
                                                <div class="text-danger text-center" id="error-message-login"></div>
                                            </div>
                                            <div class="form-label-group">
                                                <input type="text" id="inputLoginUsername" class="form-control"
                                                       placeholder="Username" name="login"
                                                       pattern="^[\w]{4,45}$" required autofocus>
                                                <div
                                                        class="invalid-feedback"><fmt:message
                                                        key="form.invalid.feedback"/></div>
                                                <label
                                                        for="inputLoginUsername"><fmt:message
                                                        key="form.username"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="password" id="inputLoginPassword" class="form-control"
                                                       placeholder="Password" name="pass" required>
                                                <div class="invalid-feedback"><fmt:message
                                                        key="form.invalid.feedback"/></div>
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
                                            <button class="btn btn-lg btn-danger btn-block btn-login text-uppercase font-weight-bold mb-2"
                                                    type="reset"><fmt:message key="link.reset"/>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            // Disable form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Get the forms we want to add validation styles to
                    const forms = document.getElementsByClassName('needs-validation-login');
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
        <script>
            jQuery(function ($) {
                
                $('button').click(function () {
                    $('#error-message-login').empty();
                });

                $('#login-form').submit(function (e) {
                    e.preventDefault();
                    let frm = $(this);

                    $.ajax({
                        url: frm.attr('action'),
                        type: frm.attr(`method`),
                        data: frm.serialize(),
                        success: function (response) {
                            const stringified = JSON.stringify(response);
                            const json = JSON.parse(stringified);

                            if (json['redirect'] != null) {
                                window.location.href = json['redirect'];
                            } else if (json['22'] != null) {
                                $('#error-login').removeAttr('hidden');
                                $('#error-message-login').append('<fmt:message key="error.wrong.login"/>');
                            } else {
                                $('#error-login').removeAttr('hidden');
                                $('#error-message-login').append('<fmt:message key="error.user.not.exist"/>');
                            }
                        },
                        error: function (request, status, error) {
                            alert("<fmt:message key="ajax.error"/>");
                        }
                    });
                });
            });
        </script>
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
                                        <form class="needs-validation-registration" method="post" 
                                              action="${pageContext.request.contextPath}/register.do" 
                                              novalidate id="register-form">
                                            <div class="form-label-group" id="error-register" hidden>
                                                <div class="text-danger text-center" id="error-message-register"></div>
                                            </div>
                                            <div class="form-label-group">
                                                <input type="text" id="inputRegisterUsername" class="form-control" placeholder="Username" name="login"
                                                       pattern="^[\w]{4,45}$" required autofocus>
                                                <div class="invalid-feedback"><fmt:message  key="form.invalid.feedback"/></div>
                                                <label for="inputRegisterUsername"><fmt:message key="form.username"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="email" id="inputRegisterEmail" class="form-control" placeholder="Email address" name="email"
                                                       pattern="^[\w._-]+@[\w.-]+\.[\w]{2,6}$" required>
                                                <div class="invalid-feedback"><fmt:message key="form.invalid.feedback"/></div>
                                                <label for="inputRegisterEmail"><fmt:message key="form.email"/></label>
                                            </div>

                                            <hr>

                                            <div class="form-label-group">
                                                <input type="password" id="inputRegisterPassword" class="form-control" placeholder="Password" name="pass"
                                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$" required>
                                                <div class="small text-center"><fmt:message key="password.span"/></div>
                                                <div class="invalid-feedback"><fmt:message key="form.invalid.feedback"/></div>
                                                <label for="inputRegisterPassword"><fmt:message key="form.password"/></label>
                                            </div>

                                            <div class="form-label-group">
                                                <input type="password" id="inputConfirmPassword" class="form-control" placeholder="Password" required>
                                                <div class="invalid-feedback"><fmt:message key="form.invalid.feedback"/></div>
                                                <label for="inputConfirmPassword"><fmt:message key="form.password.confirm"/></label>
                                            </div>
                                            <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                                    type="submit" id="submit_register"><fmt:message key="link.register"/>
                                            </button>
                                            <button class="btn btn-lg btn-danger btn-block btn-login text-uppercase font-weight-bold mb-2"
                                                    type="reset" id><fmt:message key="link.reset"/>
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
        <script>
            const password = document.getElementById("inputRegisterPassword")
                , confirm_password = document.getElementById("inputConfirmPassword");

            function validatePassword(){
                if(password.value !== confirm_password.value) {
                    confirm_password.setCustomValidity("Passwords Don't Match");
                } else {
                    confirm_password.setCustomValidity('');
                }
            }

            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;
        </script>        
        <script>
            // Disable form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Get the forms we want to add validation styles to
                    const forms = document.getElementsByClassName('needs-validation-registration');
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
        <script>
            jQuery(function ($) {

                $('button').click(function () {
                    $('#error-message-register').empty();
                });

                $('#register-form').submit(function (e) {
                    e.preventDefault();
                    let frm = $(this);

                    $.ajax({
                        url: frm.attr('action'),
                        type: frm.attr(`method`),
                        data: frm.serialize(),
                        success: function (response) {
                            const stringified = JSON.stringify(response);
                            const json = JSON.parse(stringified);

                            if (json['redirect'] != null) {
                                window.location.href = json['redirect'];
                            } else if (json['7'] != null) {
                                $('#error-register').removeAttr('hidden');
                                $('#error-message-register').append('<fmt:message key="error.invalid.email"/>');
                            } else if (json['8'] != null) {
                                $('#error-register').removeAttr('hidden');
                                $('#error-message-register').append('<fmt:message key="error.invalid.login"/>');
                            } else if (json['20'] != null) {
                                $('#error-register').removeAttr('hidden');
                                $('#error-message-register').append('<fmt:message key="error.user.exist"/>');
                            } else {
                                $('#error-register').removeAttr('hidden');
                                $('#error-message-register').append('<fmt:message key="error.email.exist"/>');
                            }
                        },
                        error: function (request, status, error) {
                            alert("<fmt:message key="ajax.error"/>");
                        }
                    });
                });
            });
        </script>
    </c:if>
</fmt:bundle>
