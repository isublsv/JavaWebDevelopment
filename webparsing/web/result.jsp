<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Drug List</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #aabcfe;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #aabcfe;
            color: #669;
            background-color: #e8edff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #aabcfe;
            color: #039;
            background-color: #b9c9fe;
            display: none;
        }

        .tg .tg-baqh {
            text-align: center;
            vertical-align: top
        }

        .tg .tg-j0tj {
            background-color: #D2E4FC;
            text-align: center;
            vertical-align: top
        }

        .tg .tg-0lax {
            text-align: center;
            vertical-align: top
        }

        .tg .tg-5cz4 {
            background-color: #D2E4FC;
            border-color: inherit;
            text-align: center;
            vertical-align: middle
        }

        .tabs > input {
            display: none;
        }

        .tabs > div {
            display: none;
            padding: 12px;
            border: 1px solid #C0C0C0;
            background: #FFFFFF;
        }

        .tabs > label {
            display: inline-block;
            padding: 7px;
            margin: 0 -5px -1px 0;
            text-align: center;
            color: #666666;
            border: 1px solid #C0C0C0;
            background: #E0E0E0;
            cursor: pointer;
        }

        .tabs > input:checked + label {
            color: #000000;
            border: 1px solid #C0C0C0;
            border-bottom: 1px solid #FFFFFF;
            background: #FFFFFF;
        }

        .tab_1:checked ~ #txt_1,
        .tab_2:checked ~ #txt_2,
        .tab_3:checked ~ #txt_3,
        .tab_4:checked ~ #txt_4 {
            display: block;
        }
    </style>
