from Linked_List.linked_list import Linked_List
from datetime import datetime

class AppointmentService:
    def __init__(self):
        self.appointments = Linked_List()

    # Add a new appointment
    def add_appointment(self, appointment):
        if self._search(appointment) != False:
            raise ValueError("Appointment ID already exists")
        id = appointment.get_appointment_id()
        date = appointment.get_date()
        description = appointment.get_description()
        if id == None:
            raise ValueError("ID cannot be null")
        if len(id) > 10:
            raise ValueError("ID cannot be longer than 10 characters")
        if date == None:
            raise ValueError("Date cannot be null")
        if date < datetime.now():
            raise ValueError("Date cannot be in the past")
        if description == None:
            raise ValueError("Description cannot be null")
        if len(description) > 50:
            raise ValueError("Description cannot be longer than 50 characters")
        self.appointments.insert(id, appointment)

    # Delete an appointment
    def delete_appointment(self, appointment):
        if self._search(appointment) == False:
            raise ValueError("Appointment ID not found")
        self.appointments.remove(appointment.get_appointment_id())

    # Internal search function
    def _search(self, appointment):
        appointment = self.appointments.search(appointment.get_appointment_id())
        return appointment

    # External, returns appointment
    def find_appointment_by_id(self, id):
        appointment = self.appointments.search(id)
        if appointment == False:
            raise ValueError("Appointment ID not found")
        return appointment
    
    # Prints all appointments in list
    def print_list(self):
        node = self.appointments.get_head()
        while node:
            appointment = node.data
            self.print_appointment(appointment)
            node = self.appointments.next_node(node)

    def print_appointment(self, appointment):
        print("\nAppointment", appointment.get_appointment_id(), "\n\tDate:\t\t", appointment.get_date().strftime('%m/%d/%Y'), "\n\tDescription:\t", appointment.get_description())

