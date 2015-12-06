<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/test.css" type="text/css">
<script src="js/jquery-1.6.2.min.js"></script>

<script>
$(function() {
	var testResult = {
			funResult: function() {
				$.ajax({
					async: false,
					type: "POST",
					url: "http://localhost:8080/onlinetest/studentServlet?method=checkAnswer",
					dateType: "json",
					success: function(result) {
						dateTest = $.parseJSON(result);
						aResultTest = {};
						aResultTest.date = dateTest;
						console.log(aResultTest);					
					}				
				});
			},
		};
	testResult.funResult();
	
	$(".sendPage").submit(function() {
		alert(testResult.result);
	});
});
</script>
</head>

<body>
<div class="main">
	<center><h1>此次在线考试科目为：${requestScope.subject.subjectName }</h1>
		<h3>总分：${requestScope.teacherSubject.grade }分 &nbsp;&nbsp;&nbsp; 考试时间：${requestScope.teacherSubject.time } 分钟</h3>
	</center>
	
	
	<form action="studentServlet?method=checkAnswer" method = "post">
		<input type = "hidden" name = "testPaperId" value = "${requestScope.testPaperId }">
		
		<c:forEach items = "${requestScope.paperTypes }" var = "paperType" >
			<B>${paperType.typeName }(每小题${paperType.score} 分，共${paperType.count } x ${paperType.score } = ${paperType.allScore } 分 )</B>	
			<% int i = 1; %><br><br>
							
			<c:choose>
				<c:when test="${paperType.optionCount == 4}">
					<c:forEach items = "${paperType.questions }" var = "question">
						<div class="question">
						<%=i %>.${question.questionName }<br><br>
						<input type = "radio" name = "${question.questionId }" value = "A">A.${question.options.optionA }<br>
						<input type = "radio" name = "${question.questionId }" value = "B">B.${question.options.optionB }<br>
						<input type = "radio" name = "${question.questionId }" value = "C">C.${question.options.optionC }<br>
						<input type = "radio" name = "${question.questionId }" value = "D">D.${question.options.optionD }<br>
						
						<% i++; %><br><br>
						</div>
					</c:forEach>
				</c:when>
				
				<c:when test="${paperType.optionCount == 2}">
					<c:forEach items = "${paperType.questions }" var = "question">
						<div class="question">
						<%=i %>.${question.questionName }<br><br>
						<input type = "radio" name = "${question.questionId }" value = "1">正确<br>
						<input type = "radio" name = "${question.questionId }" value = "0">错误<br>
						
						<% i++; %><br><br>
						</div>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<c:forEach items = "${paperType.questions }" var = "question">
					<div class="question">
						<%=i %>.${question.questionName }<br><br>
						答题区域：<br><br>
						<textarea rows="15" cols="60" name = "${question.questionId }">
						</textarea>
						<br><br>
					
						<% i++; %>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<br><br>			
		</c:forEach>
		
		<input type="submit" class="sendPage" value="提交">
	</form>
</div>

	<div class="right">
		<p>注意事项：</p>
		<p>考试期间请不要关闭页面，否则考试作废；</p>
		<p>考试期间请不要刷新页面，否则试卷会重置，答案不予保存；</p>
		<p>请考生诚信考试；</p>
	</div>
</body>
</html>