package student.domain;

public class Option {

	private int optionId;
	private int  questionId;
	private String optionContent;

	
	public Option() {
		// TODO Auto-generated constructor stub
	}


	public int getOptionId() {
		return optionId;
	}


	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getOptionContent() {
		return optionContent;
	}


	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}


	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", questionId=" + questionId
				+ ", optionContent=" + optionContent + "]";
	}


	public Option( int questionId, String optionContent) {
		super();
		this.questionId = questionId;
		this.optionContent = optionContent;
	}

	
	
}
