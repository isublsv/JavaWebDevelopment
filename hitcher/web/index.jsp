<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Where you want to go?</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/all.css" rel="stylesheet">

</head>

<body id="page-top">

<jsp:include page="WEB-INF/jsp/navbar.jsp"/>

<fmt:bundle basename="pagecontent" prefix="index.">
<header class="bg-primary text-white">
    <div class="container text-center">
        <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                type="submit"><fmt:message key="find.trip"/>
        </button>
    </div>
</header>

    <section id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2>About this page</h2>
                    <p class="lead">This is a great place to talk about your webpage. This template is purposefully
                        unstyled
                        so you can use it as a boilerplate or starting point for you own landing page designs! This
                        template
                        features:</p>
                </div>
            </div>
        </div>
</section>

    <section id="services" class="bg-light">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2>Services we offer</h2>
                    <p class="lead">We offer the service that provide</p>
                </div>
            </div>
        </div>
</section>

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h2>Contact us</h2>
                    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero odio fugiat
                        voluptatem
                        dolor, provident officiis, id iusto! Obcaecati incidunt, qui nihil beatae magnam et repudiandae
                        ipsa
                        exercitationem, in, quo totam.</p>
                </div>
            </div>
        </div>
</section>
</fmt:bundle>
<jsp:include page="WEB-INF/jsp/footer.jsp"/>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="js/scrolling-nav.js"></script>

</body>

</html>
