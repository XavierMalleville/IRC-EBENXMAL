package com.cfranc.irc.ui;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.cfranc.irc.server.User;

public class ListRenderer implements ListCellRenderer<User> {

	public ListRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel avatar = new JLabel(value.getLogin()); 
		if (!value.getAvatar().isEmpty()) {
			ImageIcon imageIcon = new ImageIcon(value.getAvatar())    ; // load the image to a imageIcon
			Image newimg = imageIcon.getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);   
			avatar.setIcon(new ImageIcon(newimg));  
		}
		if (isSelected) {
			avatar.setBackground(list.getSelectionBackground());
			avatar.setForeground(list.getSelectionForeground());
		} else {
			avatar.setBackground(list.getBackground());
			avatar.setForeground(list.getForeground());
		}
		avatar.setOpaque(true);
		return avatar;
	}

}
