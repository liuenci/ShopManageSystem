package cn.dao.sell;

import java.util.List;

import cn.model.common.Good;
import cn.model.common.Storage;

public class SalesControl2 {
	/**
	 * 1. 查询商品清单
	 */
	public List<Good> selqueryGood() {
		// 创建Dao对象
		SalesDao2 d = new SalesDao2();
		// 调用添加方法
		List<Good> good = d.queryGood();
		return good;
	}
	/**
	 * 仓库名
	 */
	public List<Storage> getStorage() {
		// 创建Dao对象
		SalesDao2 d = new SalesDao2();
		// 调用添加方法
		List<Storage> good=d.Obtain_Library_information();
		return good;
		
	}
}
