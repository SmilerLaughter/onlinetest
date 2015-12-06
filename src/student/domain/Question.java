 package student.domain;


public class Question {
	
	private int questionId;
	private String questionName;
	private int chapterId;
	private String correctAnswer;
	private int questionTypeId;
	private Option options;

	
	public Option getOptions() {
		return options;
	}


	public Question(int questionId, String questionName, int chapterId,
			String correctAnswer, int questionTypeId, Option options) {
		super();
		this.questionId = questionId;
		this.questionName = questionName;
		this.chapterId = chapterId;
		this.correctAnswer = correctAnswer;
		this.questionTypeId = questionTypeId;
		this.options = options;
	}


	public void setOptions(Option options) {
		this.options = options;
	}


	public Question() {

	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getQuestionName() {
		return questionName;
	}


	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}


	public int getChapterId() {
		return chapterId;
	}


	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	public int getQuestionTypeId() {
		return questionTypeId;
	}


	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	


	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionName="
				+ questionName + ", chapterId=" + chapterId
				+ ", correctAnswer=" + correctAnswer + ", questionTypeId="
				+ questionTypeId + ", options=" + options + "]";
	}


	public Question(String questionName, int chapterId, String correctAnswer,
			int questionTypeId, Option options) {
		super();
		this.questionName = questionName;
		this.chapterId = chapterId;
		this.correctAnswer = correctAnswer;
		this.questionTypeId = questionTypeId;
		this.options = options;
	}


	@Override
	public int hashCode() {
		
		return questionId;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
			if (questionId != other.questionId)
			return false;
		
		return true;
	}

}