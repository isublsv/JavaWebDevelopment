<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="profile.preferences.">
    <br/><br/>
    <form method="post" action="${pageContext.request.contextPath}/profile/edit/preferences.do" class="needs-validation" novalidate>
        <div class="group-wrapper">
            <div class="form-group row">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01"><fmt:message key="com"/></label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="communication">
                        <option value="1"><fmt:message key="com.negative"/></option>
                        <option value="2"><fmt:message key="com.neutral"/></option>
                        <option value="3"><fmt:message key="com.positive"/></option>
                    </select>
                    <fmt:bundle basename="pagecontent" prefix="profile.">
                        <div class="invalid-feedback"><fmt:message key="invalid.feedback"/></div>
                    </fmt:bundle>
                </div>
            </div>
            <div class="form-group row">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect02"><fmt:message key="music"/></label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect02" name="music">
                        <option value="1"><fmt:message key="music.negative"/></option>
                        <option value="2"><fmt:message key="music.neutral"/></option>
                        <option value="3"><fmt:message key="music.positive"/></option>
                    </select>
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
<script type="text/javascript">
    $(document).ready(function () {
        $("#inputGroupSelect01").val('${requestScope.authorizedUser.communication}');
        $("#inputGroupSelect02").val('${requestScope.authorizedUser.music}');
    });
</script>
