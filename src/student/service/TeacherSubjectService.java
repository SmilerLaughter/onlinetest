package student.service;

import java.util.List;

import student.dao.SubjectDao;
import student.dao.TeacherDao;
import student.dao.TeacherSubjectDao;
import student.daoimpl.SubjectDaoImpl;
import student.daoimpl.TeacherDaoImpl;
import student.daoimpl.TeacherSubjectDaoImpl;
import student.domain.Subject;
import student.domain.Teacher;
import student.domain.TeacherSubject;

public class TeacherSubjectService {

	private TeacherDao teacherDao = new TeacherDaoImpl();
	private SubjectDao subjectDao = new SubjectDaoImpl();
	
	public List<Teacher> getTeacherSubjects(){
		List<Teacher> teachers = teacherDao.getTeachers();
		List<Subject> subjects = null;
		for(Teacher teacher : teachers ){
			subjects = subjectDao.getTeacherSubjects(teacher.getTeacherId());
			teacher.setSubjects(subjects);
		}
		
		return teachers;
	}


	
}
