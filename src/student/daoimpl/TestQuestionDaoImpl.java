package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.TestQuestionDao;
import student.domain.Question;
import student.domain.TestQuestion;

public class TestQuestionDaoImpl extends BaseDao<TestQuestion> implements TestQuestionDao{

	@Override
	public void insertQuestions(int studentPaperId, List<Question> questions) {

		String sql = "insert into testquestions(studentPaperId,questionId) values(?,?)";
		Object[][] params =new Object[questions.size()][2] ;
		
		for(int i = 0 ;i < questions.size() ; i++){
			params[i][0] = studentPaperId;
			params[i][1] = questions.get(i).getQuestionId();
		}
		
		batch(sql, params);
	}

}
