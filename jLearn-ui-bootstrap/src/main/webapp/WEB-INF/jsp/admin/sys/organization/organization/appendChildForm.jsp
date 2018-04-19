<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@include file="/WEB-INF/jsp/common/admin/import-maintain-js.jspf"%>
<jee:contentHeader/>
<div class="panel">
	<%@include file="nav.jspf" %>
	
	<form:form method="post" commandName="child" id="appendChildForm" cssClass="form-horizontal" enctype="multipart/form-data">
		<jee:showGlobalError commandName="child"/>

        <div class="control-group">
        	<label class="control-label"><fmt:message key="tree.parent.name" /> </label>
        	<div class="controls">
        		<maintain:showIcon iconIdentity="${parent.icon}"/>
        		${parent.name}
        	</div>
        </div>
        <div class="control-group">
            <form:label path="name" cssClass="control-label"><fmt:message key="tree.child.name" /></form:label>
            <div class="controls">
                <form:input path="name" cssClass="validate[required,custom[name]]" placeholder="小于50个字符"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="icon" cssClass="control-label"><fmt:message key="tree.child.icon" /></form:label>
            <div class="controls">
                <form:input path="icon" cssClass="input-medium"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="show" cssClass="control-label"><fmt:message key="tree.show" /></form:label>
            <div class="controls inline-radio">
                <form:radiobuttons path="show" items="${booleanList}" itemLabel="info" itemValue="value" cssClass="validate[required]"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <button type="submit" class="btn btn-primary">
                    <i class="icon-file-alt"></i>
                    ${op}
                </button>
            </div>
        </div>
	</form:form>
</div>
<jee:contentFooter/>

<script type="text/javascript">
$(function () {
    $.validationEngineLanguage.allRules.name = {
        "regex": /^.{1,50}$/,
        "alertText": "* 小于50个字符"
    };
    var validationEngine = $("#appendChildForm").validationEngine();
    <jee:showFieldError commandName="child"/>

    $.maintain.icon.initIconList($("#icon"));

});
</script>
