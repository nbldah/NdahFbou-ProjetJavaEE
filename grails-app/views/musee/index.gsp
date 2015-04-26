
<%@ page import="projetjavaee.Musee" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>

    <style type="text/css" media="screen">
    div {
        font-size:smaller;
    }
    </style>

</head>
<body>
<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-musee" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:form>
        <fieldset class="form">
            <div class="fieldcontain">

                <label for="nom">
                    Nom du musée  contient :
                </label>
                <g:textField name="nom"/>

                <label for="codePostal">
                    Le code postal :
                </label>
                <g:form name = "codePostal">
                    <select name="codePostal" size="1">
                        <option>31000</option>
                        <option>31200</option>
                        <option>31300</option>
                        <option>31400</option>
                        <option>31500</option>
                    </select>
                </g:form>
            </div>

            <div class="fieldcontain">
                <label for="rue">
                    Le nom de la rue contient:
                </label>
                <g:textField name="rue"/>
            </div>
            <div style="float: right">
                <g:actionSubmit action="doSearchMusee" value="Rechercher" />
            </div>
        </fieldset>

    </g:form>


    <table>
        <thead>
        <tr>
            <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />

            <g:sortableColumn property="horaireOuverture" title="${message(code: 'musee.horaireOuverture.label', default: 'Horaire Ouverture')}" />

            <g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />

            <g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />

            <g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />

            <th><g:message code="musee.adresse.label" default="Adresse" /></th>

            <th><g:message code="musee.gestionnaire.label" default="Gestionnaire"/></th>

        </tr>

        </thead>

        <tbody>

        <g:each in="${museeInstanceList}" status="i" var="museeInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

                <td>${fieldValue(bean: museeInstance, field: "horaireOuverture")}</td>

                <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>

                <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>


            </tr>

            <tr>
                <g:if test="${!(((List) session.getAttribute("mesFavoris"))?.contains(museeInstance.nom))}">
                    <td><g:link action="ajouterFavoris" params="[nom: museeInstance.nom]"
                                style="text-align: right"><input type="button" value="Ajouter à ma liste"
                                  class="button"/></g:link></td>
                </g:if>
            </tr>
        </g:each>
    </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${museeInstanceCount ?: 0}"/>
    </div>

</div>



<g:if test="${((List) session.getAttribute("mesFavoris"))?.size() > 0}">
    <div>
        <h1>Mes Favoris</h1>
        <br/>
        <table>

            <g:each in="${session.getAttribute("mesFavoris")}" status="i" var="museeInstance">

                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${museeInstance}</td>
                    <td><g:link action="supprimerFavoris"  params="[nom: museeInstance, from: 'musee/index']" style="text-align: right">
                        <input type="button" value="Supprimer"></g:link></td>
                    <td><g:link action="demandePref" controller="demandevisitemusee/index"  params="[nom: museeInstance]"
                                style="text-align: right"><input type="button" value="Faire une demande"></g:link>
                    </td>
                </tr>

            </g:each>

        </table>
    </div>
</g:if>






</body>
</html>
