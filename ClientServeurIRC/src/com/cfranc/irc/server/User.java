package com.cfranc.irc.server;

public class User {

	private String name;
	private String login;
	private String pwd;
	
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
		
	public User(String name, String login, String pwd) {
		super();
		this.name = name;
		this.login = login;
		this.pwd = pwd;
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
	
	

	
	
}
