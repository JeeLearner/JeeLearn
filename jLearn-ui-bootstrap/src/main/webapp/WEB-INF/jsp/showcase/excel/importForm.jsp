<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<jee:contentHeader/>
<div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a>
				<i class="icon-level-down"></i> <fmt:message key="crud.import" />${type.info}
			</a>
		</li>
		<li>
			<a href="<jee:BackURL />" class="btn btn-link">
				<i class="icon-reply"></i> <fmt:message key="crud.reply" />
			</a>
		</li>
	</ul>

	<form:form id="editForm" method="post" commandName="m" cssClass="form-horizontal" enctype="multipart/form-data">

		<jee:showMessage/>

		<div class="control-group">
			<label for="file" class="control-label"><fmt:message key="op.format" /></label>

			<div class="controls">
				<c:choose>
					<c:when test="${type eq 'csv'}">
						一行一条，以逗号分隔，如：<br/> 第一行是标题 ： 编号,内容<br/> 从第二行开始是内容 ： 1,zhangsan<br/>
					</c:when>
					<c:otherwise>
						一行一条，每个单元格一个，如：<br/> 第一行是标题 ： 编号,内容<br/> 从第二行开始是内容 ： 1,zhangsan<br/>
					</c:otherwise>
				</c:choose>
				注意：如果编号冲突 覆盖内容，否则新增。<br/>
			</div>
		</div>

		<div class="control-group">
			<label for="file" class="control-label"><fmt:message key="op.file" /></label>

			<div class="controls">
				<input id="file" type="file" name="file" class="custom-file-input" />
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary">
                    <i class="icon-level-down"></i>
                    <fmt:message key="crud.import" />
                </button>
				<a href="<jee:BackURL/>" class="btn">
					<i class="icon-reply"></i> 
					<fmt:message key="crud.reply" />
				</a>
			</div>
		</div>

	</form:form>
</div>
<jee:contentFooter/>