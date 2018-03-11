<%@ tag import="com.learn.common.base.utils.web.taglib.PrettyTimeUtils" %>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="seconds" type="java.lang.Integer" required="true" description="秒" %>
<%=PrettyTimeUtils.prettySeconds(seconds == null ? 0 : seconds)%>