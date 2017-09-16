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
	// 数据库连接路径
	private static final String URL = "jdbc:mysql://localhost:3306/shopManageSystem?useUnicode=true&amp;characterEncoding=utf-8";
	// 用户名
	private static final String USER = "root";
	// 密码
	private static final String PASSWORD = "123456";
	// 数据库连接对象
	private static Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public DBUtil() {
		// 1.加载驱动程序
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public Connection createConnection() {
		// 如果连接对象为空，就调用创建连接方法
		try {
			if (conn == null || conn.isClosed()) {
				// 2.获得数据库的连接
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
			// 3.执行sql语句
			ps = createConnection().prepareStatement(sql);
			// 设置参数
			if (params != null) {
				// 调用参数设置方法
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
			// 3.获取SQL执行对象
			ps = createConnection().prepareStatement(sql);
			// 设置参数
			if (params != null) {
				// 调用参数设置方法
				params.setParams(ps);
			}
			rs = ps.executeQuery();
			// 获取数据表字段集合
			ResultSetMetaData rsmt = rs.getMetaData();

			// 4. 处理结果集
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
			// 3.获取SQL执行对象
			ps = createConnection().prepareStatement(sql);
			// 设置参数
			if (params != null) {
				// 调用参数设置方法
				params.setParams(ps);
			}
			rs = ps.executeQuery();
			// 获取数据表字段集合
			ResultSetMetaData rsmt = rs.getMetaData();

			// 4. 处理结果集
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
	 * 关闭数据库连接
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
	 * 将结果集的数据存放到map集合中
	 * 
	 * @param rsmt
	 * @param rs2
	 */
	private Map<String, Object> getResult(ResultSetMetaData rsmt, ResultSet rs2) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 找到对应字段名和值
		try {
			for (int i = 1; i <= rsmt.getColumnCount(); i++) {
				// 找到列名
				String colname = rsmt.getColumnName(i);
				// 找到列对应的值
				Object colvalue = rs.getObject(colname);
				if (colvalue == null) {
					colvalue = "";
				}
				// 将字段和值添加到map集合map.put
				map.put(colname, colvalue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回map集合
		return map;
	}

}
