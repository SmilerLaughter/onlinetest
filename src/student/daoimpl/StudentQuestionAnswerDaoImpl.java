package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.StudentQUestionAnswerDao;
import student.domain.StudentQuestionAnswer;

public class StudentQuestionAnswerDaoImpl extends BaseDao<StudentQuestionAnswer> implements StudentQUestionAnswerDao{

	@Override
	public void insert(List<StudentQuestionAnswer> studentQuestionAnswers) {
		// TODO Auto-generated method stub
		String sql = "insert into studentpaperanswers(testQuestionId,studentAnswer,receiveScore,flag) values(?,?,?,?)";
		
		Object[][] params = new Object[studentQuestionAnswers.size()][4];
		for(int i = 0 ; i < studentQuestionAnswers.size(); i++ ){
			params[i][0] = studentQuestionAnswers.get(i).getTestQuestionId();
			params[i][1] = studentQuestionAnswers.get(i).getStudentAnswer();
			params[i][2] = studentQuestionAnswers.get(i).getReceiveScore();
			params[i][3] = studentQuestionAnswers.get(i).getFlag();
		}
		
		batch(sql,params);
	}
}
