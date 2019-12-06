<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.driver.">
    <br/>
    <br/>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/profile/edit/driver_info.do"
          class="needs-validation" novalidate>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="numberInput" class="col-md-5"><fmt:message key="number"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="numberInput" placeholder="<fmt:message key="number"/>"
                           name="number" pattern="^\d[A-Z]{2} [\d]{6}$"
                           value="${requestScope.authorizedUser.driverLicenseNumber}" required>
                    <small><fmt:message key="number.example"/>1AA 123456</small>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="modelInput" class="col-md-5"><fmt:message key="model"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="modelInput" placeholder="<fmt:message key="model"/>"
                           name="model" pattern="^[A-Za-zА-Яа-яЎ ,.]{0,60}$"
                           value="${requestScope.authorizedUser.carModel}" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="colorInput" class="col-md-5"><fmt:message key="color"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="colorInput" placeholder="<fmt:message key="color"/>"
                           name="color" pattern="^[A-Za-zА-Яа-яЎ ,.]{0,60}$"
                           value="${requestScope.authorizedUser.carColor}" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
        </div>
        <fmt:bundle basename="pagecontent" prefix="profile.">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="save"/>">
            <input type="reset" class="btn btn-danger" value="<fmt:message key="reset"/>">
        </fmt:bundle>
    </form>
</fmt:bundle>
