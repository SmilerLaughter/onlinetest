package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.GradeDao;
import student.domain.Grade;

public class GradeDaoImpl extends BaseDao<Grade> implements GradeDao{

	@Override
	public List<Grade> getGradesForSubjects(int studentId) {
		String sql = "select subjectName from subjects where subjectId = (select subjectId from teacher_subjects where teacherSubjectId = ?) ";
		List<Grade> grades = getTeacherSujectIds(studentId);
		String subjectName= null;
		
		for(Grade grade : grades){
			subjectName = queryForSingleValue(sql, grade.getTeacherSubjectId());
			grade.setSubjectName(subjectName);
		}
		
		return grades;
	}
	
	public List<Grade> getTeacherSujectIds(int studentId){
		String sql = "select grade,teacherSubjectId from studentpapers where studentId = ?";
		return queryForList(sql, studentId);
	}

}
