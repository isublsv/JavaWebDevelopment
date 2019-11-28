<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:bundle basename="pagecontent" prefix="footer.">
    <footer class="py-5 bg-dark">
        <div class="container">
            <p class="m-0 text-center text-white"><fmt:message key="copyright"/></p>
        </div>
    </footer>
</fmt:bundle>
