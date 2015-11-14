<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="css/student.css" type="text/css">
	<script src="js/addEvent.js"></script>
	<script src="js/choose.js"></script>
</head>


<body>
	<div id="header">
		<h1>在线考试系统</h1>
		<div class="userInfo">
			<p class="user">${sessionScope.student.studentName }</p>
			<p class="id">${sessionScope.student.studentId }</p>
		</div>		
	</div>
	<div id="content">
		<div class="left">
			<ul class="nav">
				<li>查看成绩</li>
				<li>查看成绩</li>
				<li>查看成绩</li>
				<li>查看成绩</li>
			</ul>	
			<button id="choose">选择考试科目</button>		
		</div>	
		<div class="right">
			
		</div>	
	</div>

	<div class="box" id="box">
		<h1 id="title">选择考试科目</h1>
		<a href="javascript:;" class="close" id="close">关闭</a>
		<!-- <ul id="subject" class="subject">
			<li>大学生计算机基础</li>
			<li>c语言</li>
			<li>网页设计与制作</li>
		</ul>
		<div id="teacher" class="teacher">
			<h2>请选择相应科目的老师：</h2>
			<p>aaa</p>
			<p>bbb</p>
			<p>ccc</p>
		</div>
		<p class="result">你选择的科目是：
			<span>大学生计算机基础</span>&nbsp;&nbsp;<span>谢青</span>
		</p>-->		
		<form action="">
			<div class="subject">
				<h2>老师：</h2>
				<c:forEach items = "${requestScope.teachers }" var = "teacher" >
					<input type="radio" name="teacherId" value = "${teacher.teacherId }"><label for="">${teacher.teacherName }</label>

					<h2>科目：</h2>
					<c:forEach items = "${teacher.subjects}" var = "subject" >
						<input type="radio" name="subjectId" value = "${subject.subjectId }" ><label for="">${subject.subjectName }</label>
					</c:forEach>
					
				</c:forEach>
			</div>
	
			<input type="submit" id="send" class="send" value="开始考试"> 
			
		</form>
	</div>
	<!--背景遮罩层-->
	<div class="cover" id="mask" onselectStart="return false"></div>


	<div id="footer">
		<p>信管工作室</p>
	</div>
</body>
</html>

