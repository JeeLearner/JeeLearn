<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<jee:contentHeader/>
<%@include file="/WEB-INF/jsp/common/admin/import-maintain-js.jspf"%>
<div class="panel">
	<%@include file="nav.jspf" %>
	
	<form:form method="post" commandName="m" id="editForm" cssClass="form-horizontal" enctype="multipart/form-data">
		<jee:showGlobalError commandName="m"/>

        <form:hidden path="id"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
        <form:hidden path="weight"/>
        <form:hidden path="show" id="show"/>
        
        <div class="control-group">
        	<form:label path="name" cssClass="control-label"><fmt:message key="sys.organization.name" /> </form:label>
        	<div class="controls">
        		<form:input path="name" cssClass="validate[required,custom[name]]" placeholder="小于50个字符" />
        	</div>
        </div>
        <div class="control-group">
        	<form:label path="icon" cssClass="control-label"><fmt:message key="sys.organization.icon" /></form:label>
        	<div class="controls">
        		<form:input path="icon" cssClass="input-medium"/><maintain:showIcon iconIdentity="${m.icon}" />
        	</div>
        </div>
        <%-- <div class="control-group">
        	<form:label path="show" cssClass="control-label"><fmt:message key="sys.organization.show" /></form:label>
        	<div class="controls inline-radio">
        		<form:radiobuttons path="show" items="${booleanList}" itemLabel="info" itemValue="value" cssClass="validate[required]"/>
        	</div>
        </div> --%>
        <div class="form-group label-style">
            <form:label path="show" cssClass="control-label"><fmt:message key="sys.organization.show"/></form:label>
			<div class="controls inline-radio">
				<label>
                       <input id="show1" name="show"class="required" checked="checked" type="radio" value="true">
                       <span class="text" for="show1">是</span>
                   </label>
				<label>
                       <input id="show2" name="show" class="required" type="radio" value="false">
                       <span class="text" for="show2">否</span>
                   </label>
			</div>
		</div>
        
        <c:if test="${op eq '新增'}">
        	<c:set var="icon" value="icon-file-alt" />
        </c:if>
        <c:if test="${op eq '修改'}">
            <c:set var="icon" value="icon-edit"/>
        </c:if>
        <c:if test="${op eq '删除'}">
            <c:set var="icon" value="icon-trash"/>
        </c:if>
        <div class="control-group">
        	<div class="controls">
        		<button type="submit" class="btn btn-primary">
        			<i class="${icon }"></i>
        			${op }
        		</button>
        	</div>
        </div>
	</form:form>
</div>
<jee:contentFooter/>

<script type="text/javascript">
	var showval = '${m.show}';
	if(showval=='false'){
		$("#show1").removeAttr('checked')
		$("#show2").attr('checked','checked')
	}else if(showval=='true'){
		$("#show2").removeAttr('checked')
		$("#show1").attr('checked','checked')
	}
	
	$(function(){
		<c:choose>
			<c:when test="${op eq '查看'}">
		        $.app.readonlyForm($("#editForm"), true);
		        $("#show1").attr('disabled','disabled');
                $("#show2").attr('disabled','disabled');
		    </c:when>
		    <c:when test="${op eq '删除'}">
	            //删除时不验证 并把表单readonly
	            $.app.readonlyForm($("#editForm"), ${m.root});
	        </c:when>
		    <c:otherwise>
	            $.validationEngineLanguage.allRules.name = {
	                "regex": /^.{1,50}$/,
	                "alertText": "* 小于50个字符"
	            };
	            var validationEngine = $("#editForm").validationEngine();
	            <jee:showFieldError commandName="m"/>
	        </c:otherwise>
		</c:choose>
		$.maintain.icon.initIconList($("#icon"));
	});
</script>
