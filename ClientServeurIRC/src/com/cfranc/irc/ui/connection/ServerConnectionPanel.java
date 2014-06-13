package com.cfranc.irc.ui.connection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ServerConnectionPanel extends JPanel {

	private JTextField serverField; 		
	private JTextField serverPortField; 	
	
	public ServerConnectionPanel() {
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		serverField = new JTextField("localhost");
		serverPortField = new JTextField("4567");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(border, "Information server"));
		this.add(new JLabel("Server :", JLabel.RIGHT), new GridBagConstraints(0, 0, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(serverField, new GridBagConstraints(1, 0, 1, 1, 1.0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(new JLabel("Port :", JLabel.RIGHT), new GridBagConstraints(0, 1, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(serverPortField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0,0)); 
	}

	public JTextField getServerField() {
		return serverField;
	}

	public JTextField getServerPortField() {
		return serverPortField;
	}

	
}
