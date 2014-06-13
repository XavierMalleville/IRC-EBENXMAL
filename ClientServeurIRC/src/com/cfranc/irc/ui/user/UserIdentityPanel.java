package com.cfranc.irc.ui.user;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class UserIdentityPanel extends JPanel {

	private JTextField lastNameField; 		//Nom
	private JTextField firstNameField; 		//Prénom
	
	public UserIdentityPanel() {
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		lastNameField = new JTextField("last name");
		firstNameField = new JTextField("first name");
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(border, "Information d'identification"));
		this.add(new JLabel("Nom :", JLabel.RIGHT), new GridBagConstraints(0, 0, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(lastNameField, new GridBagConstraints(1, 0, 1, 1, 1.0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(new JLabel("Prénom :", JLabel.RIGHT), new GridBagConstraints(0, 1, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(firstNameField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0,0)); 
	}

	public JTextField getLastNameField() {
		return lastNameField;
	}

	public JTextField getFirstNameField() {
		return firstNameField;
	}

}
