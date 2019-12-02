<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.data.">
    <form method="post" action="${pageContext.request.contextPath}/profile/edit/personal_data.do"
          class="needs-validation" novalidate>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="nameInput" class="col-sm-3"><fmt:message key="name"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="nameInput" placeholder="Name" name="name"
                           value="${sessionScope.authorizedUser.name}" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="surnameInput" class="col-sm-3"><fmt:message key="surname"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="surnameInput" placeholder="Surname" name="surname"
                           value="${sessionScope.authorizedUser.surname}" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="patronymicInput" class="col-sm-3"><fmt:message key="patronymic"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="patronymicInput" placeholder="Patronymic"
                           name="patronymic"
                           value="${sessionScope.authorizedUser.patronymic}">
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
        </div>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="phoneInput" class="col-sm-3"><fmt:message key="phone"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="tel" class="form-control" id="phoneInput" placeholder="(xx) xxx-xx-xx" name="phone"
                           pattern="[\+]\d{1,3}\s[\(]\d{2,3}[\)]\s\d{3}[\-]\d{2}[\-]\d{2}" minlength="17" maxlength="20"
                           value="${sessionScope.authorizedUser.phoneNumber}" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
        </div>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="addressInput" class="col-sm-3"><fmt:message key="address"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="addressInput" placeholder="name@example.com"
                           name="address"
                           value="${sessionScope.authorizedUser.address}">
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
