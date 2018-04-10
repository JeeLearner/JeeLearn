<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<div data-table="table" class="panel">
	<ul class="nav nav-tabs">
        <li class="active">
            <a href="${ctx}/showcase/excel">
                <i class="icon-table"></i>
                	<fmt:message key="op.alldatalist" />
            </a>
        </li>
    </ul>
	<jee:showMessage/>
	<div class="row-fluid tool ui-toolbar">
		<div class="span6">
			<div class="btn-group">
				<shiro:hasPermission name="showcase:excel:create">
                <a class="btn btn-create">
                    <span class="icon-file-alt"></span>
                   	 <fmt:message key="crud.create" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="showcase:excel:update">
                <a class="btn btn-update">
                    <span class="icon-edit"></span>
                   	 <fmt:message key="crud.update" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="showcase:excel:delete">
                <a class="btn btn-delete">
                    <span class="icon-trash"></span>
                   	 <fmt:message key="crud.delete" />
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="showcase:excel:create">
                    <a class="btn btn-custom no-disabled" href="${ctx}/showcase/excel/init" data-toggle="tooltip" data-placement="bottom" data-title="先删除老的数据再生成100w数据">
                        <i class="icon-file-alt"></i>
                        <fmt:message key="showcase.excel.init" />
                    </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="showcase:excel:import or showcase:excel:export">
                    <div class="btn-group last">
                        <a class="btn no-disabled dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-laptop"></i>
                            <fmt:message key="showcase.excel.import-export" />
                            <i class="caret"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <shiro:hasPermission name="showcase:excel:import">
                            <li>
                                <a class="btn no-disabled" href='${ctx}/showcase/excel/import?type=csv'>
                                   <i class="icon-level-down"></i>
                                   <fmt:message key="showcase.excel.importcvs" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" href='${ctx}/showcase/excel/import?type=excel2003'>
                                    <i class="icon-level-down"></i>
                                    <fmt:message key="showcase.excel.importexcel2003" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" href='${ctx}/showcase/excel/import?type=excel2007'>
                                    <i class="icon-level-down"></i>
                                  <fmt:message key="showcase.excel.importexcel2007" />
                                </a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="showcase:excel:export">
                            <li>
                                <a class="btn no-disabled" id="export-csv">
                                    <i class="icon-level-up"></i>
                                    <fmt:message key="showcase.excel.exportcvs" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" id="export-excel-2003-usermodel">
                                    <i class="icon-level-up"></i>
                                    <fmt:message key="showcase.excel.exportexcel2003" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" id="export-excel-2003-sheet">
                                    <i class="icon-level-up"></i>
                                    <fmt:message key="showcase.excel.exportexcel2003sheet" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" id="export-excel-2003-xml">
                                    <i class="icon-level-up"></i>
                                    <fmt:message key="showcase.excel.exportexcel2003sheets" />
                                </a>
                            </li>
                            <li>
                                <a class="btn no-disabled" id='export-excel-2007'>
                                    <i class="icon-level-up"></i>
                                    <fmt:message key="showcase.excel.exportexcel2007multi" />
                                </a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </div>
                </shiro:hasPermission>
			</div>
		</div>
		<div class="span6">
			<%@include file="searchForm.jsp" %>
		</div>
	</div>
	<%@include file="listTable.jsp" %>
	
</div>
<jee:contentFooter/>

<script>
$("#test").click(function () {
    window.location.href = "${ctx}/admin/poll";
</script>