package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Format {

	
	// 创建面板方法
		public JPanel createPanel(int x, int y, int width, int height) {
			JPanel jPanel = new JPanel();
			jPanel.setLayout(null);
			Color color = new Color(237, 242, 248);
			jPanel.setBackground(color);
			jPanel.setBounds(x, y, width, height);
			jPanel.setBorder(BorderFactory.createEtchedBorder());
			return jPanel;
		}

		// 创建标签方法
		public JLabel createLabel(String name, int x, int y, int width, int height, String fontName, int fontSize) {
			JLabel jLabel = new JLabel(name);
			jLabel.setBounds(x, y, width, height);
			jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
			return jLabel;
		}

		// 创建按钮方法
		public JButton createButton(String name, int x, int y, int width, int height, String fontName, int fontSize) {
			JButton jButton = new JButton(name);
			jButton.setBounds(x, y, width, height);
			Color color = new Color(129, 194, 214);
			jButton.setBackground(color);
			return jButton;
		}

		// 创建文本框方法
		public JTextField createTextField(String text, int x, int y, int width, int height, String fontName, int fontSize,
				Color color) {
			JTextField jTextField = new JTextField();
			jTextField.setBounds(x, y, width, height);
			jTextField.setText(text);
			jTextField.setFont(new Font(text, Font.PLAIN, fontSize));
			jTextField.setBackground(color);
			return jTextField;
		}

		// 创建图片按钮
		public JButton createImagesButton(String path, int logoWidth, int logoHeight, int x, int y, int width, int height) {
			ImageIcon logoIcon = new ImageIcon(path);
			Image logoImage = logoIcon.getImage();
			logoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_DEFAULT);
			logoIcon.setImage(logoImage);
			JButton jButton = new JButton();
			jButton.setIcon(logoIcon);
			jButton.setBounds(x, y, width, height);
			Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
			jButton.setBorder(emptyBorder);
			return jButton;
		}

		// 创建密码输入框方法
		public JPasswordField createPasswordField(int x, int y, int width, int height, Color color) {
			JPasswordField jPasswordField = new JPasswordField();
			jPasswordField.setBounds(x, y, width, height);
			jPasswordField.setBackground(color);
			jPasswordField.setHorizontalAlignment(JTextField.CENTER);
			return jPasswordField;
		}
		//创建下拉列表框
		public JComboBox createJComboBox(int x, int y, int width, int height,String fontName, int fontSize) {
			JComboBox jComboBox = new JComboBox();
			jComboBox.setBounds(x, y, width, height);
			jComboBox.setFont(new Font(fontName,Font.PLAIN,fontSize));
			return jComboBox;
		}


}
