<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<%@include file="/WEB-INF/jsp/common/import-zTree-css.jspf"%>
<%@include file="/WEB-INF/jsp/common/import-zTree-js.jspf"%>

<div class="panel">
	<%@include file="nav.jspf" %>
<jee:showMessage/>
<form method="post" id="moveForm" class="form-horizontal">
	<jee:BackURL hiddenInput="true"/>
	<input type="hidden" id="moveType" name="moveType" value="">
	
    <div class="control-group">
    	<label class="control-label"><fmt:message key="tree.source" /> </label>
    	<div class="controls">
    		<span class="help-inline" style="padding: 4px;">
     		<maintain:showIcon iconIdentity="${source.icon}"/>
     		${source.name}
     	</span>
    	</div>
    </div>
    <div class="control-group">
        <label class="control-label"><fmt:message key="tree.target" /> </label>
        <div class="controls input-append" title="选择目标节点">
            <input type="hidden" id="targetId" name="target" value="${target.id}">
            <input type="text" id="targetName" name="targetName" value="${target.name}" class="validate[required]" readonly="readonly">
            <a id="selectTree"  href="javascript:;">
                <span class="add-on"><i class="icon-chevron-down"></i></span>
            </a>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><fmt:message key="tree.move.totarget" /></label>
        <div class="controls">
            <button id="moveAsPrev" type="submit" class="btn btn-primary">
                <i class="icon-chevron-left"></i>
                <fmt:message key="tree.move.prev" />
            </button>
            <button id="moveAsNext" type="submit" class="btn btn-primary">
                <i class="icon-chevron-right"></i>
                <fmt:message key="tree.move.next" />
            </button>
            <button id="moveAsInner" type="submit" class="btn btn-primary">
                <i class="icon-chevron-down"></i>
                <fmt:message key="tree.move.inner" />
            </button>
        </div>
    </div>
</form>
</div>
<jee:contentFooter/>

<script type="text/javascript">
$(function () {
    var async = ${not empty param.async and param.async eq true};
    var zNodes =[
        <c:forEach items="${trees}" var="m">
        { id:${m.id}, pId:${m.pId}, name:"${m.name}", iconSkin:"${m.iconSkin}", open: true, root : ${m.root},isParent:${m.isParent}, nocheck:${m.nocheck}},
        </c:forEach>
    ];
    $.zTree.initSelectTree({
        zNodes : zNodes,
        urlPrefix : "${ctx}/admin/sys/organization/organization",
        excludeId: "${source.id}",
        async : async,
        select : {
            btn : $("#selectTree,#targetName"),
            id : "targetId",
            name : "targetName"
        },
        autocomplete : {
            enable : true
        }
    });

    $.zTree.initMoveBtn();


    var validationEngine = $("#moveForm").validationEngine({
        validationEventTrigger : "submit"
    });

});
</script>
