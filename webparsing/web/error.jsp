<%@ page contentType="text/html;charset=UTF-8" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <title>Error page</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}

<a href="${pageContext.request.contextPath}/index.jsp">Index page</a>
</body>
</html>
