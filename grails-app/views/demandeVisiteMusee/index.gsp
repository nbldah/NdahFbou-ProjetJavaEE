<%@ page import="projetjavaee.Musee; projetjavaee.DemandeVisiteMusee" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Demande de visite de musée</title>
    <style type="text/css" media="screen">
    div {
        font-size: smaller;
    }

    .label {
        text-align: right;
    }
    </style>
</head>

<body>
<a href="#list-demandeVisiteMusee" class="skip" tabindex="-1"><g:message
        code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<g:form>
    <fieldset class="form">
        <div class="fieldcontain">
            <table>
                <tr>
                    <td class="label">Musee préféré :</td>
                    <td><g:select name="musee"
                                  from="${session.getAttribute("mesFavoris")}"></g:select></td>
                </tr>
                <tr>
                    <td class="label">Date de début :</td>
                    <td><g:datePicker name="dateDebut" precision="day"
                                      years="${2015..2030}"/></td>
                </tr>
                <tr>
                    <td class="label">Date de fin :</td>
                    <td><g:datePicker name="dateFin" precision="day"
                                      years="${2015..2030}"/></td>
                </tr>
                <tr>
                    <td class="label">Nombre de personnes (max 6) :</td>
                    <td><g:select name="nbPers" from="${1..6}"/></td>
                </tr>
            </table>
        </div>
        <br/>

        <div style="float: left">
            <g:link action="redirecToMusee" controller="demandeVisiteMusee"
                    style="text-align: right">
                <input type="button" value="Retour">
            </g:link>
        </div>

        <div style="float: right">
            <g:actionSubmit action="doValiderDemande"
                            value="Valider la demande"/>
        </div>
    </fieldset>
</g:form>

<g:if test="${!(session.getAttribute("dateValide")) && session.getAttribute("dateValide") != null}">
    <h2 style="text-align: left; color:#DD0707">*La date de début doit être inférieure à la date de fin.</h2>
</g:if>

</body>
</html>
