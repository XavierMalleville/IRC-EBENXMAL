package com.cfranc.irc.ui.connection;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cfranc.irc.ui.user.UserConnectionPanel;

public class ConnectionPanel extends JPanel {

    private ServerConnectionPanel panelServer;
    private UserConnectionPanel panelUser;
    
	/**
	 * Create the panel.
	 */
	public ConnectionPanel() {
		setPreferredSize(new Dimension(320, 200));
	    setLayout(new GridLayout(2,1, 10, 10));
	    panelUser = new UserConnectionPanel();
	    add(panelUser);
	    panelServer = new ServerConnectionPanel();
	    add(panelServer);
	}

	public JTextField getUserNameField() {
		return panelUser.getLoginField();
	}
	public JPasswordField getPasswordField() {
		return panelUser.getPasswordField();
	}

	public JTextField getServerPortField() {
		return panelServer.getServerPortField();
	}

	public JTextField getServerField() {
		return panelServer.getServerField();
	}
	
}
