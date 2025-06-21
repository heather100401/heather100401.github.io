from datetime import datetime

class Appointment:
    def __init__(self, appointment_id, date, description):
        self._appointment_id = appointment_id               # Unique, cannot be updated, max length 10
        self._date = date                                   # Cannot be null or in the past
        self._description = description                     # Cannot be null, max length 50
        
    # Getters
    def get_appointment_id(self):
        return self._appointment_id
    
    def get_date(self):
        return self._date
    
    def get_description(self):
        return self._description

    # Setters
    def set_date(self, date):
        self._date = date

    def set_description(self, description):
        self._description = description
