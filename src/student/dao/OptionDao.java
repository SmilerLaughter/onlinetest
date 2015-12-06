package student.dao;

import java.util.List;

import student.domain.Option;

public interface OptionDao {
	
	public List<Option> getOptions(int questionId);
}
