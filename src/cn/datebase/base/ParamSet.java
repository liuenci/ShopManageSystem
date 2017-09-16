package cn.datebase.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL参数设置实现类
 * 
 * @author Administrator
 *
 */
public class ParamSet implements ParamSetimpl {

	private Object[] param;

	public ParamSet(Object... objects) {
		this.param = objects;
	}

	@Override
	public void setParams(PreparedStatement pstm) throws SQLException {
		// 通过循环设置参数
		for (int i = 0; i < param.length; i++) {
			pstm.setObject((i + 1), param[i]);
		}

	}
}
