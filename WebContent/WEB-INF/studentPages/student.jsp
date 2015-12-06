<%@page import="student.domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>学生考试界面</title>
	<link rel="stylesheet" href="css/student.css" type="text/css">
	<script src="js/jquery-1.6.2.min.js"></script>
	<script src="js/handlebars-1.0.0.beta.6.js"></script>
	<script src="js/addEvent.js"></script>
	<script src="js/choose.js"></script>	
	<script src="js/json.js"></script>
	
	
</head>

<body>
	<div id="header">
		<h1>在线考试系统</h1>
		<div class="userInfo">
			<p class="user">苏丹</p>
			<p class="id">2014210868</p>
		</div>		
	</div>
	<div id="content">
	
		<!-- 侧栏 -->
		<div class="left">
			<ul class="nav" id="tab_title"> 
				<li class="selected">最近公告</li>
				<li>修改信息</li>
				<li>查看成绩</li>
			</ul>	
			<button id="choose">选择考试科目</button>		
		</div>	
		<!-- 侧栏end -->
		
		<!-- 内容 -->
		<div class="right" id="tab">
		
			<!-- tab切换 -->
			<div class="mod" style="display:block;">
				<div class="tab_ban">
					<h2>最新公告</h2>
					<div class="announce">
						<p>系统正在创建。。。请不要打扰</p>
					</div>					
				</div>
			</div>
			<div class="mod">
				<div class="tab_ban">
					<h2>修改信息</h2>
					<form action="studentServlet?method=updatePassword" method="post" class="psdForm">
						<div class="info_change">
							<p>旧密码：
								<input type="password" name = "oldPassword"  class="oldPsd">
								<span class="empty">请输入原始密码！</span>
								<span class="error">您输入的密码与原密码不匹配！</span>
								<span class="ok">与原密码匹配！</span>
							</p>
							<p>新密码：
								<input type="password" name = "newPassword" class="newPsd">
								<span class="error">请输入新的密码！</span>
							</p>
							<p>确认新密码：
								<input type="password" name = "comfireNewPassword" class="subNewPsd">
								<span class="error">两次输入密码不一致！</span>
								<span class="error">请确认新的密码！</span>
							</p>
						</div>
						<input type="submit" value="保存" class="save">
					</form>
					
				</div>
			</div>
			<div class="mod">				
				<div class="tab_ban">
					<h2>您的成绩单</h2>
					<div class="grade">  
						<!-- handlebars显示
						<p>科目1:10</p>	
						<p>科目1:10</p>
						<p>科目1:10</p>	 -->	
					</div>
				</div>
			</div>
		</div>
		<!-- tab切换end -->	
	</div>
	<!-- 内容end -->
	
	<!-- 浮层 -->
	<div class="box" id="box">
		<h2 id="title">请选择老师</h2>
		<span class="close" id="close">关闭</span>		
		<form action="studentServlet?method=getPaper" method="POST">
			<!--老师-->
			<div class="subject" id="teacher">
				<!-- handlebars显示 -->
			</div> 
			<!-- 科目 -->
			<div class="subject_outer">
				<h2>请选择科目</h2>
				<div  class="subject" id="subject">
					<!-- handlebars显示 -->
				</div>						
				<input type="submit" id="send" class="send" value="提交">
			</div>			 		
		</form>
	</div>
	<!-- 浮层end -->
	
	<!--遮罩层-->
	<div class="cover" id="mask" onselectStart="return false"></div>
	
	<!-- 成绩  handlebars-->
	<script id="grade_complate" type="text/x-handlebars-template">		
		{{#each this}}	
			<p>{{subjectName}}：{{grade}}</p>		
		{{/each}}		
	</script>

	<!-- 老师 handlebars-->
	<script id="teacher_complate" type="text/x-handlebars-template">		
		{{#each this}}	
			<input type="radio" name="teacherId">
			<label>{{teacherName}}</label>			
		{{/each}}		
	</script>
	<!-- 科目 handlebars-->
	<script id="subjects_complate" type="text/x-handlebars-template">		
		{{#each this}}	
			{{#with subjects}}
				<div class="subject_box">
					{{#each this}}							
						<input type="radio" name="subjectId">
						<label>{{subjectName}}</label>			
					{{/each}}
				</div>
			{{/with}}				
		{{/each}}	
	</script>
	
	<script>
		//提交试卷之后的提醒
		var result = "${requestScope.result}";
		
		console.log(result);
		if(result != null && result.length > 0){
			alert(result);
		}
	</script>
</body>
</html>

