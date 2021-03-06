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
    <br/>
    <br/>
    <br/>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/trip/offer.do" class="needs-validation" novalidate>
            <div class="group-wrapper" id="from">
                <div class="row">
                    <div class="col-lg-3 py-2"><fmt:message key="from"/></div>
                </div>
                <div class="form-group row">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="countryFrom"><fmt:message key="country"/></label>
                        </div>
                        <select class="custom-select" id="countryFrom" required>
                            <option></option>
                            <c:forEach var="item" items="${requestScope.dest}">
                                <option value="${item.id}">${item.countryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <script>
                    $('#countryFrom').on('change', function () {
                        const country = $('#countryFrom').val();
                        const city = $('#cityFrom');

                        city.empty();

                        <c:forEach var="item" items="${requestScope.dest}">
                        if ((country === "${item.id}")) {
                            <c:forEach var="city" items="${item.cities}">
                            city.append('<option value="${city.id}">${city.cityName}</option>');
                            </c:forEach>
                        }
                        </c:forEach>
                    });
                </script>
                <div class="form-group row">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="cityFrom"><fmt:message key="city"/></label>
                        </div>
                        <select class="custom-select" id="cityFrom" name="from" required></select>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
            </div>
            <div class="group-wrapper" id="to">
                <div class="row">
                    <div class="col-lg-3 py-2"><fmt:message key="to"/></div>
                </div>
                <div class="form-group row">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="countryTo"><fmt:message key="country"/></label>
                        </div>
                        <select class="custom-select" id="countryTo" required>
                            <option></option>
                            <c:forEach var="item" items="${requestScope.dest}">
                                <option value="${item.id}">${item.countryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <script>
                    $('#countryTo').on('change', function () {
                        const country = $('#countryTo').val();
                        const city = $('#cityTo');

                        city.empty();

                        <c:forEach var="item" items="${requestScope.dest}">
                        if ((country === "${item.id}")) {
                            <c:forEach var="city" items="${item.cities}">
                            city.append('<option value="${city.id}">${city.cityName}</option>');
                            </c:forEach>
                        }
                        </c:forEach>
                    });
                </script>
                <div class="form-group row">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="cityTo"><fmt:message key="city"/></label>
                        </div>
                        <select class="custom-select" id="cityTo" name="to" required></select>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
            </div>
            <div class="group-wrapper" id="date">
                <div class="form-group row">
                    <div class="col-md-5">
                        <label for="departure" class="col-md-5"><fmt:message key="departure.datetime"/></label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" id="departure" name="departure"
                               required>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <label for="arrival" class="col-md-5"><fmt:message key="arrival.datetime"/></label>
                    </div>
                    <div class="col-md-7">
                        <input type="date" class="form-control" id="arrival" name="arrival" max="2020-12-31"
                               required>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
            </div>
            <div class="group-wrapper" id="numbers">
                <div class="form-group row">
                    <div class="col-md-5">
                        <label class="col-md-5" for="seats"><fmt:message key="free.seat"/></label>
                    </div>
                    <div class="col-sm-7">
                        <input type="number" class="form-control" id="seats" min="1" name="seats" required>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <label class="col-md-5" for="price"><fmt:message key="show.price"/></label>
                    </div>
                    <div class="col-md-7">
                        <input type="number" class="form-control" id="price" min="1" step="0.5" name="price" required>
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </div>
                </div>
            </div>
            <div class="group-wrapper" id="switches">
                <div class="form-group row">
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="smoking" name="smoking" value="true">
                        <label class="custom-control-label" for="smoking"><fmt:message key="smoking"/></label>
                    </div>
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="pets" name="pets" value="true">
                        <label class="custom-control-label" for="pets"><fmt:message key="pets"/></label>
                    </div>
                </div>
            </div>
            <input type="submit" class="btn btn-primary align-content-center" value="<fmt:message key="offer"/>">
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


