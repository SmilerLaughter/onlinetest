package student.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import student.dao.BaseDao;
import student.dao.PaperTypeDao;
import student.dao.SubjectDao;
import student.daoimpl.PaperTypeDaoImpl;
import student.daoimpl.SubjectDaoImpl;
import student.dbutils.DBUtils;
import student.domain.Option;
import student.domain.PaperType;
import student.domain.Question;
import student.domain.Student;
import student.domain.StudentQuestionAnswer;
import student.filter.TransationConnection;
import student.service.TeacherSubjectService;
import student.service.TestPaperService;

public class Test{
	
	private TeacherSubjectService teacherSubjectService = new TeacherSubjectService();
	private TestPaperService testPaperService = new TestPaperService(); 
	private SubjectDao SubjectDao = new SubjectDaoImpl();
	private PaperTypeDao paparTypeDaoImpl= new PaperTypeDaoImpl();
	
	@org.junit.Test
	public void test(){
		int teacherId = 1;
		int subjectId = 2;
		Student student = new Student(1,"学生1","123",1);
		
		try {
			Connection connection = DBUtils.getConnection();
			TransationConnection.getTransationConnection().bind(connection);
			List<PaperType> paperTypes = paparTypeDaoImpl.getPaperTypes(2);
			System.out.println(paperTypes.toString());
		//	testPaperService.getTestQuestions(subjectId, teacherId,student);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void addQuestions(){
		int chapterIdRand;
		int questionTypeIdRand;
		String questionName = "问题";
		String correctAnswer = "答案";
		Question question = null;
		TestDaoImpl testDaoImpl = new TestDaoImpl();
		OptionDaoIml optionDaoIml = new OptionDaoIml();
		Connection connection = null;
		
		try {
			 connection = DBUtils.getConnection();
			TransationConnection.getTransationConnection().bind(connection);
			
			for(int i = 1; i < 2000; i++){
				questionTypeIdRand = (int)(Math.random()*3) + 1 ;
				chapterIdRand = (int)(Math.random()*9) + 1;
				questionName = "问题" + i;
				Option option = null;
				
				if (questionTypeIdRand != 2) {
					correctAnswer = "答案" + i;
					question = new Question(i,questionName, chapterIdRand, correctAnswer, questionTypeIdRand, option);
					testDaoImpl.insertQuestion(question);
				}else {
					correctAnswer = "A";
					option = new Option(i, "选项A "+i , "选项B "+ i,"选项C " + i, "选项 D" + i);
					question = new Question(i,questionName, chapterIdRand, correctAnswer, questionTypeIdRand, option);
					testDaoImpl.insertQuestion(question);
					optionDaoIml.insert(option);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			DBUtils.release(connection);
		}
		
	}
	
	@org.junit.Test
	public void insert(){
		try {
			Connection connection = DBUtils.getConnection();
		//	TransationConnection.getTransationConnection().bind(connection);
		BaseDao<StudentQuestionAnswer> baseDao = new BaseDao<StudentQuestionAnswer>();
		String sql = "insert into studentpaperanswers(testQuestionId,studentAnswer,receiveScore,flag) values(?,?,?,?)";
		
		Object[][] params = new Object[1][4];
		for(int i = 0 ; i < 1; i++ ){
			params[i][0] = 8842;
			params[i][1] = " r";
			params[i][2] = 0;
			params[i][3] =1;
		}
		
		System.out.println(params.length + "paramlength");
		
		 baseDao.batch(sql,params); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
