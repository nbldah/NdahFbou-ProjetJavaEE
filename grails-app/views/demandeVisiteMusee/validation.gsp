<%--
  Created by IntelliJ IDEA.
  User: Fred
  Date: 26/04/2015
  Time: 18:15
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Confirmation de la demande de visite</title>
</head>

<body>
<g:if test="${(boolean) session.getAttribute("etatDemande")}">
    <h1 style="text-align: center">Votre demande a bien été prise en compte.
        <br/>Vous pouvez consulter l'état de votre demande avec le code suivant: ${session.getAttribute("code")}
    </h1>

    <div style="text-align: center">
        <g:link action="redirecToMusee" controller="demandeVisiteMusee"
                style="text-align: center">
            <input type="button" value="OK">
        </g:link>
    </div>
</g:if>
</body>
</html>