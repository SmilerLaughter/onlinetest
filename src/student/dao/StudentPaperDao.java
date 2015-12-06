package student.dao;

import student.domain.StudentPaper;


public interface StudentPaperDao {
	
	public void insert(StudentPaper studentPaper);
	public void updataGrade(int grade,int studentPaperId);
}
