package student.daoimpl;

import student.dao.BaseDao;
import student.dao.StudentPaperDao;
import student.domain.StudentPaper;

public class StudentPaperDaoImpl extends BaseDao<StudentPaper> implements StudentPaperDao{

	@Override
	public void insert(StudentPaper studentPaper) {

		String sql = "insert into studentpapers(studentId,teachersubjectId) values(?,?)";
		int studentPaperId = (int)insert(sql, studentPaper.getStudentId(),studentPaper.getTeacherSubjectId());
		studentPaper.setStudentPaperId(studentPaperId);
	}

	@Override
	public void updataGrade(int grade,int studentPaperId) {

		String sql = "update studentpapers set grade = ? where studentPaperId = ?";
		update(sql, grade,studentPaperId);
	}
	
}
