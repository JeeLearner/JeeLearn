<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<div data-table="table" class="panel">

    <ul class="nav nav-tabs">
        <li ${param['search.deleted_eq'] ne 'false' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/maintain/notification/template">
                <i class="icon-table"></i>
                <fmt:message key="notification.template.list" />
            </a>
        </li>
        <li ${param['search.deleted_eq'] eq 'false' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/maintain/notification/template?search.deleted_eq=false">
                <i class="icon-table"></i>
             	<fmt:message key="notification.template.list.deleted" />
            </a>
        </li>
    </ul>

    <jee:showMessage/>

    <div class="row-fluid tool ui-toolbar">
        <div class="span4">
            <div class="btn-group">
                <shiro:hasPermission name="maintain:notificationTemplate:create">
                <a class="btn btn-create">
                    <i class="icon-file-alt"></i>
                    <fmt:message key="crud.create" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="maintain:notificationTemplate:update">
                <a id="update" class="btn btn-update">
                    <i class="icon-edit"></i>
                    <fmt:message key="crud.update" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="maintain:notificationTemplate:delete">
                <a class="btn btn-delete">
                    <i class="icon-trash"></i>
                    <fmt:message key="crud.delete" />
                </a>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="span8">
            <%@include file="searchForm.jsp"%>
        </div>
    </div>
    <%@include file="listTable.jsp"%>

</div>

<jee:contentFooter/>