package student.dao;

import java.util.List;

import student.domain.Question;

public interface TestQuestionDao {

	public void insertQuestions(int studentPaperId, List<Question> questions);
}
