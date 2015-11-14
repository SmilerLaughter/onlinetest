package student.filter;

import java.sql.Connection;

public class TransationConnection {
	private static TransationConnection instance = new TransationConnection();
	
	private TransationConnection(){
		
	}
	
	public static TransationConnection getTransationConnection(){
		return instance;
	}
	
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	//绑定连接
	public void bind(Connection connection){
		threadLocal.set(connection);
	}
	
	//获取连接——以对应的THreadLocal的键来获取对应的连接对象
	public Connection getConnection(){
		return threadLocal.get();
	}
	//解除绑定
	public void remove(){
		threadLocal.remove();
	}
}
