package student.daoimpl;

import java.util.List;

import student.dao.BaseDao;
import student.dao.OptionDao;
import student.domain.Option;

public class OptionDaoImpl extends BaseDao<Option> implements OptionDao{

	@Override
	public List<Option> getOptions(int questionId) {

		String sql = "select * from options where questionId = ? order by optionContent";
		return queryForList(sql, questionId);
	}

}
