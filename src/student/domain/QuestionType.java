package student.domain;

public class QuestionType {
	private int questionTypeId;
	private String questionTypeName;
	
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

	public QuestionType(int questionTypeId, String questionTypeName) {
		super();
		this.questionTypeId = questionTypeId;
		this.questionTypeName = questionTypeName;
	}

	@Override
	public String toString() {
		return "QuestionType [questionTypeId=" + questionTypeId
				+ ", questionTypeName=" + questionTypeName + "]";
	}
	
	
	
	
}
