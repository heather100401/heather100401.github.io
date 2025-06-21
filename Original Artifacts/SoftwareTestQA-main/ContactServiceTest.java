package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import contactService.Contact;
import contactService.ContactService;



class ContactServiceTest {
	
	Contact contact = new Contact("001", "Heather", "Page", "1234567890", "Address");
	
	ContactService contactService = new ContactService();

	@Test
	void testAddContactID() {
		contactService.addContact(contact);
		assertTrue(contactService.getContactID(contact.getContactID()).equals("001"));
	}
	
	@Test
	void testAddFirstName() {
		contactService.addContact(contact);
		assertTrue(contactService.getFirstName(contact.getContactID()).equals("Heather"));
	}
	
	@Test
	void testAddLastName() {
		contactService.addContact(contact);
		assertTrue(contactService.getLastName(contact.getContactID()).equals("Page"));
	}
	
	@Test
	void testAddPhone() {
		contactService.addContact(contact);
		assertTrue(contactService.getPhone(contact.getContactID()).equals("1234567890"));
	}
	
	@Test
	void testAddAddress() {
		contactService.addContact(contact);
		assertTrue(contactService.getAddress(contact.getContactID()).equals("Address"));
	}
	
	void testAddExistingContact() {
		contactService.addContact(contact);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.addContact(contact);
		});
	}
	
	@Test
	void testDeleteContact() {
		contactService.addContact(contact);
		contactService.deleteContact("001");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.deleteContact("001");
		});
	}
	
	@Test
	void testGetDeletedTask() {
		contactService.addContact(contact);
		contactService.deleteContact("001");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.getContactID(contact.getContactID());
		});
	}
	
	@Test
	void testUpdateFirstName() {
		contactService.addContact(contact);
		contactService.updateFirstName("001", "New Name");
		assertTrue(contactService.getFirstName(contact.getContactID()).equals("New Name"));
	}
	
	@Test
	void testUpdateLastName() {
		contactService.addContact(contact);
		contactService.updateLastName("001", "New Name");
		assertTrue(contactService.getLastName(contact.getContactID()).equals("New Name"));
	}
	
	@Test
	void testUpdatePhone() {
		contactService.addContact(contact);
		contactService.updatePhone("001", "4567890123");
		assertTrue(contactService.getPhone(contact.getContactID()).equals("4567890123"));
	}
	
	@Test
	void testUpdateAddress() {
		contactService.addContact(contact);
		contactService.updateAddress("001", "New Address");
		assertTrue(contactService.getAddress(contact.getContactID()).equals("New Address"));
	}

}
