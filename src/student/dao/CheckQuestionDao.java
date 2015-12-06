package student.dao;

import java.util.List;

import student.domain.CheckQuestion;

public interface CheckQuestionDao {

	public List<CheckQuestion> getCheckQuestions(int studentPaperId);
}
