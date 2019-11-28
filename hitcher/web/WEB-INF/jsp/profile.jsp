<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Where you want to go?</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/profile.css"/>" rel="stylesheet">
    
</head>
<body>
<jsp:include page="template/navbar.jsp"/>
<fmt:bundle basename="pagecontent" prefix="profile.">
    <div class="container">
        <div class="row">
            <div class="col-md-2 mb-3">
                <ul class="nav nav-pills flex-column" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="personal-data-tab" data-toggle="tab" href="#personal-data"
                           role="tab"
                           aria-controls="home" aria-selected="true">Personal data</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="preferences-tab" data-toggle="tab" href="#preferences" role="tab"
                           aria-controls="profile" aria-selected="false">Preferences</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="license-tab" data-toggle="tab" href="#license" role="tab"
                           aria-controls="contact" aria-selected="false">Driver License</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="email-tab" data-toggle="tab" href="#email" role="tab"
                           aria-controls="contact" aria-selected="false">Email</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab"
                           aria-controls="contact" aria-selected="false">Email</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pass-tab" data-toggle="tab" href="#pass" role="tab"
                           aria-controls="contact" aria-selected="false">Email</a>
                    </li>
                </ul>
            </div>
            <!-- /.col-md-4 -->
            <div class="col-md-10">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="personal-data" role="tabpanel"
                         aria-labelledby="personal-data-tab">
                        <h3>My Personal data</h3>

                    </div>
                    <div class="tab-pane fade" id="preferences" role="tabpanel" aria-labelledby="preferences-tab">
                        <h3>My preferences</h3>

                    </div>
                    <div class="tab-pane fade" id="license" role="tabpanel" aria-labelledby="license-tab">
                        <h3>My Driver License</h3>

                    </div>
                    <div class="tab-pane fade" id="email" role="tabpanel" aria-labelledby="email-tab">
                        <h3>My Email</h3>

                    </div>
                    <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                        <h3>My Review</h3>

                    </div>
                    <div class="tab-pane fade" id="pass" role="tabpanel" aria-labelledby="pass-tab">
                        <h3>My Password</h3>

                    </div>
                </div>
            </div>
            <!-- /.col-md-8 -->
        </div>


    </div>
    <!-- /.container -->
</fmt:bundle>
<jsp:include page="template/footer.jsp"/>

<!-- Bootstrap core JavaScript -->
<script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Plugin JavaScript -->
<script src="<c:url value="/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
</body>
</html>
