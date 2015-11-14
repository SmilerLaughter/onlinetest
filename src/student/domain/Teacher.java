package student.domain;

import java.util.List;

public class Teacher {
	private int teacherId;
	private String teacherName;
	private String password;
	private List<Subject> subjects;
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public int getTeacherId() {
		return teacherId;
	}
	
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName="
				+ teacherName + ", password=" + password + ", subjects="
				+ subjects + "]";
	}

	public Teacher(int teacherId, String teacherName, String password) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.password = password;
	}

	
	
	
	
}
