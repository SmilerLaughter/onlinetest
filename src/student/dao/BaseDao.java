package student.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import student.dbutils.DBUtils;
import student.filter.TransationConnection;


public class BaseDao<T> implements DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	private Class clazz;
	
	public BaseDao() {
		Type type = getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type[] types = ((ParameterizedType)type).getActualTypeArguments();
			if(types != null && types.length > 0){
				clazz = (Class)types[0];
			}
		}
	}
	
	@Override
	public long insert(String sql, Object... args) {
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			if(args != null && args.length > 0){
				for(int i = 0 ; i < args.length ; i++){
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()){
				id = resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtils.release(preparedStatement, resultSet);
		}
		
		return id;
	}



	@Override
	public void update(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T queryOneObject(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			return  queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public <V> V queryForSingleValue(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			return (V)queryRunner.query(connection, sql,new ScalarHandler(), args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... args) {

		Connection connection = null;
		try {
			connection = TransationConnection.getTransationConnection().getConnection();
			queryRunner.batch(connection, sql, args);
		} catch (Exception e) {
			// TODO: handle exception
		//e.printStackTrace();
		}
	}
}

