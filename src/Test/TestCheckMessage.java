package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import checkmess.Message;

class TestCheckMessage {

	@Test
	void testSuccess() {
		Message mess = new Message("Frd G", 8088, "0987654321");
		Message mess1 = new Message("I just sent Frd G !!! ^_^", 8088, "0987654321");
		mess.exe();
		mess1.exe();
		assertEquals("Success", 2, mess.getResult());
		assertEquals("Success", 2, mess1.getResult());
	}
	
	
	@Test
	void testFrdMissing() {
		Message mess = new Message("Fra B", 8088, "0987654321");
		Message mess1 = new Message("I send Frdd", 8088, "0987654321");
		mess.exe();
		mess1.exe();
		assertEquals("Frd missing", mess.getMessageReturn().trim());
		assertEquals(0, mess.getResult());
		assertEquals("Frd missing", mess1.getMessageReturn().trim());
	}

	
	@Test
	void testBorGMissing() {
		Message mess = new Message("Frd BA", 8088, "0987654321");
		Message mess1 = new Message("Frd gh Fr", 8088, "0987654321");
		mess.exe();
		mess1.exe();
		assertEquals("B/G missing", mess.getMessageReturn().trim());
		assertEquals("B/G missing", mess1.getMessageReturn().trim());
	}
	
	@Test
	void testReceiver() {
		Message mess = new Message("Frd chg", 8088, "0987654321");
		Message mess1 = new Message("Frd del", 8033, "0987654321");
		mess.exe();
		mess1.exe();
		assertEquals("You must send to 8033", mess.getMessageReturn().trim());
		assertEquals("You must send to 8011", mess1.getMessageReturn().trim());
	}
	
	@Test
	void testNewUser() {
		Message mess = new Message("Frd B", 8080, "0987654323");
		mess.exe();
		assertEquals("You should send \"Frd B\" or \"Frd G\"to 8088", mess.getMessageReturn().trim());
	}
	
	@Test
	void testRejoin() {
		Message mess = new Message("Frd B", 8080, "0987654322");
		Message mess1 = new Message("Frd B", 8011, "0987654322");
		Message mess2 = new Message("Frd RJ", 8011, "0987654322");
		mess.exe();
		mess1.exe();
		mess2.exe();
		assertEquals("You must rejoin system. Please send \' Frd RJ B(or G)\' to 8011", mess.getMessageReturn().trim());
		assertEquals("RJ missing.You should send \"Frd RJ B\" or \"Frd RJ G\" to 8011", mess1.getMessageReturn().trim());
		assertEquals("B/G missing", mess2.getMessageReturn().trim());
	}

	
}