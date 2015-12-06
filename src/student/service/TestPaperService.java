package student.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import student.dao.CheckQuestionDao;
import student.dao.OptionDao;
import student.dao.PaperTypeDao;
import student.dao.QuestionDao;
import student.dao.StudentDao;
import student.dao.StudentPaperDao;
import student.dao.StudentQUestionAnswerDao;
import student.dao.SubjectDao;
import student.dao.TeacherSubjectDao;
import student.dao.TestQuestionDao;
import student.daoimpl.CheckQuestionDaoImpl;
import student.daoimpl.OptionDaoImpl;
import student.daoimpl.PaperTypeDaoImpl;
import student.daoimpl.QuestionDaoImpl;
import student.daoimpl.StudentDaoImpl;
import student.daoimpl.StudentPaperDaoImpl;
import student.daoimpl.StudentQuestionAnswerDaoImpl;
import student.daoimpl.SubjectDaoImpl;
import student.daoimpl.TeacherSubjectDaoImpl;
import student.daoimpl.TestQuestionDaoImpl;
import student.domain.CheckQuestion;
import student.domain.Option;
import student.domain.PaperType;
import student.domain.Question;
import student.domain.Student;
import student.domain.StudentPaper;
import student.domain.StudentQuestionAnswer;
import student.domain.Subject;
import student.domain.TeacherSubject;

public class TestPaperService {

	private TeacherSubjectDao teacherSubjectDaoImpl = new TeacherSubjectDaoImpl();
	private PaperTypeDao paperTypeDaoImpl = new PaperTypeDaoImpl();
	private QuestionDao questionDaoImpl = new QuestionDaoImpl();
	private OptionDao optionDaoImpl = new OptionDaoImpl();
	private StudentDao studentDaoImpl = new StudentDaoImpl();
	private StudentPaperDao studentPaperDaoImpl = new StudentPaperDaoImpl();
	private TestQuestionDao testQuestionDaoImpl = new TestQuestionDaoImpl();
	private SubjectDao subjectDaoImpl = new SubjectDaoImpl();
	private CheckQuestionDao checkQuestionDaoImpl = new CheckQuestionDaoImpl();
	private StudentQUestionAnswerDao studentQUestionAnswerDaoImpl = new StudentQuestionAnswerDaoImpl();
	
	public List<PaperType> getTestQuestions(int subjectId, int teacherId, Student student,HttpServletRequest request) { 
		
		TeacherSubject teacherSubject = teacherSubjectDaoImpl.getTeacherSubject(teacherId, subjectId);
		int teacherSubjectId = teacherSubject.getTeacherSubjectId();
		request.setAttribute("teacherSubject", teacherSubject);
		
		Subject subject = subjectDaoImpl.getSubject(subjectId);
		request.setAttribute("subject", subject);
		
		List<PaperType> paperTypes = paperTypeDaoImpl.getPaperTypes(teacherSubjectId);
		
		
		List<Question> questions = new ArrayList<Question>();
		List<Question> allTestQuestions = new ArrayList<Question>();
		
		for(PaperType paperType : paperTypes){
			questions = getPortionQuestions(paperType,subjectId);
			paperType.setQuestions(questions);//把题目装进版块中
			allTestQuestions.addAll(questions);
		}
		                                                                                                                                                              
		//数据库的其他操作
		handleDataBase(student, teacherSubjectId, allTestQuestions,request);
		return paperTypes;
	}
	

	public List<Question> getPortionQuestions(PaperType paperType,int subjectId){//从全部试题中抽取试题
		
		int questionTypeId = paperType.getQuestionTypeId();
		int count = paperType.getCount();
		List<Question> allQuestions = questionDaoImpl.getQuestions(subjectId, questionTypeId);
		List<Question> testQuestions = new ArrayList<Question>();
		
		int begin = (int)(Math.random()*10) +1;
		int allQuestionCount = allQuestions.size();
		int next = (allQuestionCount-begin)/count;
		
		while(testQuestions.size() < count){
			testQuestions.add(allQuestions.get(begin));//往试卷填补问题
			begin = begin + next;
		}
		
		//填补选项
		if(paperType.getOptionCount() == 4){
			addQuestionOption(testQuestions);
		}
		return testQuestions;
		
	}
	
	public void addQuestionOption(List<Question> questions){//为选择题添加选项
		int questionId = 0 ;
		Option option = null;
		for(Question question:questions){
			questionId = question.getQuestionId();
			option = optionDaoImpl.getOption(questionId);
			question.setOptions(option);
		}
	}

