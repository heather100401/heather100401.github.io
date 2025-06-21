from Contact.contact import Contact
from Linked_List.linked_list import Linked_List

class ContactService:
    def __init__(self):
        self.contacts = Linked_List()

    # Add a new contact
    def add_contact(self, contact):
        if self._search(contact) != False:
            raise ValueError("Contact ID already exists")
        id = contact.get_contact_id()
        first_name = contact.get_first_name()
        last_name = contact.get_last_name()
        phone = contact.get_phone()
        address = contact.get_address()
        if id == None:
            raise ValueError("ID cannot be null")
        if len(id) > 10:
            raise ValueError("ID cannot be longer than 10 characters")
        if first_name == None:
            raise ValueError("First name cannot be null")
        if len(first_name) > 10:
            raise ValueError("First name cannot be longer than 10 characters")
        if last_name == None:
            raise ValueError("Last name cannot be null")
        if len(last_name) > 10:
            raise ValueError("Last name cannot be longer than 10 characters")
        if phone == None:
            raise ValueError("Phone number cannot be null")
        if len(phone) != 10:
            raise ValueError("Phone number must be exactly 10 characters")
        if address == None:
            raise ValueError("Address name cannot be null")
        if len(address) > 30:
            raise ValueError("Address cannot be longer than 30 characters")
        self.contacts.insert(id, contact)

    # Delete a contact
    def delete_contact(self, contact):
        if self._search(contact )== False:
            raise ValueError("Contact ID not found")
        self.contacts.remove(contact.get_contact_id())
        return True        


    # Update values
    def update_first_name(self, contact, first_name):
        if self._search(contact) == False:
            raise ValueError("Contact ID not found")
        if first_name == None:
            raise ValueError("First name cannot be null")
        if len(first_name) > 10:
            raise ValueError("First name cannot be longer than 10 characters")
        id = contact.get_contact_id()
        last_name = contact.get_last_name()
        phone = contact.get_phone()
        address = contact.get_address()
        new_contact = Contact(id, first_name, last_name, phone, address)
        self.delete_contact(contact)
        self.add_contact(new_contact)

    def update_last_name(self, contact, last_name):
        if self._search(contact) == False:
            raise ValueError("Contact ID not found")
        if last_name == None:
            raise ValueError("Last name cannot be null")
        if len(last_name) > 10:
            raise ValueError("Last name cannot be longer than 10 characters")
        id = contact.get_contact_id()
        first_name = contact.get_first_name()
        phone = contact.get_phone()
        address = contact.get_address()
        new_contact = Contact(id, first_name, last_name, phone, address)
        self.delete_contact(contact)
        self.add_contact(new_contact)

    def update_phone(self, contact, phone):
        if self._search(contact) == False:
            raise ValueError("Contact ID not found")
        if phone == None:
            raise ValueError("Phone number cannot be null")
        if len(phone) != 10:
            raise ValueError("Phone number must be exactly 10 characters")
        id = contact.get_contact_id()
        first_name = contact.get_first_name()
        last_name = contact.get_last_name() 
        address = contact.get_address()
        new_contact = Contact(id, first_name, last_name, phone, address)
        self.delete_contact(contact)
        self.add_contact(new_contact)

    def update_address(self, contact, address):
        if self._search(contact) == False:
            raise ValueError("Contact ID not found")
        if address == None:
            raise ValueError("Address name cannot be null")
        if len(address) > 30:
            raise ValueError("Address cannot be longer than 30 characters")
        id = contact.get_contact_id()
        first_name = contact.get_first_name()
        last_name = contact.get_last_name() 
        phone = contact.get_phone()
        new_contact = Contact(id, first_name, last_name, phone, address)
        self.delete_contact(contact)
        self.add_contact(new_contact)

    # Internal search function
    def _search(self, contact):
        contact = self.contacts.search(contact.get_contact_id())
        return contact
    
    # External, returns contact
    def find_contact_by_id(self, id):
        contact = self.contacts.search(id)
        if contact == False:
            raise ValueError("Contact ID not found")
        return contact
    
    # Prints all contacts in list
    def print_list(self):
        node = self.contacts.get_head()
        while node:
            contact = node.data
            self.print_contact(contact)
            node = self.contacts.next_node(node)

    # Prints a singular contact
    def print_contact(self, contact):
        print("\nContact", contact.get_contact_id(), "\n\tName:\t\t\t", contact.get_first_name(), contact.get_last_name(), "\n\tPhone Number:\t\t", contact.get_phone(), "\n\tAddress:\t\t", contact.get_address())
