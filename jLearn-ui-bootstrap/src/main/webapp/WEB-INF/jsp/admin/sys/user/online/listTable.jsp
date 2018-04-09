<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="scroll-pane">
	<table id="table" class="sort-table table table-bordered table-hover"
            data-async="true" data-async-callback="callback" data-async-container="panel" style="width:1000px;max-width: 1000px;">
        <thead>
        <tr>
            <th style="width: 80px;">
                <a class="check-all" href="javascript:;"><fmt:message key="crud.all" /></a>
                |
                <a class="reverse-all" href="javascript:;"><fmt:message key="crud.unall" /></a>
            </th>
            <th sort="username" style="width: 60px;"><fmt:message key="user.user" /></th>
            <th style="width: 100px"><fmt:message key="user.host.ip" /></th>
            <th style="width: 100px"><fmt:message key="user.system.host.ip" /></th>
            <th style="width: 90px"><fmt:message key="user.login.time" /></th>
            <th style="width: 90px"><fmt:message key="user.visit.lasttime" /></th>
            <th style="width: 50px"><fmt:message key="user.status" /></th>
            <th><fmt:message key="user.user-agent" /></th>
            <th><fmt:message key="user.session.id" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" var="m">
            <tr>
                <td class="check">
                    <input type="checkbox" name="ids" value="${m.id}"/>
                </td>
                <td>
                    <c:if test="${m.userId eq 0}"><fmt:message key="user.visitor" /></c:if>
                    <a href="${ctx}/admin/sys/user/${m.userId}">${m.username}</a>
                </td>
                <td>${m.host}</td>
                <td>${m.systemHost}</td>
                <td><pretty:prettyTime date="${m.startTimestamp}"/></td>
                <td><pretty:prettyTime date="${m.lastAccessTime}"/></td>
                <td>${m.status.info}</td>
                <td>${m.userAgent}</td>
                <td>${m.id}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jee:page page="${page}"/>