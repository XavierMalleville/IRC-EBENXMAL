package com.cfranc.irc.server;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.cfranc.irc.IfClientServerProtocol;

public class BroadcastThread extends Thread implements IfClientServerProtocol {
	
	public static HashMap<User, ServerToClientThread> clientTreadsMap=new HashMap<User, ServerToClientThread>();
	static{
		Collections.synchronizedMap(clientTreadsMap);
	}
	
	/**
	 * Cette m�thode addClient envoie un message � tous les clients pour leurs signifier 
	 * la connection d'un nouvel utilisateur,et elle envoie un message d'ajout des autres 
	 * d'utilisateur au clients qui vient de ce connecter.
	 * 
	 * @param user
	 */
	
	public static boolean addClient(User user, ServerToClientThread serverToClientThread){
		boolean res=true;
		if(clientTreadsMap.containsKey(user)){
			res=false;
		}
		else{
			clientTreadsMap.put(user, serverToClientThread);
			
			Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
			Collection<User> users =clientTreadsMap.keySet();
			Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
			
			while (receiverClientThreadIterator.hasNext()) {
				ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
				for (User userExistant : users) {
					clientThread.post(IfClientServerProtocol.ADD + userExistant.getLogin());	
				} 
			}
		
		}
		return res;
	}

	public static void sendMessage(User sender, String msg){
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			clientThread.post("#"+sender.getLogin()+"#"+msg);			
			System.out.println("sendMessage : "+"#"+sender.getLogin()+"#"+msg);
			
		}
	}

	/**
	 * Cette m�thode removeClient envoie un message � tous les clients pour leurs signifier 
	 * la deconnection d'un utilisateur,et elle parcour tous les enfants de l'arbre pour supprimer 
	 * celui qui correspond � l'utilisateur qui se deconnecte et demande au modele de se rafraichir.
	 * 
	 * @param user
	 */
	public static void removeClient(User user){
		clientTreadsMap.remove(user);
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			clientThread.post(IfClientServerProtocol.DEL + user.getLogin() );
		}
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) ClientConnectThread.getClientListModel().getRoot();
		for (int i= 0; i <root.getChildCount(); i++ ) {
			if (root.getChildAt(i).toString().equals(user.getLogin())) {
				root.remove(i);
				ClientConnectThread.getClientListModel().reload((TreeNode) root);
				break;
			}
		}
	}
	
	public static boolean accept(User user){
		boolean res=true;
		if(clientTreadsMap.containsKey(user)){
			res= false;
		}
		return res;
	}
}
