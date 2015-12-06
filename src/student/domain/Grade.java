package student.domain;

public class Grade {

	private int grade;
	private String subjectName;
	private int teacherSubjectId;
	
	public int getTeacherSubjectId() {
		return teacherSubjectId;
	}


	public void setTeacherSubjectId(int teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}


	public int getGrade() {
		return grade;
	}
	
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Grade() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Grade [grade=" + grade + ", subject=" + subjectName + "]";
	}
	
	
	
}
