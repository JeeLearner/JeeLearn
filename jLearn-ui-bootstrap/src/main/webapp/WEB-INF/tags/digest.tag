<%@ tag import="com.learn.common.base.utils.web.html.HtmlUtils" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="内容" %>
<%@ attribute name="length" type="java.lang.Integer" required="true" description="长度" %>
<%=HtmlUtils.text(content, length)%>