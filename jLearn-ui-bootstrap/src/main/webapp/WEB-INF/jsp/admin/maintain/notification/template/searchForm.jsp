<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="searchForm" class="form-inline search-form" data-change-search="true">

    <jeeform:label path="search.id_in"><fmt:message key="notification.template.id"  /></jeeform:label>
    <jeeform:input path="search.id_in" cssClass="input-medium" placeholder="多个使用空格分隔"/>
    &nbsp;
    <jeeform:label path="search.name_like"><fmt:message key="notification.template。name"  /> </jeeform:label>
    <jeeform:input path="search.name_like" cssClass="input-small" placeholder="模糊匹配"/>

    &nbsp;&nbsp;
    <input type="submit" class="btn" value="<fmt:message key='crud.query' />">
	<a class="btn btn-link btn-clear-search"><fmt:message key="crud.clean" /></a>

</form>
