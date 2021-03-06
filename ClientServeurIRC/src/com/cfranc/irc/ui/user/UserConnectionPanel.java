package com.cfranc.irc.ui.user;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class UserConnectionPanel extends JPanel implements FocusListener {

	private JTextField loginField;			//login/pseudo
	private JPasswordField passwordField;	//password

	public UserConnectionPanel() {
		this.setLayout(new GridBagLayout());
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

		loginField= new JTextField("login");
		loginField.addFocusListener(this);
		passwordField = new JPasswordField("trustworthy");
		passwordField.addFocusListener(this);

		this.setBorder(BorderFactory.createTitledBorder(border, "Information de connexion"));
		this.add(new JLabel("Login / Pseudo :", JLabel.RIGHT), new GridBagConstraints(0, 0, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(loginField, new GridBagConstraints(1, 0, 1, 1, 1.0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(new JLabel("Mot de passe :", JLabel.RIGHT), new GridBagConstraints(0, 1, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 
		this.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5,5), 0,0)); 

	}

	protected void selectAll(JTextField source) {
		// TODO Auto-generated method stub
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	
	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField source = (JTextField) e.getSource();
		if ((source.getText()!=null) && (!source.getText().equals(""))) {
			source.setSelectionStart(0);
			source.setSelectionEnd(source.getText().length());
		}
	}
	
}
