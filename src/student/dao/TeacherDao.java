package student.dao;

import java.util.List;

import student.domain.Teacher;

public interface TeacherDao {
	public Teacher getTeacher(int id);
	public List<Teacher> getTeachers();
}
