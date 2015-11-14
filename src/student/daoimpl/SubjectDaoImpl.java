package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.SubjectDao;
import student.domain.Subject;

public class SubjectDaoImpl  extends BaseDao<Subject> implements SubjectDao{

	@Override
	public Subject getSubject(int id) {
		String sql = "select * from subjects where subjectId = ?";
		return queryOneObject(sql, id);
	}

	@Override
	public List<Subject> getTeacherSubjects(int teacherId) {
		String sql = "select * from subjects "
				+ "where subjectId in("
				+ "select subjectId from teacher_subjects where teacherId = ?"
				+ ")";
		return queryForList(sql, teacherId);
	}

}
