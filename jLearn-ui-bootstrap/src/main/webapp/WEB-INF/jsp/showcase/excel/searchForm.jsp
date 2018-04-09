<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="searchForm" class="form-inline search-form" data-change-search="true">
	<jeeform:label path="search.id_in"><fmt:message key="showcase.excel.table.id" /></jeeform:label>
	<jeeform:input path="search.id_in" cssClass="input-medium" placeholder="<fmt:message key='op.query.split.blank' />"/>
	&nbsp;&nbsp;
	<input type="submit" class="btn" value="<fmt:message key='crud.query' />">
	<a class="btn btn-link btn-clear-search"><fmt:message key="crud.clean" /></a>
</form>

