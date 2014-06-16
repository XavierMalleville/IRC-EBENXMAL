package com.cfranc.irc.ui.user;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cfranc.irc.ui.SimpleChatClientApp;
import com.sun.corba.se.spi.orbutil.fsm.Action;

public class CreateUserDialog extends JDialog {

	private UserPanel userPanel;
	private JButton createUser;
	
	
	public CreateUserDialog(ActionListener controler) {
		// TODO Auto-generated constructor stub
		setTitle("Nouveau utilisateur");
		userPanel = new UserPanel();
		add(userPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		createUser = new JButton("Create and connect");
		createUser.setActionCommand(SimpleChatClientApp.SAVE_CONNECT_USER);
		createUser.addActionListener(controler);
		buttonPanel.add(createUser); 
		
		this.add(buttonPanel, BorderLayout.PAGE_END);
		this.pack();
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
