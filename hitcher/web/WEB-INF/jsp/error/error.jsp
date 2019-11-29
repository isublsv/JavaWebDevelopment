<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:bundle basename="pagecontent" prefix="error.">
    <!DOCTYPE html>
    <html lang="${cookie['lang'].value}">
    <head>
        <title><fmt:message key="title"/></title>
        <style type="text/css">
            .error-template {
                padding: 40px 15px;
                text-align: center;
            }
        </style>
    </head>
    <body>
    <jsp:include page="../template/navbar.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="error-template">
                    <div class="error-details">
                        <fmt:message key="${requestScope.errorMessage}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../template/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>
