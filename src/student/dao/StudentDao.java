package student.dao;

import student.domain.Student;

public interface StudentDao {
	public void updateFlag(int studentId);
	public Student getStudent(int studentId);	
}
