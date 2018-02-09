<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="5">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="main.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,800,900" rel="stylesheet">
    <script defer src="https://use.fontawesome.com/releases/v5.0.4/js/all.js"></script>
    <title>Ships - Sea Battle</title>
    <style>
        form {
            text-align: center;
        }

        table {
            border-collapse: collapse;
            display: inline-block;
        }

        td {
            width: 20px;
            height: 20px;
            text-align: center;
            border: 1px solid black;
        }

        td.SHIP {
            background-color: darkgrey;
        }
        td.HIT {
            background-color: red;
        }
        td.MISS {
            background-color: aqua;
        }
    </style>
</head>
<body class="w3-display-container container">

<div class="w3-card-4 w3-display-middle">

    <c:set var="myField" value="${playerGameContext.player.myField}"/>
    <c:set var="enemyField" value="${playerGameContext.player.enemyField}"/>


    <div class="w3-padding">




        <table class="w3-margin">
            <tr>
                <c:forEach var="col" items=" ,A,B,C,D,E,F,G,H,I,J">
                    <td>${col}</td>
                </c:forEach>
            </tr>
            <c:forEach var="row" begin="1" end="10">
                <tr>
                    <td>${row}</td>
                    <c:forEach var="col" items="A,B,C,D,E,F,G,H,I,J">
                        <c:set var="addr" value="${col}${row}"/>
                        <td class="${myField.getCell(addr)}">
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>





        <table class="w3-margin">
            <tr>
                <c:forEach var="col" items=" ,A,B,C,D,E,F,G,H,I,J">
                    <td>${col}</td>
                </c:forEach>
            </tr>
            <c:forEach var="row" begin="1" end="10">
                <tr>
                    <td>${row}</td>
                    <c:forEach var="col" items="A,B,C,D,E,F,G,H,I,J">
                        <c:set var="addr" value="${col}${row}"/>
                        <td class="${enemyField.getCell(addr)}">
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>


    </div>
</div>

</body>
</html>
