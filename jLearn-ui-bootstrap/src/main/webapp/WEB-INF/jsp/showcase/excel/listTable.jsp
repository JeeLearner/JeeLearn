<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<table id="table" class="sort-table table table-bordered table-hover" data-async="true">
	<thead>
		<tr>
			<th style="width: 80px;">
				<a class="check-all" href="javascript:;"><fmt:message key="crud.all" /></a>
				| 
				<a class="reverse-all" href="javascript:;"><fmt:message key="crud.unall" /></a>
			</th>
			<th style="width: 100px" sort="id"><fmt:message key="showcase.excel.table.id" /></th>
        	<th><fmt:message key="showcase.excel.table.content" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="m">
			<tr>
				<td class="check"><input type="checkbox" name="ids" value="${m.id}" /></td>
            <td>
                <a class="btn btn-link btn-edit" href="${ctx}/showcase/excel/${m.id}">${m.id}</a>
            </td>
            <td style="word-wrap: break-word;word-break: break-all;">${m.content}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jee:page page="${page}" />