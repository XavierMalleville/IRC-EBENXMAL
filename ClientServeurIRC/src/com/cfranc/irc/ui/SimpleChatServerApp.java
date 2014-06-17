package com.cfranc.irc.ui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.cfranc.irc.server.ClientConnectThread;
import com.cfranc.irc.server.User;

public class SimpleChatServerApp {
	private static Properties properties; 
	private SimpleChatFrameServer frame;
	public StyledDocument model=new DefaultStyledDocument();
	DefaultTreeModel clientListModel=new DefaultTreeModel(new DefaultMutableTreeNode("root"));
	private ClientConnectThread clientConnectThread;
			
	public SimpleChatServerApp(int port) {
		
		// Init GUI
		this.frame=new SimpleChatFrameServer(port, this.model, clientListModel);		
		try {
			this.model.insertString(this.model.getLength(), "Wellcome into IRC Server Manager\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JFrame)this.frame).setVisible(true);
		
		// Start connection services
		this.clientConnectThread=new ClientConnectThread(port, this.model, clientListModel);
		this.clientConnectThread.start();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		properties = new Properties();
		try {
			properties.load(new FileReader(new File("server.properties")));
			User.urlBase = properties.getProperty("database");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SimpleChatServerApp app = new SimpleChatServerApp(4567);					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}