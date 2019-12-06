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
                <div class="col-md-5">
                    <label for="nameInput" class="col-md-5"><fmt:message key="name"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="nameInput" placeholder="Name" name="name"
                           value="${requestScope.authorizedUser.name}" pattern="^[A-Za-zА-Яа-яЎ]{2,20}$" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="surnameInput" class="col-md-5"><fmt:message key="surname"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="surnameInput" placeholder="Surname" name="surname"
                           value="${requestScope.authorizedUser.surname}" pattern="^[A-Za-zА-Яа-яЎ]{2,60}$" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="patronymicInput" class="col-md-5"><fmt:message key="patronymic"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="patronymicInput" placeholder="Patronymic"
                           name="patronymic" value="${requestScope.authorizedUser.patronymic}" 
                           pattern="^[A-Za-zА-Яа-я ,.]{0,60}$">
                </div>
            </div>
        </div>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="phoneInput" class="col-md-5"><fmt:message key="phone"/></label>
                </div>
                <div class="col-md-7">
                    <input type="tel" class="form-control" id="phoneInput" placeholder="+375 (XX) XXX-XX-XX" 
                           name="phone"
                           pattern="[\+]\d{1,3}\s[\(]\d{2,3}[\)]\s\d{3}[\-]\d{2}[\-]\d{2}" minlength="17" maxlength="20"
                           value="${requestScope.authorizedUser.phoneNumber}" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
        </div>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="addressInput" class="col-md-5"><fmt:message key="address"/></label>
                </div>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="addressInput" placeholder="Address"
                           name="address" pattern="^[A-Za-zА-Яа-яЎ\-,.\d /]{0,100}$"
                           value="${requestScope.authorizedUser.address}">
                </div>
            </div>
        </div>
        <fmt:bundle basename="pagecontent" prefix="profile.">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="save"/>">
            <input type="reset" class="btn btn-danger" value="<fmt:message key="reset"/>">
        </fmt:bundle>
    </form>
</fmt:bundle>
