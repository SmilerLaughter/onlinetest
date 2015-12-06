package student.domain;

public class StudentPaper {

	private int studentPaperId;
	private int studentId;
	private int grade;
	private int teacherSubjectId;
	
	public StudentPaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StudentPaper [studentPaperId=" + studentPaperId
				+ ", studentId=" + studentId + ", grade=" + grade
				+ ", teacherSubjectId=" + teacherSubjectId + "]";
	}

	public int getStudentPaperId() {
		return studentPaperId;
	}

	public void setStudentPaperId(int studentPaperId) {
		this.studentPaperId = studentPaperId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getTeacherSubjectId() {
		return teacherSubjectId;
	}

	public void setTeacherSubjectId(int teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}

	public StudentPaper(int studentPaperId, int studentId, int grade,
			int teacherSubjectId) {
		super();
		this.studentPaperId = studentPaperId;
		this.studentId = studentId;
		this.grade = grade;
		this.teacherSubjectId = teacherSubjectId;
	}
	
	
}