</head>
<body>
<table class="tg">
    <caption></caption>
    <tr>
        <th class="tg-baqh" colspan="13" scope="colgroup">Drugs</th>
    </tr>
    <tr>
        <td class="tg-j0tj">Id</td>
        <td class="tg-j0tj">Name</td>
        <td class="tg-j0tj">Group</td>
        <td class="tg-j0tj">Analogs</td>
        <td class="tg-j0tj">Versions</td>
    </tr>
    <c:forEach var="drug" items="${requestScope.drugs}">
        <tr>
            <td class="tg-0lax">${drug.id}</td>
            <td class="tg-0lax">${drug.name}</td>
            <td class="tg-0lax">
                <c:set var="group" value="${drug.group}"/>
                <c:set var="lcGroup" value="${fn:toLowerCase(group)}"/>
                    ${lcGroup}
            </td>
            <td class="tg-0lax">
                <c:forEach var="analog" items="${drug.analogs}">
                    ${analog}<br/>
                </c:forEach>
            </td>
            <td class="tg-0lax">
                <div class="tabs">
                    <input type="radio" name="inset" value="" class="tab_1" id="tab_${drug.id}_1" checked>
                    <label for="tab_${drug.id}_1">Tablets</label>

                    <input type="radio" name="inset" value="" class="tab_2" id="tab_${drug.id}_2">
                    <label for="tab_${drug.id}_2">Drops</label>

                    <input type="radio" name="inset" value="" class="tab_3" id="tab_${drug.id}_3">
                    <label for="tab_${drug.id}_3">Powder</label>

                    <input type="radio" name="inset" value="" class="tab_4" id="tab_${drug.id}_4">
                    <label for="tab_${drug.id}_4">Ointment</label>

                    <div id="txt_1">
                        <table class="tg">
                            <caption></caption>
                            <tr>
                                <th class="tg-baqh" colspan="8" scope="colgroup">Tablets</th>
                            </tr>
                            <tr>
                                <td class="tg-j0tj" colspan="4">Certificate</td>
                                <td class="tg-5cz4" rowspan="2">Dosage</td>
                                <td class="tg-j0tj" colspan="3">Package</td>
                            </tr>
                            <tr>
                                <td class="tg-j0tj">Number</td>
                                <td class="tg-j0tj">Issue date</td>
                                <td class="tg-j0tj">Expiration date</td>
                                <td class="tg-j0tj">Registration</td>
                                <td class="tg-j0tj">Type</td>
                                <td class="tg-j0tj">Quantity</td>
                                <td class="tg-j0tj">Price</td>
                            </tr>
                            <c:forEach var="version" items="${drug.versions}">
                                <c:choose>
                                    <c:when test="${version.type == 'TABLETS'}">
                                        <c:forEach var="pharmacy" items="${version.pharmacies}">
                                            <tr>
                                                <td class="tg-0lax">${pharmacy.certificate.number}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.issueDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.expirationDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.registration}</td>
                                                <td class="tg-0lax">${pharmacy.dosage}</td>
                                                <td class="tg-0lax">
                                                    <c:set var="type" value="${pharmacy.type.type}"/>
                                                    <c:set var="lcType" value="${fn:toLowerCase(type)}"/>
                                                    <c:set var="typeValue" value="${fn:replace(lcType,'_',' ')}"/>
                                                        ${typeValue}
                                                </td>
                                                <td class="tg-0lax">${pharmacy.type.quantity}</td>
                                                <td class="tg-0lax">${pharmacy.type.price}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="txt_2">
                        <table class="tg">
                            <caption></caption>
                            <tr>
                                <th class="tg-baqh" colspan="8" scope="colgroup">Drops</th>
                            </tr>
                            <tr>
                                <td class="tg-j0tj" colspan="4">Certificate</td>
                                <td class="tg-5cz4" rowspan="2">Dosage</td>
                                <td class="tg-j0tj" colspan="3">Package</td>
                            </tr>
                            <tr>
                                <td class="tg-j0tj">Number</td>
                                <td class="tg-j0tj">Issue date</td>
                                <td class="tg-j0tj">Expiration date</td>
                                <td class="tg-j0tj">Registration</td>
                                <td class="tg-j0tj">Type</td>
                                <td class="tg-j0tj">Volume</td>
                                <td class="tg-j0tj">Price</td>
                            </tr>
                            <c:forEach var="version" items="${drug.versions}">
                                <c:choose>
                                    <c:when test="${version.type == 'DROPS'}">
                                        <c:forEach var="pharmacy" items="${version.pharmacies}">
                                            <tr>
                                                <td class="tg-0lax">${pharmacy.certificate.number}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.issueDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.expirationDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.registration}</td>
                                                <td class="tg-0lax">${pharmacy.dosage}</td>
                                                <td class="tg-0lax">
                                                    <c:set var="type" value="${pharmacy.type.type}"/>
                                                    <c:set var="lcType" value="${fn:toLowerCase(type)}"/>
                                                    <c:set var="typeValue" value="${fn:replace(lcType,'_',' ')}"/>
                                                        ${typeValue}
                                                </td>
                                                <td class="tg-0lax">${pharmacy.type.volume}</td>
                                                <td class="tg-0lax">${pharmacy.type.price}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="txt_3">
                        <table class="tg">
                            <caption></caption>
                            <tr>
                                <th class="tg-baqh" colspan="8" scope="colgroup">Powder</th>
                            </tr>
                            <tr>
                                <td class="tg-j0tj" colspan="4">Certificate</td>
                                <td class="tg-5cz4" rowspan="2">Dosage</td>
                                <td class="tg-j0tj" colspan="3">Package</td>
                            </tr>
                            <tr>
                                <td class="tg-j0tj">Number</td>
                                <td class="tg-j0tj">Issue date</td>
                                <td class="tg-j0tj">Expiration date</td>
                                <td class="tg-j0tj">Registration</td>
                                <td class="tg-j0tj">Type</td>
                                <td class="tg-j0tj">Weight</td>
                                <td class="tg-j0tj">Price</td>
                            </tr>
                            <c:forEach var="version" items="${drug.versions}">
                                <c:choose>
                                    <c:when test="${version.type == 'POWDER'}">
                                        <c:forEach var="pharmacy" items="${version.pharmacies}">
                                            <tr>
                                                <td class="tg-0lax">${pharmacy.certificate.number}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.issueDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.expirationDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.registration}</td>
                                                <td class="tg-0lax">${pharmacy.dosage}</td>
                                                <td class="tg-0lax">
                                                    <c:set var="type" value="${pharmacy.type.type}"/>
                                                    <c:set var="lcType" value="${fn:toLowerCase(type)}"/>
                                                    <c:set var="typeValue" value="${fn:replace(lcType,'_',' ')}"/>
                                                        ${typeValue}
                                                </td>
                                                <td class="tg-0lax">${pharmacy.type.weight}</td>
                                                <td class="tg-0lax">${pharmacy.type.price}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="txt_4">
                        <table class="tg">
                            <caption></caption>
                            <tr>
                                <th class="tg-baqh" colspan="9" scope="colgroup">Ointment</th>
                            </tr>
                            <tr>
                                <td class="tg-j0tj" colspan="4">Certificate</td>
                                <td class="tg-5cz4" rowspan="2">Dosage</td>
                                <td class="tg-j0tj" colspan="4">Package</td>
                            </tr>
                            <tr>
                                <td class="tg-j0tj">Number</td>
                                <td class="tg-j0tj">Issue date</td>
                                <td class="tg-j0tj">Expiration date</td>
                                <td class="tg-j0tj">Registration</td>
                                <td class="tg-j0tj">Type</td>
                                <td class="tg-j0tj">Weight</td>
                                <td class="tg-j0tj">Concentration</td>
                                <td class="tg-j0tj">Price</td>
                            </tr>
                            <c:forEach var="version" items="${drug.versions}">
                                <c:choose>
                                    <c:when test="${version.type == 'OINTMENT'}">
                                        <c:forEach var="pharmacy" items="${version.pharmacies}">
                                            <tr>
                                                <td class="tg-0lax">${pharmacy.certificate.number}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.issueDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.expirationDate}</td>
                                                <td class="tg-0lax">${pharmacy.certificate.registration}</td>
                                                <td class="tg-0lax">${pharmacy.dosage}</td>
                                                <td class="tg-0lax">
                                                    <c:set var="type" value="${pharmacy.type.type}"/>
                                                    <c:set var="lcType" value="${fn:toLowerCase(type)}"/>
                                                    <c:set var="typeValue" value="${fn:replace(lcType,'_',' ')}"/>
                                                        ${typeValue}
                                                </td>
                                                <td class="tg-0lax">${pharmacy.type.weight}</td>
                                                <td class="tg-0lax">${pharmacy.type.concentration}</td>
                                                <td class="tg-0lax">${pharmacy.type.price}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Index page</a>
</body>
</html>
