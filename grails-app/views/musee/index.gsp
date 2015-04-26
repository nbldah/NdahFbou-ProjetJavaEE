
<%@ page import="projetjavaee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
                <g:paginate controller="Musee" action="list" total="${museeInstanceCount}" />
			</div>
		</div>
	</body>
</html>
