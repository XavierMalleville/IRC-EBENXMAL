package com.cfranc.irc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import com.cfranc.irc.client.IfSenderModel;
import com.cfranc.irc.server.User;

public class ChatFrameClient extends JFrame {
	
	private static Document documentModel;
	private static ListModel<User> listModel;

	private final ResourceAction sendAction = new SendAction();
	private final ResourceAction disconnectAction = new DisconnectAction();
	private final ResourceAction lockAction = new LockAction();
	private final ResourceAction visibleAction = new VisibleAction() ;
	private IfSenderModel sender;
	private String senderName;	
	private Emoticon emoticon;
	private JTextField textField;
	private JLabel lblSender;
	private JToolBar toolBar;
	private JTextPane textArea;
	
	private boolean isScrollLocked=true;
	

	
	public ChatFrameClient() {
		this(null, new DefaultListModel<User>(), SimpleChatClientApp.defaultDocumentModel());
	}

	/**
	 * Create the frame.
	 */
	public ChatFrameClient(IfSenderModel sender, ListModel<User> clientListModel, Document documentModel) {
		this.sender=sender;
		this.documentModel=documentModel;
		documentModel.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				Document doc = e.getDocument();
				try {
					getTextArea().setText(doc.getText(0, doc.getLength()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}  
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		
		this.listModel= clientListModel;
		setTitle(Messages.getString("SimpleChatFrameClient.4")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		//Le menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu(Messages.getString("SimpleChatFrameClient.5")); //$NON-NLS-1$
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		JMenuItem mntmEnregistrerSous = new JMenuItem(Messages.getString("SimpleChatFrameClient.6")); //$NON-NLS-1$
		mnFile.add(mntmEnregistrerSous);
				
		JMenu mnOutils = new JMenu(Messages.getString("SimpleChatFrameClient.7")); //$NON-NLS-1$
		mnOutils.setMnemonic('O');
		menuBar.add(mnOutils);
		
		JMenuItem mntmEnvoyer = new JMenuItem(Messages.getString("SimpleChatFrameClient.8")); //$NON-NLS-1$
		mntmEnvoyer.setAction(sendAction);
		mnOutils.add(mntmEnvoyer);
		mnOutils.add(disconnectAction);
		
		JSeparator separator = new JSeparator();
		mnOutils.add(separator);
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem(lockAction);
		mnOutils.add(chckbxmntmNewCheckItem);


		lblSender = new JLabel("?"); //$NON-NLS-1$
		lblSender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSender.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSender.setPreferredSize(new Dimension(100, 14));
		lblSender.setMinimumSize(new Dimension(100, 14));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.getInputMap().put(KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, 0),
                Messages.getString("SimpleChatFrameClient.12")); //$NON-NLS-1$
		textField.getActionMap().put(Messages.getString("SimpleChatFrameClient.13"), sendAction); //$NON-NLS-1$
		textField.setPreferredSize(new Dimension(300, 20));
		textField.setMinimumSize(new Dimension(300, 20));
		
		JButton btnSend = new JButton(sendAction);
		btnSend.setMnemonic(KeyEvent.VK_ENTER);

		
		JList<Emoticon> listEmoticon = new JList<Emoticon>(SimpleChatClientApp.EMOTICONS);
		listEmoticon.setCellRenderer(new EmoticonListRenderer());
		listEmoticon.setVisibleRowCount(3);
		listEmoticon.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listEmoticon.setMinimumSize(new Dimension(180,75));
		listEmoticon.setPreferredSize(new Dimension(180,75));
		listEmoticon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listEmoticon.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int iFirstSelectedElement=((JList)e.getSource()).getSelectedIndex();
				if(iFirstSelectedElement>=0 && iFirstSelectedElement<((JList)e.getSource()).getModel().getSize()){
					emoticon= (Emoticon) ((JList)e.getSource()).getModel().getElementAt(iFirstSelectedElement);
				}
			}
		});
		
