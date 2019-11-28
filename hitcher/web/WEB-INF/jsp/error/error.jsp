<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:bundle basename="pagecontent" prefix="error.">
<html>
<head>
    <title>Error page</title>
</head>

<body>
<jsp:include page="../template/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1><fmt:message key="oops"/></h1>
                <div class="error-details">
                    <fmt:message key="${requestScope.errorMessage}"/>
                </div>
                <div class="error-actions">
                    <a href="<c:url value="/"/>" class="btn btn-primary btn-lg">
                        <span class="glyphicon glyphicon-home"></span> <fmt:message key="button.takeMeHome"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
</fmt:bundle>
