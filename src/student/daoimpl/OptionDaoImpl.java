package student.daoimpl;

import student.dao.BaseDao;
import student.dao.OptionDao;
import student.domain.Option;

public class OptionDaoImpl extends BaseDao<Option> implements OptionDao{

	@Override
	public Option getOption(int questionId) {

		String sql = "select * from options where questionId = ?";
		return queryOneObject(sql, questionId);
	}

}
