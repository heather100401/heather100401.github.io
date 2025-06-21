class Contact:
    def __init__(self, contact_id, first_name, last_name, phone, address):
        self._contact_id = contact_id               # Unique, cannot be updated, max length of 10
        self._first_name = first_name               # Cannot be null, max length 10
        self._last_name = last_name                 # Cannot be null, max length 10
        self._phone = phone                         # Cannot be null, exactly 10 digits
        self._address = address                     # Cannot be null, max length 30
    
    #getters
    def get_contact_id(self):
        return self._contact_id
    
    def get_first_name(self):
        return self._first_name
    
    def get_last_name(self):
        return self._last_name
    
    def get_phone(self):
        return self._phone
    
    def get_address(self):
        return self._address

    #setters
    def set_first_name(self, first_name):
        self._first_name = first_name

    def set_last_name(self, last_name):
        self._last_name = last_name   

    def set_phone(self, phone):
        self._phone = phone

    def set_address(self, address):
        self._address = address 

