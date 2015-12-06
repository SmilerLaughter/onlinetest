
//教师、科目
$(function() {
	var obj = {
		fun: function() {
			$.ajax({
				async: false,
				type: "POST",
				url: "http://localhost:8080/onlinetest/studentServlet?method=startTest",
				dateType: "json",
				success: function(result) {
					date = $.parseJSON(result);
					aResult = {};
					aResult.date = date;
					console.log(aResult);	
				}				
			});
		},
	};
	obj.fun();
	
	
	//注册模板引擎--教师、科目
	var teacherComplate = Handlebars.compile($("#teacher_complate").html()),
		subjectComplate = Handlebars.compile($("#subjects_complate").html());
	$("#teacher").html(teacherComplate(date));
	$("#subject").html(subjectComplate(date));

	//点击显示科目框	
	$("#teacher :input").click(function() {
		var index = $("#teacher :input").index(this);				

		$("#subject div")
			.eq(index).show()
			.siblings("div").hide();
		$(".subject_outer").slideDown();
		
		//给点击老师的单选按钮添加value属性
		$(this).attr("value", date[index].teacherId);
		
		//给点击老师的相应科目单选按钮添加value属性
		var subjectInputs = $(".subject_box").eq(index).find("input");		
		for(var i = 0; i < subjectInputs.length; i ++) {		
			subjectInputs.eq(i).attr("value", date[index].subjects[i].subjectId);		
		}	
	});
	$("#subject :input").click(function() {
		$("#send").slideDown();
	});
});


//学生信息
$(function() {
	var stu = {
			fn: function() {
				$.ajax({
					async: false,
					type: "POST",
					url: "http://localhost:8080/onlinetest/studentServlet?method=getStudent",
					dateType: "json",
					success: function(result) {
						dateStu = $.parseJSON(result);
						aResultStu = {};
						aResultStu.date = dateStu;
						console.log(aResultStu);     
					}				
				});
			}
		};
	stu.fn();
	
	
	// 考试资格判断 	
	$("#choose").click(function() {
		if(dateStu.flag > 0) {
			showBox(dateStu.flag);
		} else {
			alert("你没有考试资格！");
		}
		
	});
	
	$(".user").html(dateStu.studentName);
	$(".id").html(dateStu.studentId);

	
	
	// 密码验证 
	$(function() {
		
		var oldPsdHoutai = dateStu.password;
		var flag = false;


		// 判断旧密码框 
		$(".oldPsd").blur(function() {
			
			$(this)
				.siblings()
				.css("display","none");

			// 为空 
			if($(this).val() == "") {
				$(this)
					.next()
					.css("display","inline-block");
				$(this).parent().siblings().children().attr("disabled","disabled");

			// 错误 
			} else if($(this).val() !== oldPsdHoutai) {			
				$(this)
					.next()
					.next()
					.css("display","inline-block");
				$(this).parent().siblings().children().attr("disabled","disabled");

			// 正确 
			} else {
				$(this)
					.next()
					.next()
					.next()
					.css("display","inline-block");
					$(this).parent().siblings().children().removeAttr("disabled");
			}
		}).focus(function() {
			$(this)
				.siblings()
				.css("display","none");
			$(this).parent().next().children().eq(1).css("display","none");
		});

		// 判断新密码框是否为空 
		$(".newPsd").blur(function() {
			if($(this).val() == "") {
				$(this).next().css("display","inline-block");
			} else {
				$(this).next().css("display","none");
			}
		}).focus(function() {
			$(this).next().css("display","none");
		});

		// 判断两次输入的新密码是否一致 
		$(".subNewPsd").blur(function() {
			flag = false;
			$(this)
				.siblings()
				.css("display","none");

			if($(".newPsd").val() !== $(".subNewPsd").val()) {		
				$(this)
					.next()
					.css("display","inline-block");
			} else {
				$(this)
					.next()
					.next()
					.css("display","inline-block");

				// 验证通过 
				flag = true;
			}
			if($(this).val()) {
				$(this)
					.next()
					.next()
					.css("display","none");
			} 
		}).focus(function() {
			$(this)
				.siblings()
				.css("display","none");
		});

		// 提交验证 
		$(".psdForm").submit(function() {	
			if($(".newPsd").val() == "") {
				$(".newPsd")
					.next()
					.css("display","inline-block");
			}
			if($(".subNewPsd").val() == "") {
				$(".subNewPsd")
					.next()
					.next()
					.css("display","inline-block");
			} else {
				$(".subNewPsd")
				.next()
				.next()
				.css("display","none");
			}
			if(!flag) {				
				if( $(".info_change :input").val() == "") {
					alert("您未填写任何信息");
				}			
				return false;					
			} else {
				alert("修改成功！");
			}
		});
	});	
});


//显示成绩
$(function() {
	var grade = {
			fnGrade: function() {
				$.ajax({
					async: false,
					type: "POST",
					url: "http://localhost:8080/onlinetest/studentServlet?method=getTestGrade",
					dateType: "json",
					success: function(result) {
						dateGrade = $.parseJSON(result);
						aResultGrade = {};
						aResultGrade.date = dateGrade;
						console.log(aResultGrade);  
						console.log(aResultGrade.date[0].grade);  
					}				
				});
			}
		};
	grade.fnGrade();

	//注册模板引擎--成绩
	var gradeComplate = Handlebars.compile($("#grade_complate").html());	
	$(".grade").html(gradeComplate(dateGrade));
	for(var i = 0; i < dateGrade.length; i ++ ) {
		if(dateGrade[i].grade == 0) {
			$(".grade p").eq(i).css("display","none");
		}
	}
});

//tab
$(function() {
	var $title = $("#tab_title li");

	$title.click(function() {

		$(this).addClass("selected")
			.siblings().removeClass("selected");
		var index = $title.index(this);

		$("div#tab>div")
			.eq(index).show()
			.siblings().hide();
	});
});