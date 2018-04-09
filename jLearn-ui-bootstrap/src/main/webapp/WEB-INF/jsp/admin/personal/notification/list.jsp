<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<div data-table="table" class="panel">

	<ul class="nav nav-tabs">
		<li class="active">
            <a href="${ctx}/admin/personal/notification">
                <i class="icon-table"></i>
           		<fmt:message key="notification.my" />
            </a>
        </li>
	</ul>
	
	<div class="row-fluid tool ui-toolbar">
		<table id="table" class="sort-table table table-hover">
            <thead>
            <tr>
                <th sort="system" style="width: 100px;"><fmt:message key="notification.system" /> </th>
                <th sort="date" style="width: 120px;"><fmt:message key="op.date" /></th>
                <th><fmt:message key="notification.content" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="m">
                <tr>
                    <td>${m.system.info}</td>
                    <td><pretty:prettyTime date="${m.date}"/></td>
                    <td>
                        <%-- ${fn:replace(m.content, "{ctx}", ctx)} --%>
                        ${m.content}
                        
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jee:page page="${page}"/>
	</div>
