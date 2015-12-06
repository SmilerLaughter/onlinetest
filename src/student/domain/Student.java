package student.domain;

public class Student {
	private int studentId;
	private String studentName;
	private String password;
	private int flag;
	private int studentPaperId;
	
	
	


	public int getStudentPaperId() {
		return studentPaperId;
	}

	public void setStudentPaperId(int studentPaperId) {
		this.studentPaperId = studentPaperId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student(int studentId, String studentName, String password,int flag) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.password = password;
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", password=" + password + ", flag=" + flag
				+ ", studentPaperId=" + studentPaperId + "]";
	}


}
