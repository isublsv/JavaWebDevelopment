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
            <div class="col-md-5">
                <label for="emailInput" class="col-md-5"><fmt:message key="email"/></label>
            </div>
            <div class="col-md-7">
                <input type="email" class="form-control" id="emailInput" placeholder="Email" name="email"
                       value="${requestScope.authorizedUser.email}" pattern="^[\w._-]+@[\w.-]+\.[\w]{2,6}$" required>
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
