package student.dao;

import java.util.List;

import student.domain.Grade;

public interface GradeDao {

	public List<Grade> getGradesForSubjects(int studentId);
}
