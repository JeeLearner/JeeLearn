<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<%@include file="/WEB-INF/jsp/common/import-zTree-js.jspf"%>
<div data-table="table" class="panel">

    <ul class="nav nav-tabs">
        <li ${empty param['search.show_eq'] ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/organization/organization/${parent.id}/children">
                <i class="icon-table"></i>
                [${parent.name}]<fmt:message key="tree.child.list" />
            </a>
        </li>
        <li ${param['search.show_eq'] eq 'true' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/organization/organization/${parent.id}/children?search.show_eq=true">
                <i class="icon-table"></i>
                <fmt:message key="tree.show" />
            </a>
        </li>
        <li ${param['search.show_eq'] eq 'false' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/organization/organization/${parent.id}/children?search.show_eq=false">
                <i class="icon-table"></i>
                <fmt:message key="tree.hidden" />
            </a>
        </li>
        <li>
            <a href="${ctx}/admin/sys/organization/organization/${parent.id}/update">
                <i class="icon-reply"></i>
                <fmt:message key="tree.child.backparent" />
            </a>
        </li>
    </ul>
    <%@include file="refreshTreeMessage.jsp"%>

    <div class="row-fluid tool ui-toolbar">
        <div class="span4">
            <div class="btn-group">
                <shiro:hasPermission name="sys:organization:create">
                <a id="appendChild" class="btn btn-custom no-disabled" href="${ctx}/admin/sys/organization/organization/${parent.id}/appendChild?BackURL=<jee:BackURL/>">
                    <i class="icon-file-alt"></i>
                    <fmt:message key="tree.parent.addchild" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:organization:update">
                <a id="updateTree" class="btn btn-custom">
                    <i class="icon-edit"></i>
                    <fmt:message key="crud.update" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:organization:delete">
                <a id="deleteTree" class="btn btn-custom">
                    <i class="icon-trash"></i>
                    <fmt:message key="crud.delete" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:organization:update">
                <div class="btn-group">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-pencil"></i>
                        <fmt:message key="op.more" />
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a id="moveTree" class="btn btn-custom">
                                <i class="icon-move"></i>
                           		<fmt:message key="tree.move" />
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="btn btn-link status-show">
                                <i class="icon-pencil"></i>
                                <fmt:message key="op.check.show" />
                            </a>
                        </li>
                        <li>
                            <a class="btn btn-link status-hide">
                                <i class="icon-pencil"></i>
                                <fmt:message key="op.check.hidden" />
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            </shiro:hasPermission>
        </div>
        <div class="span8">
            <%@include file="listChildrenSearchForm.jsp"%>
        </div>
    </div>
    <%@include file="listChildrenTable.jsp"%>

</div>

<jee:contentFooter/>
<script type="text/javascript">
    $(function() {
        var tableId = "table";
        var urlPrefix = "${ctx}/admin/sys/organization/organization";
        $.btn.initChangeShowStatus(urlPrefix + "/changeStatus", tableId);
        $.zTree.initMaintainBtn(urlPrefix, tableId, ${not empty param.async and param.async});
    });
</script>