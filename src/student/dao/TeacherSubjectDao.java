package student.dao;

import student.domain.TeacherSubject;

public interface TeacherSubjectDao {
	
	public TeacherSubject getTeacherSubject(int teacherId,int subjectId);
	public TeacherSubject getTeacherSubjectWithStudentPaperId(int studentPaperId);
}
	 