package com.cfranc.irc.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.cfranc.irc.client.ClientToServerThread;
import com.cfranc.irc.server.User;
import com.cfranc.irc.ui.connection.ConnectionDialog;
import com.cfranc.irc.ui.user.CreateUserDialog;

public class SimpleChatClientApp implements ActionListener {
	
	
	public static final String CONNECT = "connect";
	public static final String DISCONNECT = "disconnect";
	public static final String CREATE_USER  = "create_user";
	public static final String SAVE_CONNECT_USER = "save_and_connect";
	public static final Emoticon[] EMOTICONS = {new Emoticon(":0", "emoticon/0.png"),new Emoticon(":1", "emoticon/1.png"),new Emoticon(":2", "emoticon/2.png"),
									new Emoticon(":3", "emoticon/3.png"),new Emoticon(":4", "emoticon/4.png"),new Emoticon(":5", "emoticon/5.png"),
									new Emoticon(":6", "emoticon/6.png"),new Emoticon(":7", "emoticon/7.png"),new Emoticon(":8", "emoticon/8.png"),
									new Emoticon(":9", "emoticon/9.png")};
		
	private ConnectionDialog dlgConn; 
	private CreateUserDialog dlgUser;
	
    static String[] ConnectOptionNames = { "Connect" };	
    static String   ConnectTitle = "Connection Information";
    Socket socketClientServer;
    int serverPort;
    String serverName;
    String clientName;
    String clientPwd;
    String clientRealName;
    User clientUser;
    
	private ChatFrameClient frame;
	public StyledDocument documentModel=new DefaultStyledDocument();
	DefaultListModel<User> clientListModel=new DefaultListModel<User>();
	
    public static final String BOLD_ITALIC = "BoldItalic";
    public static final String GRAY_PLAIN = "Gray";
        
	public static DefaultStyledDocument defaultDocumentModel() {
		DefaultStyledDocument res=new DefaultStyledDocument();
	    
	    Style styleDefault = (Style) res.getStyle(StyleContext.DEFAULT_STYLE);
	    
	    res.addStyle(BOLD_ITALIC, styleDefault);
	    Style styleBI = res.getStyle(BOLD_ITALIC);
	    StyleConstants.setBold(styleBI, true);
	    StyleConstants.setItalic(styleBI, true);
	    StyleConstants.setForeground(styleBI, Color.black);	    

	    res.addStyle(GRAY_PLAIN, styleDefault);
        Style styleGP = res.getStyle(GRAY_PLAIN);
        StyleConstants.setBold(styleGP, false);
        StyleConstants.setItalic(styleGP, false);
        StyleConstants.setForeground(styleGP, Color.lightGray);

		return res;
	}

	private static ClientToServerThread clientToServerThread;
			
	public SimpleChatClientApp(){
		
	}
	
	public void displayClient() {
		
		// Init GUI
		this.frame=new ChatFrameClient(clientToServerThread, clientListModel, documentModel);
		this.frame.setTitle(this.frame.getTitle()+" : "+ clientUser.getLogin() +" connected to "+serverName+":"+serverPort);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				quitApp(SimpleChatClientApp.this);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}
	
	public void hideClient() {
		((JFrame)this.frame).setVisible(false);
	}
	
    void displayConnectionDialog() {
    	dlgConn = new ConnectionDialog(this);
    	dlgConn.setModal(true);
    	dlgConn.setVisible(true); 

	}
    
    private void connectClient() {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socketClientServer = new Socket(this.serverName, this.serverPort);
			// Start connection services
			clientToServerThread=new ClientToServerThread(documentModel, clientListModel,socketClientServer,  clientUser);
			clientToServerThread.start();

			System.out.println("Connected: " + socketClientServer);
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		User.urlBase = args[0];
		final SimpleChatClientApp app = new SimpleChatClientApp();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app.displayConnectionDialog();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});

	}

	private static void quitApp(final SimpleChatClientApp app) {
		try {
			app.clientToServerThread.quitServer();
			app.socketClientServer.close();
			app.hideClient();
			System.out.println("SimpleChatClientApp : fermée");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SEND")) {
			frame.sendMessage();
			if (frame.getTextField().getText().equals(".bye")) {
				frame.dispose();
			};
			
		} else if (e.getActionCommand().equals(CONNECT)) {
			if (dlgConn != null) {
				serverName = dlgConn.getServerField().getText();
				serverPort = Integer.parseInt(dlgConn.getServerPortField().getText());
				clientName = dlgConn.getUserNameField().getText();
				clientPwd =  dlgConn.getPasswordField().getText();
				dlgConn.setVisible(false); 
				if (new User(clientName, clientPwd).connectionVerifUser())
				{
					clientUser = User.fromDataBase(clientName);
					connectClient();
					displayClient();
				} else  {
					JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas.", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
				

			}
		} else if (e.getActionCommand().equals(CREATE_USER)) {
			if (dlgConn != null) {
				serverName = dlgConn.getServerField().getText();
				serverPort = Integer.parseInt(dlgConn.getServerPortField().getText());
				clientName = dlgConn.getUserNameField().getText();
				clientPwd =  dlgConn.getPasswordField().getText();
				dlgConn.setVisible(false); 
				dlgUser = new CreateUserDialog(this);
				dlgUser.getLoginField().setText(clientName);
				dlgUser.getPasswordField().setText(clientPwd);
				dlgUser.setModal(true);
				dlgUser.setVisible(true);
			}
		} else if (e.getActionCommand().equals(SAVE_CONNECT_USER)) {
			dlgUser.setVisible(false);
			if (creerClient(dlgUser.getLastNameField().getText(), dlgUser.getFirstNameField().getText(), dlgUser.getLoginField().getText(), dlgUser.getPasswordField().getText(), dlgUser.getAvatarField().getText())) {
				clientUser = User.fromDataBase(dlgUser.getLoginField().getText());				
				connectClient();
				displayClient();
			} else {
				JOptionPane.showMessageDialog(null, "La création de l'utilisateur à echoué.", "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
		} 
		
	}


	private boolean creerClient(String name, String prenom, String login,String pwd, String avatar) {
		User user = new User(name, prenom, login, avatar, pwd);
		return user.addUserBase();
	}

}
