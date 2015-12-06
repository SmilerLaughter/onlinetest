package student.dao;

import java.util.List;


public interface DAO<T>{
	
	public long insert(String sql ,Object...args);
	
	public void update(String sql ,Object ... args);
	
	public List<T> queryForList(String sql ,Object ... args);
	
	
	public T queryOneObject(String sql ,Object ...args);
	
	public <V>V queryForSingleValue(String sql ,Object ... args);
	
	public void batch(String sql , Object[] ... args);
	
}