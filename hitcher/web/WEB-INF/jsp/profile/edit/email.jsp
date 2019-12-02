<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<br/>
<br/>
<br/>
<br/>
<fmt:bundle basename="pagecontent" prefix="profile.">
<form method="post" action="${pageContext.request.contextPath}/profile/edit/email.do"
      class="needs-validation" novalidate>
    <div class="group-wrapper">
        <div class="form-group row">
            <div class="col-sm-3 pl-0">
                <label for="emailInput" class="col-sm-3"><fmt:message key="email"/></label>
            </div>
            <div class="col-sm-7">
                <input type="email" class="form-control" id="emailInput" placeholder="Email" name="email"
                       value="${sessionScope.authorizedUser.email}" required>
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
