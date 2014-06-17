package com.cfranc.irc.ui.connection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ServerConnectionPanel extends JPanel implements FocusListener {

	private JTextField serverField; 		
	private JTextField serverPortField; 	
	
	public ServerConnectionPanel() {
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		serverField = new JTextField("localhost");
		serverField.addFocusListener(this);
		serverPortField = new JTextField("4567");
		serverPortField.addFocusListener(this);
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
