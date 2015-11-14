package student.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import student.dbutils.DBUtils;

/**
 * Servlet Filter implementation class ConnectionFilter
 */
@WebFilter("/*")
public class ConnectionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ConnectionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			TransationConnection.getTransationConnection().bind(connection);
			chain.doFilter(request, response);
			connection.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				HttpServletResponse servletResponse = (HttpServletResponse)response;
				servletResponse.sendRedirect("error.jsp");
			}
		}finally{
			TransationConnection.getTransationConnection().remove();
			DBUtils.release(connection);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
