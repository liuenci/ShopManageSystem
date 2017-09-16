package cn.datebase.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数设置接口
 * @author Administrator
 *
 */
public interface ParamSetimpl {
	/**
	 * SQL参数设置方法
	 * @param pstm
	 */
	void setParams(PreparedStatement pstm) throws SQLException;
}


