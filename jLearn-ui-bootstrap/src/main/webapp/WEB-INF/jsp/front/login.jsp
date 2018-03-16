<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/commom/taglibs.jspf"%>
<es:contentHeader title="用户登录" index="true" />
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<a class="brand" href="#">&nbsp;&nbsp;JeeLearn</a>
		<ul class="nav">
			<li class="active">
				<a href="#">登录</a>
			</li>
			<li>
				<a href="http://blog.csdn.net/IT_lyd" target="_blank">我的博客</a>
			</li>
			<li>
				<a href="https://github.com/JeeLearner/JeeLearn" target="_blank">github</a>
			</li>
		</ul>
		<a class="brand" style="float: right" href="mailto:512013674@qq.com" target="_blank">&nbsp;&nbsp;问题反馈</a>
	</div>
</div>

<div class="container">
	<div class="login">
		<div class="title" align="center">用户登录</div>
		<div class="form">

			<div style="margin-right: 30px;">
				<es:showMessage></es:showMessage>
			</div>

			<form id="loginForm" method="post" class="form-horizontal">
				<es:BackURL hiddenInput="true" />
				<div class="control-group">
					<label for="username">用户名、邮箱或手机号</label>
					<div class="input-prepend">
						<span class="add-on icon-user"></span> <input type="text" id="username" name="username" value="${param.username}" class="input-xlarge validate[required]" placeholder="请输入用户名、邮箱或手机号">
					</div>
				</div>
				<div class="control-group">
					<label for="password">密码</label>
					<div class="input-prepend">
						<span class="add-on icon-key"></span> <input type="password" id="password" name="password" class="input-xlarge validate[required]" placeholder="请输入密码">
					</div>
				</div>
				<%-- jcaptchaEbabled 在JCaptchaValidateFilter设置 --%>
				<c:if test="${jcaptchaEnabled}">
					<div class="control-group">
						<label for="jcaptchaCode">验证码</label>
						<div class="input-prepend">
							<span class="add-on icon-circle-blank"></span> <input type="text" id="jcaptchaCode" name="jcaptchaCode" class="input-medium validate[required,ajax[ajaxJcaptchaCall]]" placeholder="请输入验证码">
						</div>
						<img class="jcaptcha-btn jcaptcha-img" style="margin-left: 10px;" src="${ctx}/jcaptcha.jpg" title="点击更换验证码">
						<a class="jcaptcha-btn btn btn-link">换一张</a>
					</div>
				</c:if>

				<div class="control-group">
					<label class="checkbox remember">
						<input type="checkbox" name="rememberMe" value="true">下次自动登录</label> 
						<input id="submitForm" type="submit" class="btn btn-login pull-left" value="登录">
				</div>

			</form>
		</div>
	</div>
</div>

<footer class="footer">
	<div class="container">
		<p>
			<a href="http://blog.csdn.net/IT_lyd" target="_blank">个人学习专用</a>
		</p>
		<p>本站仅供学习使用，请勿用于商业</p>
		<ul class="footer-links">
			<li>
				<a href="http://blog.csdn.net/IT_lyd">博客</a>
			</li>
			<li class="muted">·</li>
			<li>
				<a href="https://github.com/JeeLearner/JeeLearn/issues?state=open" target="_blank">问题反馈</a>
			</li>
			<li class="muted">·</li>
			<li>
				<a href="https://github.com/JeeLearner/JeeLearn" target="_blank">项目主页</a>
			</li>
		</ul>
	</div>
</footer>
<es:contentFooter />
<script type="text/javascript">
	$(function() {
		$("#username").focus();
		$(".jcaptcha-btn").click(function() {
			var img = $(".jcaptcha-img");
			var imageSrc = img.attr("src");
			if(imageSrc.indexOf("?") > 0) {
				imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
			}
			imageSrc = imageSrc + "?" + new Date().getTime();
			img.attr("src", imageSrc);
		});
		$.validationEngineLanguage.allRules.ajaxJcaptchaCall = {
			"url": "${ctx}/jcaptcha-validate",
			"alertTextLoad": "* 正在验证，请稍等。。。"
		};
		$("#loginForm").validationEngine({
			scroll: false
		});
	});
</script>