package student.domain;

import java.util.List;

public class PaperType implements Comparable<PaperType>{

	private int paperTypeId;
	private int teacherSubjectId;
	private int questionTypeId;
	private int score ;
	private int level;
	private int count;
	private String typeName;
	private List<Question> questions;//每个板块的题集
	private int optionCount;//此版块每个题的选项数
	
	public int getAllScore(){
		return score*count;
	}

	public int getOptionCount() {
		return optionCount;
	}


	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	public PaperType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getPaperTypeId() {
		return paperTypeId;
	}


	public void setPaperTypeId(int paperTypeId) {
		this.paperTypeId = paperTypeId;
	}


	public int getTeacherSubjectId() {
		return teacherSubjectId;
	}


	public void setTeacherSubjectId(int teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}


	public int getQuestionTypeId() {
		return questionTypeId;
	}


	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}



	@Override
	public String toString() {
		return "PaperType [paperTypeId=" + paperTypeId + ", teacherSubjectId="
				+ teacherSubjectId + ", questionTypeId=" + questionTypeId
				+ ", score=" + score + ", level=" + level + ", count=" + count
				+ ", typeName=" + typeName + ", questions=" + questions
				+ ", optionCount=" + optionCount + "]";
	}


	public PaperType(int paperTypeId, int teacherSubjectId, int questionTypeId,
			int score, int level, int count, String typeName,
			List<Question> questions, int optionCount) {
		super();
		this.paperTypeId = paperTypeId;
		this.teacherSubjectId = teacherSubjectId;
		this.questionTypeId = questionTypeId;
		this.score = score;
		this.level = level;
		this.count = count;
		this.typeName = typeName;
		this.questions = questions;
		this.optionCount = optionCount;
	}


	@Override
	public int compareTo(PaperType paperType) {
		return new Integer(level).compareTo(paperType.getLevel());
	}
	
	
}

	
