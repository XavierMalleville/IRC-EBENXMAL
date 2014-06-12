package com.cfranc.irc.server;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.cfranc.irc.IfClientServerProtocol;

public class BroadcastThread extends Thread implements IfClientServerProtocol {
	
	public static HashMap<User, ServerToClientThread> clientTreadsMap=new HashMap<User, ServerToClientThread>();
	static{
		Collections.synchronizedMap(clientTreadsMap);
	}
	
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
	
	public static void removeClient(User user){
		clientTreadsMap.remove(user);
		Collection<ServerToClientThread> clientTreads=clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator=clientTreads.iterator();
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			clientThread.post(IfClientServerProtocol.DEL + user.getLogin() );
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