		listEmoticon.addMouseListener(new MouseListener() {
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
				if (e.getClickCount()==2) {
					getTextField().setText(getTextField().getText() + emoticon.getCode());
				}

			}
		});
		
		JPanel senderPanel = new JPanel(new GridBagLayout());
		senderPanel.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
		senderPanel.add(lblSender, new GridBagConstraints(0, 0, 1, 1, .0, .0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(10,10,10,0),0,0));
		senderPanel.add(textField, new GridBagConstraints(1, 0, 1, 1, .8, .0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10,10,10,0),0,0));
		senderPanel.add(btnSend, new GridBagConstraints(2, 0, 1, 1, .8, .0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10,10,10,0),0,0));
		senderPanel.add(new JScrollPane(listEmoticon), new GridBagConstraints(3, 0, 1, 2, .8, .0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(10,10,10,0),0,0));
		
		senderPanel.add(new JPanel(), new GridBagConstraints(0, 1, 3, 1, .0, .0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,10,0),0,0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

		JList<User> list = new JList<User>(listModel);
		list.setCellRenderer(new ListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int iFirstSelectedElement=((JList)e.getSource()).getSelectedIndex();
				if(iFirstSelectedElement>=0 && iFirstSelectedElement<listModel.getSize()){
					senderName= (listModel.getElementAt(iFirstSelectedElement)).getLogin();
					getLblSender().setText(senderName);
				}
				else{
					getLblSender().setText("?"); //$NON-NLS-1$
				}
			}
		});
		list.setMinimumSize(new Dimension(100, 0));
		splitPane.setLeftComponent(list);

		textArea = new JTextPane();
		textArea.setContentType("text/html");
		textArea.setEnabled(true);
		textArea.setEditable(false);
		
		JTabbedPane onglet = new JTabbedPane();
		onglet.add("Salon",new JScrollPane(textArea));
		splitPane.setRightComponent(onglet);
		
		toolBar = new JToolBar();
		toolBar.setVisible(false);

		add(toolBar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);
		add(senderPanel, BorderLayout.PAGE_END);
		
		JMenuItem mntmAfficherTB = new JMenuItem(Messages.getString("SimpleChatFrameClient.14")); //$NON-NLS-1$
		mntmAfficherTB.setAction(visibleAction);
		mnOutils.add(mntmAfficherTB);
		
		toolBar.add(sendAction);
		toolBar.add(disconnectAction);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point location = new Point((screenSize.width - this.getWidth())/2 , (screenSize.height - this.getHeight())/2); 
		this.setLocation(location);		
	
	}


	public JLabel getLblSender() {
		return lblSender;
	}
	
	private abstract class ResourceAction extends AbstractAction {
		public ResourceAction() {
		}
	}

	private class SendAction extends ResourceAction{	
		private Icon getIcon(){
			return new ImageIcon(ChatFrameClient.class.getResource("send_16_16.jpg")); //$NON-NLS-1$
		}
		public SendAction(){
			putValue(NAME, Messages.getString("SimpleChatFrameClient.3")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, Messages.getString("SimpleChatFrameClient.2")); //$NON-NLS-1$
			putValue(SMALL_ICON, getIcon());
		}
		public void actionPerformed(ActionEvent e) {
			sendMessage();
			if (textField.getText().equals(".bye")) {
				close();
			} else {
				textField.setText("");
			};
		}
	}
	
	private class DisconnectAction extends ResourceAction{	
		public DisconnectAction(){
			putValue(NAME, SimpleChatClientApp.DISCONNECT); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, "Se déconnecter"); //$NON-NLS-1$
		}
		public void actionPerformed(ActionEvent e) {
			textField.setText(".bye");
			sendMessage();
			close();
		}
	}
	
	private class LockAction extends ResourceAction{	
		public LockAction(){
			putValue(NAME, Messages.getString("SimpleChatFrameClient.1")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, Messages.getString("SimpleChatFrameClient.0")); //$NON-NLS-1$
		}
		public void actionPerformed(ActionEvent e) {
			isScrollLocked=(!isScrollLocked);
		}
	}
	
	private class VisibleAction extends ResourceAction{
		public VisibleAction(){
			putValue(NAME, Messages.getString("SimpleChatFrameClient.15")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, Messages.getString("SimpleChatFrameClient.14")); //$NON-NLS-1$
		}
		public void actionPerformed(ActionEvent e) {
			toolBar.setVisible(! toolBar.isVisible());			
		}
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public JTextField getTextField() {
		return textField;
	}

	public static void sendMessage(String user, String line, Style styleBI, Style styleGP) {
        try {
			documentModel.insertString(documentModel.getLength(), user+" : ", styleBI); //$NON-NLS-1$
			documentModel.insertString(documentModel.getLength(), line+"\n", styleGP); //$NON-NLS-1$
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}				        	
	}
	
	public void sendMessage() {
		sender.setMsgToSend(textField.getText());
	}
	public void close() {
		this.dispose();
	}
	
	/**
	 * Launch the application.
	 * @throws BadLocationException 
	 */
	public static void main(String[] args) throws BadLocationException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatFrameClient frame = new ChatFrameClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		

		Scanner sc=new Scanner(System.in);
		String line=""; //$NON-NLS-1$
		while(!line.equals(".bye")){ //$NON-NLS-1$
			line=sc.nextLine();			
		}
	}

	public JTextPane getTextArea() {
		return textArea;
	}
	
}
