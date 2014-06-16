package com.cfranc.irc.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.pushingpixels.flamingo.api.common.HorizontalAlignment;

import com.cfranc.irc.server.User;

public class ListRenderer implements ListCellRenderer<User> {

	public ListRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel avatar = new JLabel(value.getName() + " " + value.getPrenom()); 
		if (!value.getAvatar().isEmpty()) {
			avatar.setIcon(new ImageIcon(value.getAvatar()));
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
