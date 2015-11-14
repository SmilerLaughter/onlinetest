package student.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import student.domain.Student;
import student.domain.Teacher;
import student.service.StudentService;
import student.service.TeacherSubjectService;



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
	
	
	public void goStartTest(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		
		System.out.println(student); 
		
		List<Teacher> teachers = teacherSubjectService.getTeacherSubjects();
		request.setAttribute("teachers", teachers);
		
		request.getRequestDispatcher("/WEB-INF/studentPages/student.jsp").forward(request, response);
	}
	
	
	public void chooseSubject (HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
	
	
	}
		
		
	
}
