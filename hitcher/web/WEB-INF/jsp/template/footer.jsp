<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="language" value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="footer.">
    <footer class="navbar-expand-sm bg-dark justify-content-end navbar-dark fixed-bottom text-center">
        <div class="container">
            <p class="m-0 text-white"><fmt:message key="copyright"/></p>
        </div>
    </footer>
</fmt:bundle>