	private void handleDataBase(Student student,
			int teacherSubjectId, List<Question> questions,HttpServletRequest request) {
		
		
		//修改学生的考试资格次数
		studentDaoImpl.updateFlag(student.getStudentId());
		HttpSession session = request.getSession();
		//把考试记录插入数据库，
		StudentPaper studentPaper = new StudentPaper();
		
		
		studentPaper.setStudentId(student.getStudentId());
		studentPaper.setTeacherSubjectId(teacherSubjectId);
		studentPaperDaoImpl.insert(studentPaper);
		
		int studentPaperId = studentPaper.getStudentPaperId();
		student.setStudentPaperId(studentPaperId);
		student.setFlag(student.getFlag() - 1);
		
		//把student重新放入session中
		session.setAttribute("student", student);
		
		//抽出的考试题插入数据库，做备份
		testQuestionDaoImpl.insertQuestions(studentPaperId, questions);
		
		
	}


	public List<PaperType> getQuestionsOnceAgain(int studentPaperId,HttpServletRequest request) {
		
		List<PaperType> paperTypes = paperTypeDaoImpl.getPaperTypesWithStudentPaperId(studentPaperId);
		TeacherSubject teacherSubject = teacherSubjectDaoImpl.getTeacherSubjectWithStudentPaperId(studentPaperId);
		int teacherSubjectId = teacherSubject.getTeacherSubjectId();
		Subject subject = subjectDaoImpl.getSubjectWithTeacherSubjectId(teacherSubjectId);
		
		request.setAttribute("subject", subject);
		request.setAttribute("teacherSubject", teacherSubject);
		
		//取出生成卷子的全部试题
		List<Question> questions = questionDaoImpl.getQuestionsWithStudentPaperId(studentPaperId);
		List<Question> portionQuestions = null;
		
		
		int count = 0;
		int optionCount = 0;
		int questionCount = 0;
		
		for(PaperType paperType : paperTypes){
			count = paperType.getCount();
			optionCount = paperType.getOptionCount();
			
			portionQuestions = questions.subList(questionCount, questionCount + count );
			questionCount += count;
			
			if(optionCount == 4){//如果为选择题，就为其添加选项
				addQuestionOption(portionQuestions);
			}
			paperType.setQuestions(portionQuestions);
					
		}

		return paperTypes;
	}


	
	
	public int checkAnswer(Student student, HttpServletRequest request) {

		int studentPaperId = student.getStudentPaperId();
		List<PaperType> paperTypes = paperTypeDaoImpl.getPaperTypesWithStudentPaperId(studentPaperId);
		List<CheckQuestion> questions = checkQuestionDaoImpl.getCheckQuestions(studentPaperId);
		List<CheckQuestion> portionCheckQuestions = null;
		
		//用于添加学生的答案
		List<StudentQuestionAnswer> studentQuestionAnswers = new ArrayList<StudentQuestionAnswer>();
		StudentQuestionAnswer studentQuestionAnswer = null;
		

		//用于取到每一板块的题集
		int count = 0;
		int optionCount = 0;
		int questionCount = 0;
		int score = 0;
		
		int testQuestionId = 0;
		int flag = 0;//是否批改过
		String studentAnswer = null;//学生的答案
		
		
		boolean isGetGrade = true;//记录是否全是客观题，如果是，就计算总分
		int grade = 0;//记录总分
		
		int questionId = 0;
		
		for(PaperType paperType : paperTypes){
			count = paperType.getCount();
			optionCount = paperType.getOptionCount();
			score = paperType.getScore();
			
			//得到每一板块的题集
			portionCheckQuestions = questions.subList(questionCount, questionCount + count);

			for(CheckQuestion checkQuestion : portionCheckQuestions){
				
				int receiveScore = 0;//得到的分数
				testQuestionId = checkQuestion.getTestQuestionId();
				questionId = checkQuestion.getQuestionId();
				studentAnswer = request.getParameter(String.valueOf(questionId));//祛除前后空格
				
				if(studentAnswer != null && studentAnswer.length() > 0){
					studentAnswer = studentAnswer.trim();
				}
				
				if(optionCount ==0 ){//主观题
					
					flag  = 0;
					isGetGrade = false;
					studentQuestionAnswer = new StudentQuestionAnswer(testQuestionId, studentAnswer, receiveScore, flag);
					
				}else{//客观题
					flag = 1;
					if(studentAnswer != null){
						
					if(studentAnswer.equalsIgnoreCase(checkQuestion.getCorrectAnswer())){
						grade +=score;
						receiveScore = score;
						}
					}
					studentQuestionAnswer = new StudentQuestionAnswer(testQuestionId, studentAnswer, receiveScore, flag);
					
				}
				
				studentQuestionAnswers.add(studentQuestionAnswer);
				System.out.println(studentQuestionAnswer);
			}
			questionCount +=count;
		}
		
		
		System.out.println(studentQuestionAnswers);
		//往数据库中添加题
		studentQUestionAnswerDaoImpl.insert(studentQuestionAnswers);
		
		if(isGetGrade){
			studentPaperDaoImpl.updataGrade(grade, studentPaperId);
			return grade;
		}else {
			
			return -1;
		}
	}

	public void updateGrade(int grade,int studentPaperId){
		studentPaperDaoImpl.updataGrade(grade, studentPaperId);
	}
}
