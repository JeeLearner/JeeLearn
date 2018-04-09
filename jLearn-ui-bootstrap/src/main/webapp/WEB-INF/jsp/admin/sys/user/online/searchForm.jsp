<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="searchForm" class="form-inline search-form" data-change-search="true">
	<jeeform:label path="search.username_like"><fmt:message key="user.username" /></jeeform:label>
	<jeeform:input path="search.username_like" cssClass="input-medium" placeholder="<fmt:message key='op.query.blur' />"/>
	&nbsp;&nbsp;
	<input type="submit" class="btn" value="<fmt:message key='crud.query' />">
	<a class="btn btn-link btn-clear-search"><fmt:message key="crud.clean" /></a>
</form>