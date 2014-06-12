package com.cfranc.irc.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.activation.MailcapCommandMap;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

public class ConnectionPanel extends JPanel {

    private JTextField serverPortField;
    private JTextField serverField;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JTextField userRealNameField;
    
	public JTextField getServerPortField() {
		return serverPortField;
	}

	public JTextField getServerField() {
		return serverField;
	}

	/**
	 * Create the panel.
	 */
	public ConnectionPanel() {
	
	
	    JPanel connectionPanel = new JPanel(false);
		connectionPanel.setLayout(new BoxLayout(connectionPanel,
							BoxLayout.X_AXIS));
	
		JPanel namePanel = new JPanel(false);
		namePanel.setLayout(new GridLayout(0, 1));
	
		JPanel fieldPanel = new JPanel(false);
		fieldPanel.setLayout(new GridLayout(0, 1));
	    JLabel userNameLabel = new JLabel("Pseudo de l'utilisateur: ", JLabel.RIGHT);
	    userNameField = new JTextField("guest");
	    JLabel passwordLabel = new JLabel("Mot de passe: ", JLabel.RIGHT);
        JLabel serverLabel = new JLabel("Nom du serveur: ", JLabel.RIGHT);
        JLabel serverPortLabel = new JLabel("Port: ", JLabel.RIGHT);
	    passwordField = new JPasswordField("trustworthy");
	    serverField = new JTextField("localhost");
	    serverPortField = new JTextField("4567");
	    JLabel userRealNameLabel = new JLabel("Nom réel: ", JLabel.RIGHT);
	    userRealNameField = new JTextField("Toto");
	    GroupLayout groupLayout = new GroupLayout(this);
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addContainerGap(242, Short.MAX_VALUE)
	    			.addComponent(namePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(78)
	    			.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGap(60)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(userRealNameLabel)
	    				.addComponent(userNameLabel)
	    				.addComponent(passwordLabel)
	    				.addComponent(serverLabel)
	    				.addComponent(serverPortLabel))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
	    				.addComponent(passwordField)
	    				.addComponent(serverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(userNameField)
	    				.addComponent(userRealNameField))
	    			.addContainerGap(67, Short.MAX_VALUE))
	    );
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addGap(23)
	    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    						.addGroup(groupLayout.createSequentialGroup()
	    							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(userNameLabel))
	    							.addGap(3)
	    							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(userRealNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(userRealNameLabel))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(passwordLabel))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(serverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(serverLabel))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(serverPortField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(serverPortLabel)))
	    						.addComponent(namePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
	    			.addContainerGap(56, Short.MAX_VALUE))
	    );
	    setLayout(groupLayout);	 
	    setPreferredSize(new Dimension(320, 200));
	}

	public JTextField getUserNameField() {
		return userNameField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JTextField getUserRealNameField() {
		return userRealNameField;
	}
	
}
