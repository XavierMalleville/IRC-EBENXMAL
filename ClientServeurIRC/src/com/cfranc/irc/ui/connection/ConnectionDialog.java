package com.cfranc.irc.ui.connection;

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

public class ConnectionDialog extends JDialog {

	
	private ConnectionPanel connPanel; 
	private JButton connect;
	private JButton createUser;
	
	
	public ConnectionDialog(ActionListener controler) {
		this.setTitle("Connection");
		connPanel = new ConnectionPanel();
		this.add(connPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		connect = new JButton("Connect");
		connect.setActionCommand(SimpleChatClientApp.CONNECT);
		connect.addActionListener(controler);
		
		createUser = new JButton("Add new user");
		createUser.setActionCommand(SimpleChatClientApp.CREATE_USER);
		createUser.addActionListener(controler);
		buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
		buttonPanel.add(connect) ;
		buttonPanel.add(createUser) ;

		this.add(buttonPanel, BorderLayout.PAGE_END);
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point location = new Point((screenSize.width - this.getWidth())/2 , (screenSize.height - this.getHeight())/2); 
		this.setLocation(location);		
		this.setModal(true);
	}
	
	public JTextField getUserNameField() {
		return connPanel.getUserNameField();
	}
	public JPasswordField getPasswordField() {
		return connPanel.getPasswordField();
	}

	public JTextField getServerPortField() {
		return connPanel.getServerPortField();
	}

	public JTextField getServerField() {
		return connPanel.getServerField();
	}


}
