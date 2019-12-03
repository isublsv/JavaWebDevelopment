<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.pass.">
    <br/>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/profile/edit/password.do"
          class="needs-validation" novalidate>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="currentPassInput" class="col-sm-3"><fmt:message key="current"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="currentPassInput" name="currentpass"
                           autocomplete="off" required>
                    <small 
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="newPassInput" class="col-sm-3"><fmt:message key="new"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="newPassInput" name="newpass" onchange="confirmPass()" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3 pl-0">
                    <label for="newPassConfirmInput" class="col-sm-3"><fmt:message key="new.confirm"/></label>
                </div>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="newPassConfirmInput" onchange="confirmPass()" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
        </div>
        <fmt:bundle basename="pagecontent" prefix="profile.">
            <input type="submit" class="btn btn-primary" id="submit" value="<fmt:message key="save"/>" disabled>
        </fmt:bundle>
    </form>
</fmt:bundle>
<script type="text/javascript">
    function confirmPass() {
        document.getElementById('submit').disabled = document.getElementById('newPassInput').value !== document.getElementById('newPassConfirmInput').value;
    }
</script>
