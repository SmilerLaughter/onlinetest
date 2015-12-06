package student.service;

import java.util.List;

import student.dao.GradeDao;
import student.dao.StudentDao;
import student.daoimpl.GradeDaoImpl;
import student.daoimpl.StudentDaoImpl;
import student.domain.Grade;
import student.domain.Student;

public class StudentService {
	
	private StudentDao studentDaoImp = new StudentDaoImpl();
	private GradeDao gradeDaoImpl = new GradeDaoImpl();


//	public StringBuffer prepareUpdate(String oldPassword, String newPassword,
//			Student student, String comfireNewPassword) {
//
//		StringBuffer result = new StringBuffer("");
//		if(student.getPassword().equals(oldPassword)){
//			if(newPassword.equals(comfireNewPassword)){
//				student.setPassword(newPassword);
//				studentDaoImp.updatePassword(student);
//				result.append("操作成功！");
//			}else {
//				result.append("两次输入的密码不一致，请重新输入！");
//			}
//		}else {
//			result.append("输入的旧密码不正确，请重新输入");
//		}
//		return result;
//	}



	public void update( Student student) {

		studentDaoImp.updatePassword(student);
	}



	public List<Grade> getGrades(int studentId) {
		
		return gradeDaoImpl.getGradesForSubjects(studentId);
	}

}
