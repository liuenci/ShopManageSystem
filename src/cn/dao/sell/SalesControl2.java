package cn.dao.sell;

import java.util.List;

import cn.model.common.Good;
import cn.model.common.Storage;

public class SalesControl2 {
	/**
	 * 1. ��ѯ��Ʒ�嵥
	 */
	public List<Good> selqueryGood() {
		// ����Dao����
		SalesDao2 d = new SalesDao2();
		// ������ӷ���
		List<Good> good = d.queryGood();
		return good;
	}
	/**
	 * �ֿ���
	 */
	public List<Storage> getStorage() {
		// ����Dao����
		SalesDao2 d = new SalesDao2();
		// ������ӷ���
		List<Storage> good=d.Obtain_Library_information();
		return good;
		
	}
}
