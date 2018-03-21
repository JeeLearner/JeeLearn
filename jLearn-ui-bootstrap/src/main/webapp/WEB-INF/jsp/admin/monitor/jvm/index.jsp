<%long start = System.currentTimeMillis();%><html>
<head>
<title>JDK1.8 management feature</title>
</head>
<body>
<h1>Java Runtime Info</h1>
<%@include file="runtime.jsp"%><hr>
<h1>JVM OS Info</h1>
<%@include file="OS.jsp"%><hr>
<h1>JVM Memory Info</h1>
<%@include file="memory.jsp"%><hr>

<h1> Execute Cost Time:</h1>
<%=System.currentTimeMillis()-start%>

<h1>Memory Status Code</h1>
<%@include file="status1.jsp"%>
<h1>Browser Info</h1>
<%@include file="info1.jsp" %>

<h1>JVM Thread Info</h1>
<%@include file="thread.jsp"%><hr>
</body>
