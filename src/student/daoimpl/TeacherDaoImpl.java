package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.TeacherDao;
import student.domain.Subject;
import student.domain.Teacher;

public class TeacherDaoImpl extends BaseDao<Teacher> implements TeacherDao {

	@Override
	public Teacher getTeacher(int id) {
		String sql = "select * from teachers where teacherId = ?";
		return queryOneObject(sql, id);
	}

	@Override
	public List<Teacher> getTeachers() {
		String sql = "select * from teachers";
		return queryForList(sql);
	}

}
