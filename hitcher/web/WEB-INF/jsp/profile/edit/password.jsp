<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.pass.">
    <br/><br/>
    <form method="post" action="${pageContext.request.contextPath}/profile/edit/password.do"
          class="needs-validation" novalidate>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="currentPassInput" class="col-md-5"><fmt:message key="current"/></label>
                </div>
                <div class="col-md-7">
                    <input type="password" class="form-control" id="currentPassInput" name="currentpass"
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$" required>
                    <fmt:bundle basename="pagecontent" prefix="navbar.">
                        <div class="small text-center"><fmt:message key="password.span"/></div>
                    </fmt:bundle>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="newPassInput" class="col-md-5"><fmt:message key="new"/></label>
                </div>
                <div class="col-md-7">
                    <input type="password" class="form-control" id="newPassInput" name="newpass" 
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$" required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-5">
                    <label for="newPassConfirmInput" class="col-md-5 text-justify"><fmt:message key="new.confirm"/></label>
                </div>
                <div class="col-md-7">
                    <input type="password" class="form-control" id="newPassConfirmInput"
                           required>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
        </div>
        <fmt:bundle basename="pagecontent" prefix="profile.">
            <input type="submit" class="btn btn-primary" id="submit" value="<fmt:message key="save"/>">
            <input type="reset" class="btn btn-danger" value="<fmt:message key="reset"/>">
        </fmt:bundle>
    </form>
</fmt:bundle>
<script type="text/javascript">
    const password = document.getElementById("newPassInput")
        , confirm_password = document.getElementById("newPassConfirmInput");

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
