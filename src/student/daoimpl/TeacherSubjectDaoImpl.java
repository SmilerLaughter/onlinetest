package student.daoimpl;

import student.dao.BaseDao;
import student.dao.TeacherSubjectDao;
import student.domain.TeacherSubject;

public class TeacherSubjectDaoImpl extends BaseDao<TeacherSubject> implements TeacherSubjectDao{

	@Override
	public TeacherSubject getTeacherSubject(int teacherId, int subjectId) {
		String sql = "select * from teacher_subjects where teacherId = ? and subjectId = ?";
		return queryOneObject(sql, teacherId,subjectId);
	}

	@Override
	public TeacherSubject getTeacherSubjectWithStudentPaperId(int studentPaperId) {
		
		String sql = "select * from teacher_subjects"
				+ " where teacherSubjectId = ("
					+ "select teacherSubjectId "
					+ "from studentpapers "
					+ "where studentPaperId =?)";
		return queryOneObject(sql, studentPaperId);
	}

}
