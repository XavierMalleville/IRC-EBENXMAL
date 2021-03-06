package com.cfranc.irc;

/** IRC protocol words
 * 
 * @author Administrateur
 *
 */
public interface IfClientServerProtocol {
	public static final String LOGIN_PWD = "#Login?#Pwd?";
	public static final String SEPARATOR="#";
	public static final String KO = "#KO";
	public static final String OK = "#OK";
	public static final String ADD = "#+#";
	public static final String DEL = "#-#";
	public static final String BYE = ".bye";
}