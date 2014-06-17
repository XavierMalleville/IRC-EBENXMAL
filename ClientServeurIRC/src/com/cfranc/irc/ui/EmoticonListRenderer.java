package com.cfranc.irc.ui;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.cfranc.irc.server.User;

public class EmoticonListRenderer implements ListCellRenderer<Emoticon> {

	public EmoticonListRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Emoticon> list, Emoticon value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel emoticon = new JLabel(value.getCode()); 
		if (!value.getResource().isEmpty()) {
			ImageIcon imageIcon = new ImageIcon(EmoticonListRenderer.class.getResource(value.getResource())); 
			Image newimg = imageIcon.getImage().getScaledInstance(24, 24,  java.awt.Image.SCALE_SMOOTH);   
			emoticon.setIcon(new ImageIcon(newimg));  
		}
		if (isSelected) {
			emoticon.setBackground(list.getSelectionBackground());
			emoticon.setForeground(list.getSelectionForeground());
		} else {
			emoticon.setBackground(list.getBackground());
			emoticon.setForeground(list.getForeground());
		}
		emoticon.setOpaque(true);
		return emoticon;
	}

}
