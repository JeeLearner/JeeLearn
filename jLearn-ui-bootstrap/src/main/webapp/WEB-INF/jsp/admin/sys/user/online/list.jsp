<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<c:if test="${empty header['container']}">
<jee:contentHeader/>
<style type="text/css">
    .scroll-pane {
        float: left;
        width: 100%;
        height: 100%;
        overflow: auto;
    }
</style>
</c:if>
<div data-table="table" class="panel" id="panel">

	<ul class="nav nav-tabs">
		<li ${empty param['search.userId_eq'] and empty param['search.userId_gt'] ? 'class="active"' : ''} }>
			<a href="${ctx}/admin/sys/user/online">
				<i class="icon-table"></i>
				<fmt:message key="user.list.all" />
			</a>
		</li>
		<li ${not empty param['search.userId_gt'] ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/user/online?search.userId_gt=0">
                <i class="icon-table"></i>
                <fmt:message key="user.list.login" />
            </a>
        </li>
        <li ${not empty param['search.userId_eq'] ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/user/online?search.userId_eq=0">
                <i class="icon-table"></i>
                <fmt:message key="user.list.anon" />
            </a>
        </li>
	</ul>
	
	<div class="row-fluid tool ui-toolbar">
		<div class="span4">
			<div class="btn-group">
				<shiro:hasPermission name="sys:userOnline or monitor:userOnline"><%-- 等价于sys:userOnline:* 所有权限 --%>
                <a class="btn btn-force-logout">
                    <span class="icon-lightbulb"></span>
                   	 <fmt:message key="user.force" />
                </a>
                </shiro:hasPermission>
			</div>
		</div>
		<div class="span8">
			<%@include file="searchForm.jsp" %>
		</div>
	</div>
	<%@include file="listTable.jsp" %>
</div>
<c:if test="${empty header['container']}">
<jee:contentFooter/>
<%@include file="/WEB-INF/jsp/common/admin/import-sys-js.jspf"%>
<script type="text/javascript">
	$(function(){
		callback();
	});
	function callback(){
		//美化滚动条
		$(".scroll-pane").niceScroll({domfocus:true, hidecursordelay: 2000});
		//为强制退出按钮赋予属性
		$.sys.user.initOnlineListButton();
	}
</script>
</c:if>