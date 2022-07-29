<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<style>
		#pagecode:hover{
			cursor: pointer;
		}
	</style>
	<title>登录</title>
	<script type="text/javascript">
		$(function(){
			//2.点击验证码 跟新验证码
			$("#pagecode").click(function(){
				$(this).attr("src","${pageContext.request.contextPath}/captcha?t="+Date.now());
			});

			//4.两周以内自动登录  友好提示
			$("#autoLogin").click(function(){
				if(this.checked){
					$("#autoLoginMsg").html("公司电脑请勿勾选此项").css("color","red");
				}else{
					$("#autoLoginMsg").html("");
				}
			})

			$("#pagecode").css({
				width: '120px',
				height: '48px'
			})


			$('#username').blur(function(){
				// 验证用户名称的正确性
				$.ajax({
					url: '${pageContext.request.contextPath}/user?method=checkUserStatus',
					method: 'post',
					dataType: 'json',
					data:{
						username: $("#username").val()
					},

					success: function(data){
						// 账号状态是合法的.才能继续往下走.  -> 别说话了.
						// 账号账号是否存在.  没有存在,则注册去.
						// 已经存在着账号:
						// 未激活账号: 弹窗一下.告诉大哥,没有激活.你得激活去.
						// 账号异常了:
						// 联系管理员
						if(data.code === '0'){
							console.log('登录成功')
						}else{
							// 当账号不存在的时候,点击直接去注册页面即可;
							if (confirm(data.msg)) {
								window.location.href = data.data
							}else{

							}
						}
					},

					error: function(xhr){
						console.log(xhr)
					}
				})
			})


		})
	</script>
</head>
<body>
<!-- login -->
<div class="top center">
	<div class="logo center">
		<a href="${pageContext.request.contextPath }/index.jsp" target="_blank"><img src="./image/mistore_logo.png" alt=""></a>
	</div>
</div>
<form  method="post" action="${pageContext.request.contextPath}/user?method=login" class="form center" id="userLogin" >
	<div class="login">
		<div class="login_center">
			<div class="login_top">
				<div class="left fl">会员登录</div>
				<div class="right fr">您还不是我们的会员？<a href="${pageContext.request.contextPath }/register.jsp" target="_self">立即注册</a></div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="login_main center">
				<div class="username">
					<div class="left fl">用户名:&nbsp;</div>
					<div class="right fl">
						<input class="shurukuang" type="text" name="username" id="username" value="${userName}"  placeholder="请输入你的用户名"/>
						<label id="nameMsg"></label>
					</div>
				</div>
				<div class="username">
					<div class="left fl">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</div>
					<div class="right fl">
						<input class="shurukuang" type="password" name="password"  id="pwd" value="${password}"  placeholder="请输入你的密码"/>
					</div>
				</div>
				<div class="username">
					<div class="left fl">验证码:&nbsp;</div>
					<div class="right fl"><input  id="vcode" name="captcha" type="text" placeholder="验证码"/>
						<img  id="pagecode" src="${pageContext.request.contextPath}/captcha"></div>
				</div>
				<div class="username">
					<div class="left fl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div class="right fl"><label id="checkMsg"></label></div>
				</div>
				<div class="username">
					<input id="autoLogin" name="auto" type="checkbox" />&nbsp;&nbsp;两周以内自动登录
					<span id="autoLoginMsg"></span>
				</div>
				<div class="login_submit">
					<input class="submit" type="submit" name="submit" value="立即登录" id="btn"  >
				</div>
				<span style="color:red">${msg}</span>
			</div>
		</div>
	</div>
</form>
<footer>
	<div class="copyright">简体 | 繁体 | English | 常见问题</div>
	<div class="copyright">小米公司版权所有-京ICP备10046444-<img src="./image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号</div>

</footer>
</body>
</html>