package cn.model.common;

/**
 * ��Ӧ��
 * 
 * @author Administrator
 *
 */
public class Supply {
	// ��Ӧ�̱��
	private int sup_id;
	// ��Ӧ������
	private String sup_name;
	// ��Ӧ�̵�ַ
	private String sup_address;
	// ��Ӧ����ϵ��
	private String sup_linkMan;
	// ��Ӧ����ϵ�绰
	private String sup_phone;
	// ����״̬��0:���ֺ���1�����������
	private int sup_status;
	// ��ע
	private String sup_mark;

	public Supply() {
		super();
	}

	public Supply(String sup_name, String sup_address, String sup_linkMan, String sup_phone, int sup_status,
			String sup_mark) {
		super();
		this.sup_name = sup_name;
		this.sup_address = sup_address;
		this.sup_linkMan = sup_linkMan;
		this.sup_phone = sup_phone;
		this.sup_status = sup_status;
		this.sup_mark = sup_mark;
	}

	public int getSup_id() {
		return sup_id;
	}

	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}

	public String getSup_name() {
		return sup_name;
	}

	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}

	public String getSup_address() {
		return sup_address;
	}

	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}

	public String getSup_linkMan() {
		return sup_linkMan;
	}

	public void setSup_linkMan(String sup_linkMan) {
		this.sup_linkMan = sup_linkMan;
	}

	public String getSup_phone() {
		return sup_phone;
	}

	public void setSup_phone(String sup_phone) {
		this.sup_phone = sup_phone;
	}

	public int getSup_status() {
		return sup_status;
	}

	public void setSup_status(int sup_status) {
		this.sup_status = sup_status;
	}

	public String getSup_mark() {
		return sup_mark;
	}

	public void setSup_mark(String sup_mark) {
		this.sup_mark = sup_mark;
	}

}
