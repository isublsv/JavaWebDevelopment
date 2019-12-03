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
                <div class="col-sm-3 pl-0">
                    <label for="numberInput" class="col-sm-3"><fmt:message key="number"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="numberInput" placeholder="<fmt:message key="number"/>"
                           name="number"
                           value="${sessionScope.authorizedUser.driverLicenseNumber}" required>
                    <small><fmt:message key="number.example"/>A22 123456</small>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="modelInput" class="col-sm-3"><fmt:message key="model"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="modelInput" placeholder="<fmt:message key="model"/>"
                           name="model"
                           value="${sessionScope.authorizedUser.carModel}">
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="colorInput" class="col-sm-3"><fmt:message key="color"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="colorInput" placeholder="<fmt:message key="color"/>"
                           name="color"
                           value="${sessionScope.authorizedUser.carColor}">
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
        </div>
        <fmt:bundle basename="pagecontent" prefix="profile.">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="save"/>">
        </fmt:bundle>
    </form>
</fmt:bundle>
