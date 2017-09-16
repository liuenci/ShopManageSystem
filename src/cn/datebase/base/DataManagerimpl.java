package cn.datebase.base;
/**
 * 数据库连接接口
 * 1.创建数据库连接 createConnection
 * 2. 添删改操作，不带参数  updateExecuted(String sql)
 * 3. 添删改操作，带参数
 * 4. 无参查询,返回一条记录
 * 5. 有参查询，返回一条记录
 * 6. 无参查询，返回多条记录
 * 7. 有参查询，返回多条记录
 * 
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface DataManagerimpl {

	/**
	 * 1.创建数据库连接
	 * @return 
	 */
	Connection createConnection();
	
	/**
	 * 2. 添删改操作，不带参数
	 * @param sql
	 * @return
	 */
	int updateExecuted(String sql);
	
	/**
	 * 3. 添删改操作，带参数
	 * @param sql
	 * @param objects
	 * @return
	 */
	int updateExecuted(String sql,ParamSetimpl  params);
	
	/**
	 * 4. 无参查询,返回一条记录
	 * @param sql
	 * @return
	 */
	Map<String, Object> query(String sql);
	
	/**
	 * 5. 有参查询，返回一条记录
	 * @param sql
	 * @param objects
	 * @return
	 */
	Map<String,Object> query(String sql,ParamSetimpl  params);
	
	/**
	 * 6. 无参查询，返回多条记录
	 * @param sql
	 * @return
	 */
	List<Map<String,Object>> queryList(String sql);
	
	/**
	 * 7. 有参查询，返回多条记录
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<Map<String,Object>> queryList(String sql,ParamSetimpl  params);
	
}
