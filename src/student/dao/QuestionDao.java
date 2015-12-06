package student.dao;

import java.util.List;

import student.domain.Question;

public interface QuestionDao {

	public List<Question> getQuestions(int subjectId,int questionTypeId);
	public List<Question> getQuestionsWithStudentPaperId(int studentPaperId);
}
