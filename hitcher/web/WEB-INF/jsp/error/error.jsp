<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="error.">
    <!DOCTYPE html>
    <html lang="${language}">
    <head>
        <title><fmt:message key="title"/></title>
        <jsp:include page="/WEB-INF/jsp/template/links.jsp"/>
    </head>
    <body>
    <jsp:include page="../template/navbar.jsp"/>
    <br/><br/><br/><br/>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                    ${requestScope.errorMessage}
            </div>
        </div>
    </div>
    <jsp:include page="../template/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>
