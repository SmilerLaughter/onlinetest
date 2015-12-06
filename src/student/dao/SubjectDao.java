package student.dao;

import java.util.List;

import student.domain.Subject;

public interface SubjectDao {

	public Subject getSubject(int id);
	public List<Subject> getTeacherSubjects(int id);
	public Subject getSubjectWithTeacherSubjectId(int teacherSubjectId);
}
