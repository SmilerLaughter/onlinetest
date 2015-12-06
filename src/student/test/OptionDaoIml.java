package student.test;

import student.dao.BaseDao;
import student.domain.Option;

public class OptionDaoIml extends BaseDao<Option> {

	public void insert (Option option){
		String sql = "insert into options( questionId,optionA,optionB,optionC,optionD) values(?,?,?,?,?)";
		insert(sql, option.getQuestionId(),option.getOptionA(),option.getOptionB(),option.getOptionC(),option.getOptionD());
	}
}
