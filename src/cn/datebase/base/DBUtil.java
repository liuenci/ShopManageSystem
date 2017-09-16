package cn.datebase.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil implements DataManagerimpl {
	// ���ݿ�����·��
	private static final String URL = "jdbc:mysql://localhost:3306/shopManageSystem?useUnicode=true&amp;characterEncoding=utf-8";
	// �û���
	private static final String USER = "root";
	// ����
	private static final String PASSWORD = "123456";
	// ���ݿ����Ӷ���
	private static Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public DBUtil() {
		// 1.������������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public Connection createConnection() {
		// ������Ӷ���Ϊ�գ��͵��ô������ӷ���
		try {
			if (conn == null || conn.isClosed()) {
				// 2.������ݿ������
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	@Override
	public int updateExecuted(String sql) {
		return this.updateExecuted(sql, null);
	}

	@Override
	public int updateExecuted(String sql, ParamSetimpl params) {
		int row = 0;
		try {
			// 3.ִ��sql���
			ps = createConnection().prepareStatement(sql);
			// ���ò���
			if (params != null) {
				// ���ò������÷���
				params.setParams(ps);
			}
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public Map<String, Object> query(String sql) {
		return this.query(sql, null);
	}

	@Override
	public Map<String, Object> query(String sql, ParamSetimpl params) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 3.��ȡSQLִ�ж���
			ps = createConnection().prepareStatement(sql);
			// ���ò���
			if (params != null) {
				// ���ò������÷���
				params.setParams(ps);
			}
			rs = ps.executeQuery();
			// ��ȡ���ݱ��ֶμ���
			ResultSetMetaData rsmt = rs.getMetaData();

			// 4. ��������
			while (rs.next()) {
				data = this.getResult(rsmt, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> queryList(String sql) {
		// TODO Auto-generated method stub
		return this.queryList(sql, null);
	}

	@Override
	public List<Map<String, Object>> queryList(String sql, ParamSetimpl params) {
		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		try {
			// 3.��ȡSQLִ�ж���
			ps = createConnection().prepareStatement(sql);
			// ���ò���
			if (params != null) {
				// ���ò������÷���
				params.setParams(ps);
			}
			rs = ps.executeQuery();
			// ��ȡ���ݱ��ֶμ���
			ResultSetMetaData rsmt = rs.getMetaData();

			// 4. ��������
			while (rs.next()) {
				Map<String, Object> mp = this.getResult(rsmt, rs);
				lst.add(mp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return lst;
	}

	/**
	 * �ر����ݿ�����
	 */
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ������������ݴ�ŵ�map������
	 * 
	 * @param rsmt
	 * @param rs2
	 */
	private Map<String, Object> getResult(ResultSetMetaData rsmt, ResultSet rs2) {
		Map<String, Object> map = new HashMap<String, Object>();
		// �ҵ���Ӧ�ֶ�����ֵ
		try {
			for (int i = 1; i <= rsmt.getColumnCount(); i++) {
				// �ҵ�����
				String colname = rsmt.getColumnName(i);
				// �ҵ��ж�Ӧ��ֵ
				Object colvalue = rs.getObject(colname);
				if (colvalue == null) {
					colvalue = "";
				}
				// ���ֶκ�ֵ��ӵ�map����map.put
				map.put(colname, colvalue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����map����
		return map;
	}

}
