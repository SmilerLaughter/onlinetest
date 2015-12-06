package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.QuestionDao;
import student.domain.Question;

public class QuestionDaoImpl extends BaseDao<Question> implements QuestionDao {

	@Override
	public List<Question> getQuestions(int subjectId, int questionTypeId) {
		
		String sql = "select questionId,questionName,correctAnswer,questionTypeId,chapterId from questions "
				+ "where questionTypeId = ? and chapterId in("
				+ "select chapterId from chapters where subjectId = ?)"
				+ "order by chapterId";
		
		return queryForList(sql, questionTypeId,subjectId);
	}

	@Override
	public List<Question> getQuestionsWithStudentPaperId(int studentPaperId) {
		
		String sql = "SELECT questions.questionId questionId,questionName,correctAnswer,questionTypeId,chapterId "
				+ " FROM questions,testquestions"
				+ " WHERE studentPaperId = ? AND questions.questionId = testquestions.questionId"
				+ " ORDER BY testQuestionId";
		return queryForList(sql, studentPaperId);
	}
	
}
