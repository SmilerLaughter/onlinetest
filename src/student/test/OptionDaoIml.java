package student.test;

import java.util.List;

import student.dao.BaseDao;
import student.domain.Option;

public class OptionDaoIml extends BaseDao<Option> {

	public void insert (List<Option> options){
		String sql = "insert into options( questionId,optionContent) values(?,?)";
		
		Object[][] params = new Object[options.size()][2];
		for(int i = 0; i < options.size() ; i++){
			params[i][0] = options.get(i).getQuestionId();
			params[i][1] = options.get(i).getOptionContent();
		}
		
		batch(sql, params);
	}
}
