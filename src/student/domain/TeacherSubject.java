package student.domain;

public class TeacherSubject {

	private int teacherSubjectId;
	private int teacherId;
	private int subjectId;
	private int time;
	private int grade;
	
	

	public TeacherSubject() {
		// TODO Auto-generated constructor stub
	}

	public int getTeacherSubjectId() {
		return teacherSubjectId;
	}

	public void setTeacherSubjectId(int teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public TeacherSubject(int teacherId, int subjectId, int time, int grade) {
		super();
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.time = time;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "TeacherSubject [teacherSubjectId=" + teacherSubjectId
				+ ", teacherId=" + teacherId + ", subjectId=" + subjectId
				+ ", time=" + time + ", grade=" + grade + "]";
	}
	
	
}
