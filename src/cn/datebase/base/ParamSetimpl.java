package cn.datebase.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * �������ýӿ�
 * @author Administrator
 *
 */
public interface ParamSetimpl {
	/**
	 * SQL�������÷���
	 * @param pstm
	 */
	void setParams(PreparedStatement pstm) throws SQLException;
}


