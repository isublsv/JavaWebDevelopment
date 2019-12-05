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
        <jsp:param name="isProfileNavbar" value="true"/>
    </jsp:include>
    <br/>
    <br/>
    <br/>
    <div class="container">
        <form action="${pageContext.request.contextPath}/trip/show.do">
            <div class="group-wrapper" id="from">
                <div class="row">
                    <div class="col-lg-3 py-2"><fmt:message key="from"/></div>
                </div>
                <div class="form-group row">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="countryFrom"><fmt:message key="country"/></label>
                        </div>
                        <select class="custom-select" id="countryFrom" name="country_from" required>
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
                        <select class="custom-select" id="cityFrom" name="city_from" required></select>
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
                        <select class="custom-select" id="countryTo" name="country_to" required>
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
                        <select class="custom-select" id="cityTo" name="city_to" required></select>
                    </div>
                </div>
            </div>
            <div class="group-wrapper" id="time">
                <div class="form-group row">
                    <div class="col-sm-5 pl-0">
                        <label for="departure" class="col-sm-5"><fmt:message key="departure.datetime"/></label>
                    </div>
                    <div class="col-sm-7">
                        <input type="date" class="form-control" id="departure" name="departure"
                               required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                </div>
            </div>
            <input type="submit" class="btn btn-primary align-content-center" value="<fmt:message key="find"/>">
        </form>
    </div>
    <br/>
    <jsp:include page="/WEB-INF/jsp/template/footer.jsp"/>
        <script src="<c:url value="/js/find.js"/>"></script>
    </body>
    </html>
</fmt:bundle>
