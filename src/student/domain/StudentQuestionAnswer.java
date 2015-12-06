package student.domain;

public class StudentQuestionAnswer {

	private int testQuestionId;
	private String studentAnswer;
	private int receiveScore;
	private int flag;
	
	public StudentQuestionAnswer() {
		// TODO Auto-generated constructor stub
	}

	public int getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public int getReceiveScore() {
		return receiveScore;
	}

	public void setReceiveScore(int receiveScore) {
		this.receiveScore = receiveScore;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "StudentQuestionAnswer [testQuestionId=" + testQuestionId
				+ ", studentAnswer=" + studentAnswer + ", receiveScore="
				+ receiveScore + ", flag=" + flag + "]";
	}

	public StudentQuestionAnswer(int testQuestionId, String studentAnswer,
			int receiveScore, int flag) {
		super();
		this.testQuestionId = testQuestionId;
		this.studentAnswer = studentAnswer;
		this.receiveScore = receiveScore;
		this.flag = flag;
	}
	
	
}
