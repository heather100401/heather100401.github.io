package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import contactService.Contact;

class ContactTest {

	@Test
	void testContactID() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		assertTrue(contact.getContactID().equals("123"));
	}
	
	@Test
	void testLongContactID() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345678910", "Heather", "Page", "1234567890", "Address");
			});
	}
	
	@Test
	void testNullContactID() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "Heather", "Page", "1234567890", "Address");
			});
	}
	
	@Test
	void testFirstName() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		assertTrue(contact.getFirstName().equals("Heather"));
	}
	
	@Test
	void testSetFirstName() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		contact.setFirstName("NewName");
		assertTrue(contact.getFirstName().equals("NewName"));
	}
	
	@Test
	void testLongFirstName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "HeatherTooLongName", "Page", "1234567890", "Address");
			});
	}
	
	@Test
	void testNullFirstName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", null, "Page", "1234567890", "Address");
			});
	}
	
	@Test
	void testLastName() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		assertTrue(contact.getLastName().equals("Page"));
	}
	
	@Test
	void testSetLastName() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		contact.setLastName("NewName");
		assertTrue(contact.getLastName().equals("NewName"));
	}
	
	@Test
	void testLongLastName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "PageTooLongLastName", "1234567890", "Address");
			});
	}
	
	@Test
	void testNullLastName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", null, "1234567890", "Address");
			});
	}
	
	@Test
	void testPhone() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		assertTrue(contact.getPhone().equals("1234567890"));
	}
	
	@Test
	void testSetPhone() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		contact.setPhone("4567890123");
		assertTrue(contact.getPhone().equals("4567890123"));
	}
	
	@Test
	void testLongPhone() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "Page", "1234567891011", "Address");
			});
	}
	
	@Test
	void testShortPhone() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "Page", "1234", "Address");
			});
	}
	
	@Test
	void testNullPhone() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "Page", null, "Address");
			});
	}
	
	@Test
	void testAddress() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		assertTrue(contact.getAddress().equals("Address"));
	}
	
	@Test
	void testSetAddress() {
		Contact contact = new Contact("123", "Heather", "Page", "1234567890", "Address");
		contact.setAddress("123 New Address");
		assertTrue(contact.getAddress().equals("123 New Address"));
	}
	
	@Test
	void testLongAddress() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "Page", "1234567890", "This Address Is Way Too Longggggggggg");
			});
	}
	
	@Test
	void testNullAddress() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123", "Heather", "Page", "1234567890", null);
			});
	}
	
	

	
}
