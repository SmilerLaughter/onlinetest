package student.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import student.domain.Grade;
import student.domain.PaperType;
import student.domain.Student;
import student.domain.Teacher;
import student.service.StudentService;
import student.service.TeacherSubjectService;
import student.service.TestPaperService;



/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
 	}
	private StudentService studentService = new StudentService();
	private TeacherSubjectService teacherSubjectService = new TeacherSubjectService();
	private TestPaperService testPaperService = new TestPaperService();
	
	
	//用于本版块测试跳转，并无作用
	public void goStartTest(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		request.getRequestDispatcher("/WEB-INF/studentPages/student.jsp").forward(request, response);
	}	
	
	/*
	 *用于把学生选择考试科目和对应的老师 传给前台
	 */
	public void startTest(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		
		
		List<Teacher> teachers = teacherSubjectService.getTeacherSubjects();//获取全部老师信息
		JSONArray jsonArray = JSONArray.fromObject(teachers);
	    PrintWriter out = response.getWriter();
	    
	    HttpSession session = request.getSession();
		Student  student = (Student)session.getAttribute("student");//获取学生
	    
	    out.print(jsonArray);
	    out.flush();
	    out.close();
		
	}
	
	public void getStudent(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		 
	    HttpSession session = request.getSession();
		Student  student = (Student)session.getAttribute("student");//获取学生
		JSONObject jsonObject = JSONObject.fromObject(student);
		PrintWriter out = response.getWriter();
		    
	    out.print(jsonObject);
	    out.flush();
	    out.close();
	}
		
		
	
	/*
	 *更改密码
	 */
	public void updatePassword(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		Student  student = (Student)session.getAttribute("student");
	    
		String newPassword = request.getParameter("newPassword");//获取参数新密码

		student.setPassword(newPassword);//更新密码
		studentService.update(student);//数据库更新
		session.setAttribute("student", student);//重新放回session中
	
		request.getRequestDispatcher("/WEB-INF/studentPages/student.jsp").forward(request, response);
	}
	
	
	/*
	 * 获取卷子，如果是第一次选题，则随机抽取出题目，如果抽取题后，刷新页面，则把上次抽取出的题显示出来
	 */
	public void getPaper(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		Student student = (Student)session.getAttribute("student");//获取学生信息
		int studentPaperId = student.getStudentPaperId();//判断是否第一次抽取
		
		List<PaperType> paperTypes = null;//卷子每个版块信息
		
		if(studentPaperId > 0){//判断是否抽取过题,若抽取过题则显示抽取的题，若没有抽取过题，则随机抽取题
				
			paperTypes = testPaperService.getQuestionsOnceAgain(studentPaperId,request);//非第一次，从数据库获取
		}else{//没有抽取过题，则随机抽取题
			int subjectId = -1;
			int teacherId = -1;
			
			String subjectIdString = request.getParameter("subjectId");//获取选中的科目参数
			String teacherIdString = request.getParameter("teacherId");//获取选取的老师参数
			
			System.out.println(subjectIdString + "  " + teacherIdString);
			
			try {
				subjectId = Integer.parseInt(subjectIdString);
				teacherId = Integer.parseInt(teacherIdString);
				
				//随机抽取
				if(subjectId > 0 && teacherId > 0){
					paperTypes = testPaperService.getTestQuestions(subjectId,teacherId,student,request);//随机抽取题
				}
				
			} catch (Exception e) {
					
				e.printStackTrace();
			}
			
			//测试代码
			//paperTypes = testPaperService.getTestQuestions(2,1,student,request);
		}
			
		
		request.setAttribute("paperTypes", paperTypes);//把每个卷子板块放入request属性中
		request.getRequestDispatcher("/WEB-INF/studentPages/testPaper.jsp").forward(request, response);//页面重定向
	}
	
	

	/*
	 * 获取学生的答案，放进数据库，若没有主观题，则直接给出学生得分，如果有主观题，则等老师改分后得出总分
	 */
	public void checkAnswer(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
			
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
			StringBuffer result = new StringBuffer("");
			int studentPaperId = student.getStudentPaperId();
			
			int grade = -1;
			grade = testPaperService.checkAnswer(student,request);//学生答案和正确答案比较，并把学生答案放入数据库
			
			if(grade != -1){
				testPaperService.updateGrade(grade, studentPaperId);
				result.append("你此次考试的得分为： " + grade);
			}else {
				result.append("本套试卷含有主观题部分，请耐心等待老师批改卷子！");
			}
			
			request.setAttribute("result", result);
			//System.out.println("{\"result\":\""+result+"\"}");
			
	//		PrintWriter out = response.getWriter();
			
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("result", result);
//			out.print("{\"result\":\""+result+"\"}");
//			out.flush();
//			out.close();

			request.getRequestDispatcher("/WEB-INF/studentPages/student.jsp").forward(request, response);
		}
			
	public void getTestGrade(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Student student = (Student)session.getAttribute("student");
		
		int studentId = student.getStudentId();
		List<Grade> grades = studentService.getGrades(studentId);
		
		JSONArray jsonArray = JSONArray.fromObject(grades);
		PrintWriter out = response.getWriter();
		 
	    out.print(jsonArray);
	    out.flush();
	    out.close();
	}
	
	
	public void test(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		System.out.println(request.getParameter("id"));
	}
}
