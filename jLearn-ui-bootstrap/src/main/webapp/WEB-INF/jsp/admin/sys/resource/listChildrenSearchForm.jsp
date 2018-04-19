    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="searchForm" class="form-inline search-form" data-change-search="true">

    <esform:label path="search.name_like"><fmt:message key='common.name' /></esform:label>
    <esform:input path="search.name_like" cssClass="input-small" placeholder="模糊查询"/>
    &nbsp;&nbsp;
    <input type="submit" class="btn" value="<fmt:message key='crud.query' />">
	<a class="btn btn-link btn-clear-search"><fmt:message key="crud.clean" /></a>

</form>
