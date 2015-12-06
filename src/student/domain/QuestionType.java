package student.domain;

public class QuestionType {
	private int questionTypeId;
	private String questionTypeName;
	private int optionCount;
	

	public int getOptionCount() {
		return optionCount;
	}

	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}

	public int getQuestionTypeId() {
		return questionTypeId;
	}
	
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	
	public QuestionType() {
		// TODO Auto-generated constructor stub
	}

	public QuestionType(int questionTypeId, String questionTypeName,
			int optionCount) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
		this.optionCount = optionCount;
	}

	@Override
	public String toString() {
		return "QuestionType [questionTypeId=" + questionTypeId
				+ ", questionTypeName=" + questionTypeName + ", optionCount="
				+ optionCount + "]";
	}

	
	
}
