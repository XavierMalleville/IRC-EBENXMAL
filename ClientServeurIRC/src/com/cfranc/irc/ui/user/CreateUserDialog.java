package com.cfranc.irc.ui.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cfranc.irc.ui.SimpleChatClientApp;

public class CreateUserDialog extends JDialog {

	private UserPanel userPanel;
	private JButton createUser;
	
	
	public CreateUserDialog(ActionListener controler) {
		// TODO Auto-generated constructor stub
		setTitle("Nouvel utilisateur");
		userPanel = new UserPanel();
		add(userPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		createUser = new JButton("Create and connect");
		createUser.setActionCommand(SimpleChatClientApp.SAVE_CONNECT_USER);
		createUser.addActionListener(controler);
		buttonPanel.add(createUser); 
		
		this.add(buttonPanel, BorderLayout.PAGE_END);
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point location = new Point((screenSize.width - this.getWidth())/2 , (screenSize.height - this.getHeight())/2); 
		this.setLocation(location);		
		this.setModal(true);
		
	}

	public JTextField getLastNameField() {
		return userPanel.getLastNameField();
	}

	public JTextField getFirstNameField() {
		return userPanel.getFirstNameField();
	}

	public JTextField getLoginField() {
		return userPanel.getLoginField();
	}

	public JPasswordField getPasswordField() {
		return userPanel.getPasswordField();
	}

	public JTextField getAvatarField() {
		return userPanel.getAvatarField();
	}


}
