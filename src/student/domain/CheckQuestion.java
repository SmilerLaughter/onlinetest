package student.domain;

public class CheckQuestion {
	
	private int questionId;
	private String correctAnswer;
	private int testQuestionId;
	
	
	public CheckQuestion() {
		// TODO Auto-generated constructor stub
	}


	public CheckQuestion(int questionId, String correctAnswer,
			int testQuestionId) {
		super();
		this.questionId = questionId;
		this.correctAnswer = correctAnswer;
		this.testQuestionId = testQuestionId;
	}


	@Override
	public String toString() {
		return "CheckQuestion [questionId=" + questionId + ", correctAnswer="
				+ correctAnswer + ", testQuestionId=" + testQuestionId + "]";
	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	public int getTestQuestionId() {
		return testQuestionId;
	}


	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}
	
	

}
