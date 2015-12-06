package student.daoimpl;

import student.dao.BaseDao;
import student.dao.StudentDao;
import student.domain.Student;

public class StudentDaoImpl  extends BaseDao<Student> implements StudentDao{

	@Override
	public void updateFlag(int studentId) {

		String sql = "update students set flag = flag - 1 where studentId = ?";
		update(sql, studentId);
	}

	@Override
	public Student getStudent(int studentId) {
		// TODO Auto-generated method stub
		String sql = "select * from students where studentId = ?";
		return queryOneObject(sql, studentId);
	}

	@Override
	public void updatePassword(Student student) {

		String sql = "update students set studentPassword = ? where studentId = ?";
		update(sql, student.getPassword(),student.getStudentId());
		
	}

}
