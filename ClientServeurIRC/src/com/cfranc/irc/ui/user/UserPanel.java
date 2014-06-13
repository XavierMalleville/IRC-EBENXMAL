package com.cfranc.irc.ui.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;

import com.cfranc.irc.ui.user.UserConnectionPanel;
import com.cfranc.irc.ui.user.UserIdentityPanel;


public class UserPanel extends JPanel {
	private UserIdentityPanel pnlIdentity;
	private UserConnectionPanel pnlLogin;
	private JTextField avatarField;			//Adresse de l'avatar
	private JPanel avatarView;				//Panel de viisualisation de l'avatar
	
	public UserPanel() {
		pnlIdentity = new UserIdentityPanel();

		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

		avatarField = new JTextField("C:\\Users\\Administrateur\\Pictures\\couv.jpg");
		avatarField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				avatarView.repaint();	
			}
		});
		
		avatarView = new JPanel(new BorderLayout()) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if ((new File(avatarField.getText())).exists()) {
					g.drawImage( (new ImageIcon(avatarField.getText())).getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
				} else {
					g.clearRect(0, 0, this.getWidth(), this.getHeight());
				}
			}
		};
		
		avatarView.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 ) {
					JFileChooser fc = new JFileChooser();
			        if (fc.showDialog(avatarField, "Select") == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            avatarField.setText(file.getAbsolutePath());
			            avatarView.repaint();
			        }
				}
				
			}
		});
		
		pnlLogin = new UserConnectionPanel();
		
		JPanel pnlAvatar = new JPanel(new GridBagLayout());
		pnlAvatar.setBorder(BorderFactory.createTitledBorder(border, "Avatar"));
		pnlAvatar.add(avatarView, new GridBagConstraints(0, 0, 2, 1, 1., 1., GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5 ,5),0 ,0)); 
		pnlAvatar.add(new JLabel("Fichier :", JLabel.RIGHT), new GridBagConstraints(0, 1, 1, 1, 0., .0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5 ,5), 0,0)); 
		pnlAvatar.add(avatarField, new GridBagConstraints(1, 1, 1, 1, 1., .0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5 ,5), 0,0)); 
		
		this.setLayout(new GridBagLayout());
		this.add(pnlIdentity, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,0,10), 0, 0));
		this.add(pnlLogin, new GridBagConstraints(0, 1, 1, 1, .0, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,10,10,10), 0, 0));
		this.add(pnlAvatar, new GridBagConstraints(1, 0, 1, 2, .0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,0,10,10), 0, 0));
	}
	
	public static void main(String[] args) {
		//SubstanceLookAndFeel.setSkin(new GraphiteSkin());
	    //SubstanceLookAndFeel.setSkin(new MarinerSkin());
		SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin());
		try {
			//UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
			//UIManager.setLookAndFeel(new SubstanceMarinerLookAndFeel());
			UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
			
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame("test panel");
					frame.add (new UserPanel(), BorderLayout.CENTER);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JTextField getLastNameField() {
		return pnlIdentity.getLastNameField();
	}

	public JTextField getFirstNameField() {
		return pnlIdentity.getFirstNameField();
	}

	public JTextField getLoginField() {
		return pnlLogin.getLoginField();
	}

	public JPasswordField getPasswordField() {
		return pnlLogin.getPasswordField();
	}

	public JTextField getAvatarField() {
		return avatarField;
	}
	
	

}
