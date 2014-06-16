package com.cfranc.irc.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;

public class User {

	private String name;
	private String prenom;
	private String login;
	private String avatar;
	private String pwd;
	
	/* Emplacement de la base de donnée du serveur */
	public static String urlBase;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {	
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public User(String name, String prenom ,String login, String avatar, String pwd) {
		super();
		this.name = name;
		this.prenom= prenom;
		this.login = login;
		this.avatar= avatar;
		this.pwd = pwd;
	}
	public User(String name, String login, String pwd) {
		super();
		this.name = name;
		this.login = login;
		this.pwd = pwd;
		
		this.prenom= null;
		this.avatar= null;
	}

	public User(String login, String pwd) {
		this(login, login, pwd);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	public boolean connectionVerifUser() {
		//return  this.connectionVerifUser("c:"+ File.separator);
		return  this.connectionVerifUser(urlBase);		
	}
	
	public boolean connectionVerifUser(String filePath) {
		boolean result = false;
		if (!filePath.equals("")) {			
			Connection connection = connectionBase ("jdbc:sqlite:" + filePath);
			try {
				// Action de vérification
				result = controleExistanceUser(connection);
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e);
				}
			}			
		}		
		else {
			System.out.println("Le chemin de la base est foireux");			
		}	
		return result;
	}
	private boolean controleExistanceUser(Connection connection) {
		Statement statement;
		boolean result = false;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			result = statement.execute("SELECT * FROM TUserIRC WHERE Pseudo = " + this.login + " AND Pwd = " + this.pwd );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;						
	}
	
	public boolean addUserBase () {
		return  this.addUserBase(urlBase);
	}
	public boolean addUserBase (String filePath) {
		/* Ajouter à la base l'utilisateur actuel */
		boolean result = false;
		if (!filePath.equals("")) {			
			Connection connection = connectionBase ("jdbc:sqlite:" + filePath);
			try {
				if (! controleExistanceUser(connection)) {
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30);
					statement.executeUpdate("INSERT INTO TUserIRC VALUES ("+this.name+","+this.prenom+","+this.login+","+this.avatar+","+this.pwd+") ");
					result = true;
				}	
				else {
					System.out.println("Erreur, l'utilisateur existe déjà");
				}					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
					return result;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e);
				}
			}			
		}		
		else {
			System.out.println("Le chemin de la base est foireux");			
		}	
		return result;			
	}
	
	public boolean delUserBase () {
		return  this.addUserBase(urlBase);
	}
	public boolean delUserBase (String filePath) {
		/* Retirer de la base l'utilisateur actuel */
		boolean result = false;
		if (!filePath.equals("")) {			
			Connection connection = connectionBase ("jdbc:sqlite:" + filePath);
			try {
				if (controleExistanceUser(connection)) {
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30);
					statement.executeUpdate("DELETE * FROM TUserIRC WHERE Pseudo = " + this.login);
					result = true;
				}	
				else {
					System.out.println("Erreur, l'utilisateur n'existe plus");
				}					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
					return result;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e);
				}
			}			
		}		
		else {
			System.out.println("Le chemin de la base est foireux");			
		}	
		return result;	
	}
	
	private Connection connectionBase(String url) {
		Connection connection = null;			
		if(JDBC.isValidURL(url)) {				
			try {
				connection = DriverManager.getConnection(url);
				// Création de la table de référence si elle n'existe pas
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS TUserIRC (name string, prenom String, login String, avatar String, pwd String)");				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
		return connection;		
	}	
}
