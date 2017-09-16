package cn.datebase.base;
/**
 * ���ݿ����ӽӿ�
 * 1.�������ݿ����� createConnection
 * 2. ��ɾ�Ĳ�������������  updateExecuted(String sql)
 * 3. ��ɾ�Ĳ�����������
 * 4. �޲β�ѯ,����һ����¼
 * 5. �вβ�ѯ������һ����¼
 * 6. �޲β�ѯ�����ض�����¼
 * 7. �вβ�ѯ�����ض�����¼
 * 
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface DataManagerimpl {

	/**
	 * 1.�������ݿ�����
	 * @return 
	 */
	Connection createConnection();
	
	/**
	 * 2. ��ɾ�Ĳ�������������
	 * @param sql
	 * @return
	 */
	int updateExecuted(String sql);
	
	/**
	 * 3. ��ɾ�Ĳ�����������
	 * @param sql
	 * @param objects
	 * @return
	 */
	int updateExecuted(String sql,ParamSetimpl  params);
	
	/**
	 * 4. �޲β�ѯ,����һ����¼
	 * @param sql
	 * @return
	 */
	Map<String, Object> query(String sql);
	
	/**
	 * 5. �вβ�ѯ������һ����¼
	 * @param sql
	 * @param objects
	 * @return
	 */
	Map<String,Object> query(String sql,ParamSetimpl  params);
	
	/**
	 * 6. �޲β�ѯ�����ض�����¼
	 * @param sql
	 * @return
	 */
	List<Map<String,Object>> queryList(String sql);
	
	/**
	 * 7. �вβ�ѯ�����ض�����¼
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<Map<String,Object>> queryList(String sql,ParamSetimpl  params);
	
}
