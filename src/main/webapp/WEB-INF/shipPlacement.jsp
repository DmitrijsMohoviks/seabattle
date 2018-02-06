<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sea Battle</title>
</head>
<body>

<c:set var="myField" value="${playerGameContext.player.myField}"/>

<c:if test="${myField.invalid}">
    <h2>Your ships are not placed correctly. Please check and correct.</h2>
</c:if>

<form method="post">
    <table style="text-align: center">
        <tr>
            <c:forEach var="col" items=" ,A,B,C,D,E,F,G,H,I,J">
                <td>${col}</td>
            </c:forEach>
        </tr>
        <c:forEach var="row" begin="1" end="10">
            <tr>
                <td>${row}</td>
                <c:forEach var="col" items="A,B,C,D,E,F,G,H,I,J">
                    <td>
                        <c:set var="addr" value="${col}${row}"/>
                        <input type="checkbox" name="addr" value="${addr}" ${myField.hasShip(addr) ? "checked" : ""}>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Start!">
</form>

</body>
</html>
