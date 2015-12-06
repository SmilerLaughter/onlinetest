package student.dao;

import java.util.List;

import student.domain.PaperType;

public interface PaperTypeDao {
	
	public List<PaperType> getPaperTypes(int teacherSubjectId);
	public List<PaperType> getPaperTypesWithStudentPaperId(int studentPaperId);
	
	
}
