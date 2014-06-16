package com.cfranc.irc.server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	
	
	@BeforeClass
	public static void setupBeforeClass()
	{
		//((File) (new File("d:\\SQLite\\userchat.db"))).delete();
		User.urlBase = "d:\\SQLite\\userchat.db";
	}
	
	@Test
	public void testConnectionVerifUserNOTExists() {
		User user = new User("eric", "e", "eben", "", "eben");
		assertFalse(user.connectionVerifUser());
	}

	@Test
	public void testConnectionVerifUserExists() {
		User.urlBase = "d:\\SQLite\\userchat.db";
		User user = new User("eric", "e", "eric", "", "eben");
		assertTrue(user.connectionVerifUser());
	}
	

	@Test
	public void testAddUserBaseEXIST() {
		User user = new User("benetti", "eric", "eben", "", "eben");
		assertFalse(user.addUserBase());
	}

	@Test
	public void testAddUserBaseNOTEXIST() {
		User user = new User("Mal", "xavier", "xmal", "", "xmal");
		assertTrue(user.addUserBase());
	}
	
	@Test
	public void testDelUserBase() {
		fail("Not yet implemented");
	}

}
