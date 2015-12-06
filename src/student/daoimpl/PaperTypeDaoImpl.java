package student.daoimpl;

import java.util.Collections;
import java.util.List;

import student.dao.BaseDao;
import student.dao.PaperTypeDao;
import student.domain.PaperType;

public class PaperTypeDaoImpl extends BaseDao<PaperType> implements PaperTypeDao{

	@Override
	public List<PaperType> getPaperTypes(int teacherSubjectId) {
		
		String sql ="SELECT paperTypeId,teacherSubjectId,typeName,papertypes.questionTypeId,"
				+ "score,COUNT,LEVEL,optionCount FROM papertypes,questiontypes"
				+ " WHERE teacherSubjectId = ?"
				+ " AND papertypes.questionTypeId= questiontypes.questionTypeId";
		List<PaperType> paperTypes = queryForList(sql, teacherSubjectId);
		Collections.sort(paperTypes);
		
		return paperTypes;
	}

	@Override
	public List<PaperType> getPaperTypesWithStudentPaperId(int studentPaperId) {

		String sql = "SELECT paperTypeId,teacherSubjectId,typeName,papertypes.questionTypeId,"
				+ "score,COUNT,LEVEL,optionCount FROM papertypes,questiontypes"
				+ " WHERE teacherSubjectId = (select teacherSubjectId from studentpapers where studentPaperId = ?)"
				+ " AND papertypes.questionTypeId= questiontypes.questionTypeId";
		List<PaperType> paperTypes = queryForList(sql, studentPaperId);
		Collections.sort(paperTypes);
		
		return paperTypes;
	}

}
