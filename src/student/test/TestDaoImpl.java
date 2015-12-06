package student.test;

import student.dao.BaseDao;
import student.domain.Question;

public class TestDaoImpl extends BaseDao<Question>  {

	public void insertQuestion(Question question){
		String sql = "insert into questions(questionId,questionName,correctAnswer,questionTypeId,chapterId) values(?,?,?,?,?)";
		insert(sql,question.getQuestionId(), question.getQuestionName(),question.getCorrectAnswer(),question.getQuestionTypeId(),question.getChapterId());
		
	}
}
