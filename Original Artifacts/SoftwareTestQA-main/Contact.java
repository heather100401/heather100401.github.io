package contactService;

public class Contact {
	
	private final String contactID; // unique, cannot be updated, max length 10
    private String firstName; // cannot be null, max length 10
    private String lastName; // cannot be null, max length 10
    private String phone; // cannot be null, exactly 10 digits
    private String address; // cannot be null, max length 30
    
    public Contact(String contactID, String firstName, String lastName, String phone, String address) {
    	if (contactID == null || contactID.length() > 10){
            throw new IllegalArgumentException("Error: Contact ID cannot be null or longer than 10 characters.");
        } else{
            this.contactID = contactID;
        }

        if (firstName == null || firstName.length() > 10){
            throw new IllegalArgumentException("Error: First name cannot be null or longer than 10 characters.");
        } else{
            this.firstName = firstName;
        }
        
        if (lastName == null || lastName.length() > 10){
            throw new IllegalArgumentException("Error: Last name cannot be null or longer than 10 characters.");
        } else{
            this.lastName = lastName;
        }

        if (phone == null || phone.length() != 10){
            throw new IllegalArgumentException("Error: Phone number cannot be null and must be 10 characters.");
        } else{
            this.phone = phone;
        }
        
        if (address == null || address.length() > 30){
            throw new IllegalArgumentException("Error: Address cannot be null or longer than 30 characters.");
        } else{
            this.address = address;
        }
    }
    
    //Getters
    public String getContactID() {
        return contactID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }

    //Setters
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10){
            throw new IllegalArgumentException("Error: First name cannot be null or longer than 10 characters.");
        } else{
            this.firstName = firstName;
        }
    }
    
    public void setLastName(String lastName) {
    	if (lastName == null || lastName.length() > 10){
            throw new IllegalArgumentException("Error: Last name cannot be null or longer than 10 characters.");
        } else{
            this.lastName = lastName;
        }
    }
    
    public void setPhone(String phone) {
    	if (phone == null || phone.length() != 10){
            throw new IllegalArgumentException("Error: Phone number cannot be null and must be 10 characters.");
        } else{
            this.phone = phone;
        }
    }
    
    public void setAddress(String address) {
    	if (address == null || address.length() > 30){
            throw new IllegalArgumentException("Error: Address cannot be null or longer than 30 characters.");
        } else{
            this.address = address;
        }
    }
    
}
