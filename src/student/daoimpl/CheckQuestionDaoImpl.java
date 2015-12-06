package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.CheckQuestionDao;
import student.domain.CheckQuestion;

public class CheckQuestionDaoImpl extends BaseDao<CheckQuestion> implements CheckQuestionDao {

	@Override
	public List<CheckQuestion> getCheckQuestions(int studentPaperId) {

		String sql = "select testquestionId,testquestions.questionId,correctAnswer"
				+ " FROM testquestions,questions WHERE studentPaperId = ?"
				+ " AND testquestions.questionId = questions.questionId  "
				+ "ORDER BY testQuestionId";
		return queryForList(sql, studentPaperId);
	}
	
}
