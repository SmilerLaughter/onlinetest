package student.domain;

public class TestQuestion {
	
	private int testQuestionId;
	private int studentPaperId;
	private int questionId;
	
	public TestQuestion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TestQuestion [testQuestionId=" + testQuestionId
				+ ", studentPaperId=" + studentPaperId + ", questionId="
				+ questionId + "]";
	}

	public TestQuestion(int testQuestionId, int studentPaperId, int questionId) {
		super();
		this.testQuestionId = testQuestionId;
		this.studentPaperId = studentPaperId;
		this.questionId = questionId;
	}

	public int getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public int getStudentPaperId() {
		return studentPaperId;
	}

	public void setStudentPaperId(int studentPaperId) {
		this.studentPaperId = studentPaperId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	
}
