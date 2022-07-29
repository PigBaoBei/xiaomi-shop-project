<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript">
	$(function(){
        // 监听事件, 失去焦点事件
		// 验证用户名称是否真的 可用
		$("#username").blur(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/user?method=checkUsername",
				method: 'POST',
				dataType: 'json',
				data:{
                    username: $(this).val(),
                },
				// 回调函数,把服务器发送的数据,放到了data当中. 是一个json对象.
				success: function(data){
                    // 判断一下,本次业务请求是否成功
                    if(data.code === '0'){
                        $("#usernameMsg").html(data.msg).css("color","green");
                        $("#registerBtn").removeAttr("disabled");
                    }else{
                        $("#usernameMsg").html(data.msg).css("color","red");
                        $("#registerBtn").attr("disabled",true);
                    }
                },
				
				error: function(xhr){
                    console.log(xhr)
                }
			})
		}),
		$("#password").blur(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/user?method=checkPassword",
				method: 'POST',
				dataType: 'json',
				data:{
					password: $(this).val(),
				},
				// 回调函数,把服务器发送的数据,放到了data当中. 是一个json对象.
				success: function(data){
					// 判断一下,本次业务请求是否成功
					if(data.code === '0'){
						$("#passwordMsg").html(data.msg).css("color","green");
						$("#registerBtn").removeAttr("disabled");
					}else{
						$("#passwordMsg").html(data.msg).css("color","red");
						$("#registerBtn").attr("disabled",true);
					}
				},

				error: function(xhr){
					console.log(xhr)
				}
			})
		}),
		$("#checkPassword").blur(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/user?method=contrastPassword",
				method: 'POST',
				dataType: 'json',
				data:{

					checkPassword: $(this).val(),
				},
				// 回调函数,把服务器发送的数据,放到了data当中. 是一个json对象.
				success: function(data){
					// 判断一下,本次业务请求是否成功
					if(data.code === '0'){
						$("#checkMas").html(data.msg).css("color","green");
						$("#registerBtn").removeAttr("disabled");
					}else{
						$("#checkMas").html(data.msg).css("color","red");
						$("#registerBtn").attr("disabled",true);
					}
				},

				error: function(xhr){
					console.log(xhr)
				}
			})
		})
	})



</script>
<title>注册</title>
</head>
<body>
	<div class="regist">
		<div class="regist_center">
			<div class="regist_top">
				<div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
				<div class="right fr">
					<a href="index.jsp" target="_black">小米商城</a>
				</div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="center-block" style="margin-top: 80px;">
				<form class="form-horizontal" action="user?method=register" method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"
								placeholder="Username" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span class="help-block " id="usernameMsg">请输入用户名</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" name="password" id="password"
								class="form-control col-sm-10" placeholder="Password" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="passwordMsg" class="help-block" >请输入6位以上字符</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" name="checkPassword" id="checkPassword" class="form-control col-sm-10"
								placeholder="Password Again" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="checkMas" class="help-block ">两次密码要输入一致哦</span></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" name="email" class="form-control col-sm-10"
								placeholder="Email" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">填写正确邮箱格式</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-8" style="width: 40%">
							<label class="radio-inline"> <input type="radio"
								name="gender" value="男"> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" value="女"> 女
							</label>
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;" disabled/> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div>${registerMsg}</div>
				</form>

			</div>
		</div>
	</div>
	
</body>
</html>