package cn.datebase.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL��������ʵ����
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
		// ͨ��ѭ�����ò���
		for (int i = 0; i < param.length; i++) {
			pstm.setObject((i + 1), param[i]);
		}

	}
}
