<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="rt" uri="http://epam.gartsmanovich.com/isublsv/tag/rating" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="review.">
    <br/><br/>
    <div class="container">
        <div class="row">
            <div class="col-sm-3"><fmt:message key="rating"/></div>
            <div class="col-sm-3">
                <span class="badge badge-warning badge-pill"><rt:ratingTag list="${requestScope.received}"/></span>
            </div>
        </div>
    </div>
    <br/><br/>
    <div class="container">
        <c:if test="${empty requestScope.received}">
            <div class="list-group">
                <div class="row">
                    <div class="col-sm-12 text-center"><fmt:message key="received.empty"/></div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.received}">
            <div class="list-group">
                <div class="list-group-item">
                    <div class="row">
                        <div class="col-sm-2 text-right">Name</div>
                        <div class="col-sm-8 text-center">Review</div>
                        <div class="col-sm-2 text-left">Rating</div>
                    </div>
                </div>
                <c:forEach var="review" items="${requestScope.received}">
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="row">
                            <div class="col-sm-2 text-center">${review.who.name}</div>
                            <div class="col-sm-8 text-center">${review.text}</div>
                            <div class="col-sm-2 text-center">
                                <span class="badge badge-primary badge-pill">${review.rating}</span>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </c:if>
    </div>
</fmt:bundle>
