<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<table id="table" class="sort-table table table-bordered table-hover" data-async="true">
    <thead>
    <tr>
        <th style="width: 80px;">
            <a class="check-all" href="javascript:;"><fmt:message key="crud.all" /></a>
            |
            <a class="reverse-all" href="javascript:;"><fmt:message key="crud.unall" /></a>
        </th>
        <th style="width: 70px" sort="id"><fmt:message key="common.id" /> </th>
        <th sort="name"><fmt:message key="common.name" /></th>
        <th style="width: 60px;"><fmt:message key="tree.show" /></th>
    </tr>
    <tbody>
    <c:forEach items="${page.content}" var="m">
        <tr>
            <td class="check"><input type="checkbox" name="ids" value="${m.id}" root="${m.root}"></td>
            <td>
                <a class="btn btn-link btn-edit" href="${ctx}/admin/sys/organization/organization/${m.id}">${m.id}</a>
            </td>
            <td>
                <sys:showOrganizationName id="${m.id}"/>
            </td>
            <td>${m.show ? '是' : '否'}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jee:page page="${page}"/>
