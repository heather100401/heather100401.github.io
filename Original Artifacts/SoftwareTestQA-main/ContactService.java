package contactService;

import java.util.HashMap;
import java.util.Map;


public class ContactService {
	
	private Map<String, Contact> contacts;
	
	public ContactService() {
		contacts = new HashMap<>();
	}
	
	// Add a new contact
    public void addContact(Contact contact){
        if (contacts.containsKey(contact.getContactID())){
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactID(), contact);
    }
	
    // Delete a contact
    public void deleteContact(String contactID){
        if(!contacts.containsKey(contactID)){
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.remove(contactID);
    }
    
    // Update first name
    public void updateFirstName(String contactID, String firstName){
        Contact contact = getContact(contactID);
        contact.setFirstName(firstName);
    }
    
    // Update last name
    public void updateLastName(String contactID, String lastName){
        Contact contact = getContact(contactID);
        contact.setLastName(lastName);
    }
    
    // Update phone
    public void updatePhone(String contactID, String phone){
        Contact contact = getContact(contactID);
        contact.setPhone(phone);
    }
    
    // Update address
    public void updateAddress(String contactID, String address){
        Contact contact = getContact(contactID);
        contact.setAddress(address);
    }
    
    //Get Contact ID
    public String getContactID(String contactID) {
    	Contact contact = getContact(contactID);
    	return contact.getContactID();
    }
    
    //Get First Name
    public String getFirstName(String contactID) {
    	Contact contact = getContact(contactID);
    	return contact.getFirstName();
    }
    
    //Get Last Name
    public String getLastName(String contactID) {
    	Contact contact = getContact(contactID);
    	return contact.getLastName();
    }
    
    //Get Phone
    public String getPhone(String contactID) {
    	Contact contact = getContact(contactID);
    	return contact.getPhone();
    }
    
    //Get Address
    public String getAddress(String contactID) {
    	Contact contact = getContact(contactID);
    	return contact.getAddress();
    }
    
	// Helper method to get a contact by ID
    private Contact getContact(String contactID){
        Contact contact = contacts.get(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        return contact;
    }

}
