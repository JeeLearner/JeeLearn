<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<table id="table" class="sort-table table table-bordered table-hover" data-async="true">
    <thead>
    <tr>
        <th style="width: 80px">
            <a class="check-all" href="javascript:;"><fmt:message key="crud.all" /></a>
            |
            <a class="reverse-all" href="javascript:;"><fmt:message key="crud.unall" /></a>
        </th>
        <th style="width: 100px" sort="id"><fmt:message key="notification.template.id" /></th>
        <th style="width: 60px"><fmt:message key="notification.system" /></th>
        <th style="width: 150px" sort="name"><fmt:message key="notification.template。name" /></th>
        <th><fmt:message key="notification.content" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.content}" var="m">
        <tr>
            <td class="check"><input type="checkbox" name="ids" value="${m.id}"></td>
            <td>
                <a class="btn btn-link btn-edit" href="${ctx}/admin/maintain/notification/template/${m.id}">${m.id}</a>
            </td>
            <td>${m.system.info}</td>
            <td>${m.name}</td>
            <td>${m.template}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jee:page page="${page}"/>
